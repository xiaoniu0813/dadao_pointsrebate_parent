package com.dadao.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dadao.merchants.entity.Order;
import com.dadao.merchants.entity.OrderQuery;
import com.dadao.order.entity.OrderDetailsPO;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.entity.YOPGeneratePayOrderParm;
import com.dadao.order.mapper.IOrderDetailsMapper;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.order.service.IOrderService;
import com.dadao.pub.utils.DADAO;
import com.dadao.pub.utils.YOPTODADAO;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.vo.ShopGoodsVO;
import com.dadao.shop.mapper.IShopGoodsMapper;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.DateUtils;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单对接
 *
 * @auther NFY niufuyang
 * @create 2017-11-15
 */
@Service
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private IShopMapper iShopMapper;
    @Autowired
    private IOrderMapper iOrderMapper;
    @Autowired
    private UserAccountMapper mapper;
    @Autowired
    private IShopGoodsMapper iShopGoodsMapper;
    @Autowired
    private IOrderDetailsMapper iOrderDetailsMapper;


    @Override
    public Object createOrder(Integer quantity,OrderPO orderPO, Integer payMode,Long goodsId, String token, HttpServletRequest request, HttpServletResponse response) {
        //根据token获取用户信息
        UserAccount userAccount = mapper.findUserByToken(token);
        orderPO.setUserId(userAccount.getUserId());
        //根据商铺ID查询商铺在易宝的子商户号
        Shop shop = iShopMapper.findByShopId(orderPO.getShopId());
        orderPO.setChild_merchant_no(shop.getSubMerchantNo());
        //父商编
        String parentMerchantNo = YeepayService.getParentMerchantNo();

        //---修改分账商户 start
        //收单商户商编
        //String merchantNo = orderPO.getChild_merchant_no();
        //收单商户商编
        String receiptMerchantNo=YeepayService.getReceiptMerchantNo();
        //---修改分账商户 end

        //格式化时间精确到毫秒
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //拼接商户订单号
        String orderId = "DADAO_" + orderPO.getUserId() + formatter.format(new Date());
        ShopGoodsVO shopGoodsVO=new ShopGoodsVO();
        //订单总金额
        String orderAmount = "";
        if(payMode==0) {
            //线下支付
            orderAmount = orderPO.getAmount().toString();
        }else{
            //线上支付
            //获取购买商品
            shopGoodsVO=(ShopGoodsVO) iShopGoodsMapper.findById(goodsId);
            orderAmount= ArithUtil.mul(shopGoodsVO.getGoodsPrice(),new BigDecimal(quantity)).toString() ;
            orderPO.setAmount(new BigDecimal(orderAmount));
        }
        //服务器回调地址
        String notifyUrl = YeepayService.getNotifyUrl();
        //产品名称和描述
        String goodsName ="";
        String goodsDesc ="";
        if(payMode==0) {
            goodsName = shop.getShopName();
            goodsDesc = shop.getShopDescription();
        }else{
            goodsName = shopGoodsVO.getGoodsName();
            goodsDesc = shopGoodsVO.getDescription();
        }
        String goodsParamExt = "{\"goodsName\":\"" + goodsName + "\",\"goodsDesc\":\"" + goodsDesc + "\"}";


        Map<String, String> params = new HashMap<>();
        params.put("parentMerchantNo", parentMerchantNo);

        //---修改分账商户 start
        //params.put("merchantNo", merchantNo);
        params.put("merchantNo",receiptMerchantNo);
        //---修改分账商户 end

        params.put("orderId", orderId);
        params.put("orderAmount", orderAmount);
        params.put("notifyUrl", notifyUrl);
        params.put("goodsParamExt", goodsParamExt);
        params.put("fundProcessType", "DELAY_SETTLE");
        String uri = YeepayService.getUrl(YeepayService.TRADEORDER_URL);
        Map<String, String> results = YOPTODADAO.requestYOP(params, uri, YeepayService.TRADEORDER, YeepayService.TRADEORDER_HMAC);
        String error = results.get("error");
        if (error != null) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, error));
        }
        //获取token
        String YOPtoken = results.get("token");
        String uniqueOrderNo = results.get("uniqueOrderNo");
        //获取当前交易时间戳
        String timestamp = DateUtils.getCurrentTimeStamp();
        //1微信，2支付宝，3银联
        String directPayType = "";
/*       if(orderPO.getPayMethod()==1){
            directPayType="WX";
        }else if(orderPO.getPayMethod()==2){
            directPayType="ZFB";
        }else{
            directPayType="NC";
        }*/
        //用户标识
        String userNo = orderPO.getUserId().toString();
        //用户标识类型
        String userType = "USER_ID";

        String ext = "{\"appId\":\"\",\"openId\":\"\",\"clientId\":\"\"}";

        Map<String, String> param = new HashMap<String, String>();
        param.put("parentMerchantNo", parentMerchantNo);
        param.put("merchantNo", parentMerchantNo);
        param.put("token", YOPtoken);
        param.put("timestamp", timestamp);
        param.put("directPayType", directPayType);
        param.put("cardType", "");
        param.put("userNo", userNo);
        param.put("userType", userType);
        param.put("ext", ext);

        //持久化订单信息
        orderPO.setOrderId(orderId);
        orderPO.setMarketId(21);
        orderPO.setOrderStatus(0);
        orderPO.setChannelSequence(uniqueOrderNo);
        orderPO.setDescription("向 " + shop.getShopName() + " 支付： " + orderPO.getAmount() + " 元！");
        int saveOrderResult = iOrderMapper.save(orderPO);
        if (saveOrderResult != 1) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "持久化订单信息失败！"));
        }
        //只有线上支付的时候才有关联商品
        if(payMode==1) {
            //持久化订单关联商品
            OrderDetailsPO orderDetailsPO = new OrderDetailsPO();
            orderDetailsPO.setGoodsId(goodsId);
            orderDetailsPO.setOrderId(orderPO.getId());
            orderDetailsPO.setQuantity(quantity);

            int saveOrderDetailsResult = iOrderDetailsMapper.save(orderDetailsPO);
            if (saveOrderDetailsResult != 1) {
                return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "持久化订单中间表信息失败！"));
            }
        }

        try {
            String url = YOPTODADAO.getUrl(param);
            System.out.println(url);
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, url));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "系统未知错误，请联系管理员！"));
    }

    @Override
    public Object generateOrder(OrderPO orderPO, String token, HttpServletRequest request, HttpServletResponse response) {
        YOPGeneratePayOrderParm yopGeneratePayOrderParm = new YOPGeneratePayOrderParm();
        //根据token获取用户信息
        UserAccount userAccount = mapper.findUserByToken(token);
        orderPO.setUserId(userAccount.getUserId());
        yopGeneratePayOrderParm.setUserNo(userAccount.getUserId().toString());
        //根据商铺ID查询商铺在易宝的子商户号
        Shop shop = iShopMapper.findByShopId(orderPO.getShopId());
        orderPO.setChild_merchant_no(shop.getSubMerchantNo());
        //父商编
        String parentMerchantNo = YeepayService.getParentMerchantNo();
        yopGeneratePayOrderParm.setMerchantNo(parentMerchantNo);
        //收单商户商编
        String merchantNo = orderPO.getChild_merchant_no();
        //格式化时间精确到毫秒
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //拼接商户订单号
        String orderId = "DADAO_" + orderPO.getUserId() + formatter.format(new Date());
        //订单总金额
        String orderAmount = orderPO.getAmount().toString();
        //服务器回调地址
        String notifyUrl = YeepayService.getNotifyUrl();
        //产品名称和描述
        String goodsName = shop.getShopName();
        String goodsDesc = shop.getShopDescription();
        String goodsParamExt = "{\"goodsName\":\"" + goodsName + "\",\"goodsDesc\":\"" + goodsDesc + "\"}";

        //请求数据存储
        Map<String, String> requestBasicParams = new HashMap<String, String>();
        requestBasicParams.put("parentMerchantNo", parentMerchantNo);
        requestBasicParams.put("merchantNo", merchantNo);
        requestBasicParams.put("notifyUrl", notifyUrl);

        Map<String, String> requesParams = new HashMap<>();
        requesParams.put("parentMerchantNo", parentMerchantNo);
        requesParams.put("merchantNo", merchantNo);
        requesParams.put("orderId", orderId);
        requesParams.put("orderAmount", orderAmount);
        requesParams.put("notifyUrl", notifyUrl);
        requesParams.put("goodsParamExt", goodsParamExt);
        requesParams.put("fundProcessType", "DELAY_SETTLE");

       /* //填充信息
        Map<String, String> requesParams = new HashMap<String, String>();
        requesParams.putAll(requestBasicParams);
        requesParams.put("orderId", orderId);
        requesParams.put("orderAmount", orderAmount);
        requesParams.put("timeoutExpress", "");
        requesParams.put("requestDate", "");
        requesParams.put("paymentParamExt", "");
        requesParams.put("riskParamExt", "");
        requesParams.put("goodsParamExt", goodsParamExt);*/

        //获取YOP返回信息
        //String[] signs = {"parentMerchantNo", "merchantNo", "orderId", "orderAmount", "timeoutExpress", "requestDate", "redirectUrl", "notifyUrl", "goodsParamExt", "paymentParamExt", "memo", "riskParamExt"};
        String uri = YeepayService.getUrl(YeepayService.TRADEORDER_URL);
        Map<String, String> results = YOPTODADAO.requestYOP(requesParams, uri, YeepayService.TRADEORDER,YeepayService.TRADEORDER_HMAC);

        String error = results.get("error");
        if (error != null) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, error));
        }
        //获取token
        String YOPtoken = results.get("token");
        yopGeneratePayOrderParm.setToken(YOPtoken);

        String uniqueOrderNo = results.get("uniqueOrderNo");
        //获取当前交易时间戳
        String timestamp = DateUtils.getCurrentTimeStamp();
        System.out.println("timestamp:"+timestamp);
        yopGeneratePayOrderParm.setTimeStamp(timestamp);
        //1微信，2支付宝，3银联
        String directPayType = "";
/*       if(orderPO.getPayMethod()==1){
            directPayType="WX";
        }else if(orderPO.getPayMethod()==2){
            directPayType="ZFB";
        }else{
            directPayType="NC";
        }*/
        //用户标识
        String userNo = orderPO.getUserId().toString();
        //用户标识类型
        String userType = "USER_ID";

        String ext = "{\"appId\":\"\",\"openId\":\"\",\"clientId\":\"\"}";

        //拼装签名字符串
        StringBuilder builder = new StringBuilder()
                .append("merchantNo=").append(parentMerchantNo)
                .append("&token=").append(YOPtoken)
                .append("&timestamp=").append(timestamp)
                //.append("&directPayType=").append(directPayType)
                .append("&directPayType=").append("NC")
                .append("&cardType=").append("")
                .append("&userNo=").append(userNo)
                .append("&userType=").append(userType);

        //持久化订单信息

        orderPO.setOrderId(orderId);
        orderPO.setMarketId(21);
        orderPO.setOrderStatus(0);
        orderPO.setChannelSequence(uniqueOrderNo);
        orderPO.setDescription("向 " + shop.getShopName() + " 支付： " + orderPO.getAmount() + " 元！");
        int saveOrderResult = iOrderMapper.save(orderPO);
        if (saveOrderResult != 1) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "持久化订单信息失败！"));
        }

        try {
            //生成签名
            String sign=YeepayService.getSign(builder.toString());
            System.out.println(sign);
            yopGeneratePayOrderParm.setSign(sign);
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, yopGeneratePayOrderParm));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "系统未知错误，请联系管理员！"));
    }
    //订单批量查询
    @Override
    public OrderQuery multiOrderQuery(OrderQuery orderQuery) {
        OrderQuery query = new OrderQuery();
        //获取所有要传的参数转为Map
        Map<String,String> param = new HashMap<>();
        //主商户编号
        param.put("parentMerchantNo",orderQuery.getParentMerchantNo());
        //商户编号
        param.put("merchantNo",orderQuery.getMerchantNo());
        //请求开始时间
        param.put("requestDateBegin",orderQuery.getRequestDateBegin());
        //请求结束时间
        param.put("requestDateEnd",orderQuery.getRequestDateEnd());
        //订单状态
        param.put("status",orderQuery.getStatus());
        //页码
        param.put("pageNo",orderQuery.getPageNo());
        //每页记录数
        param.put("pageSize",orderQuery.getPageSize());
        //子商户签名
        String multiOrderQueryURL = YeepayService.getUrl(YeepayService.MULTIORDERQUERY_URL);
        Map<String, String> jsonMap = YeepayService.requestYOP(param, multiOrderQueryURL, YeepayService.MULTIORDERQUERY, YeepayService.MULTIORDERQUERY_HMAC);
        //响应参数
        query.setCode(jsonMap.get("code"));
        query.setMessage(jsonMap.get("message"));
        query.setParentMerchantNo(jsonMap.get("parentMerchantNo"));
        query.setMerchantNo(jsonMap.get("merchantNo"));
        query.setTotalRecords(jsonMap.get("totalRecords"));
        query.setPageNo(jsonMap.get("pageNo"));
        query.setPageSize(jsonMap.get("pageSize"));

        List<Order> listOrder = new ArrayList<Order>();

        //处理json中的订单
        if(jsonMap.get("orderList") != null){
            String orderListString = jsonMap.get("orderList");
            orderListString = orderListString.substring(1, orderListString.length()-1);
            String[] order = orderListString.split("},");

            for (int i = 0; i < order.length; i++) {
                Order order1 = new Order();
                if(i != order.length-1)
                    order[i] = order[i] + "}";
                System.out.println(order[i]);
                Map<String, Object> orderMap  = new HashMap<>();
                orderMap = JSON.parseObject(order[i],
                        new TypeReference<TreeMap<String, Object>>() {});
                String goodsParamExt = (String) orderMap.get("goodsParamExt");
                Map<String, String> goodsParamExtMap = JSON.parseObject(goodsParamExt,
                        new TypeReference<TreeMap<String, String>>() {});
                orderMap.put("goodsParamExt", goodsParamExtMap);

               order1.setMerchantNo(orderMap.get("merchantNo").toString());
               order1.setOrderId(orderMap.get("orderId").toString());
               order1.setUniqueOrderNo(orderMap.get("uniqueOrderNo").toString());
               order1.setStatus(orderMap.get("status").toString());
               order1.setOrderAmount(orderMap.get("orderAmount").toString());
               order1.setPayAmount(orderMap.get("payAmount").toString());
               order1.setMerchantFee(orderMap.get("merchantFee").toString());
               order1.setCustomerFee(orderMap.get("customerFee").toString());
               order1.setRequestDate(orderMap.get("requestDate").toString());
               order1.setPaySuccessDate(orderMap.get("paySuccessDate").toString());
               order1.setGoodsName(goodsParamExtMap.get("goodsName"));
               order1.setGoodsDesc(goodsParamExtMap.get("goodsDesc"));
               order1.setFundProcessType(orderMap.get("fundProcessType").toString());
                listOrder.add(order1);
            }

        }
        query.setOrderList(listOrder);
        return query;

    }
}
