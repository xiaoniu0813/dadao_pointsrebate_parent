package com.dadao.activities.shop.activity;

import com.dadao.shop.entity.RankShop;
import com.dadao.shop.entity.RecommendPosition;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.entity.vo.Shop;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.shop.mapper.RecommendPositionMapper;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
@Repository
public class ShopActivity {

    @Autowired
    private RecommendPositionMapper recommendPositionMapper;

    @Autowired
    private IShopMapper iShopMapper;

    /**
     * @Author: yangrui
     * @Description: 分页
     * @Date: 下午2:35 2017/7/13
     */
    public QueryResult findByPage(RecommendPosition recommendPosition) {
        QueryResult queryResult = new QueryResult();
        Long totalSize = recommendPositionMapper.findCount(recommendPosition);
        List list = recommendPositionMapper.findByPage(recommendPosition);
        Long totalPage = totalSize % recommendPosition.getPageSize() == 0 ? totalSize / recommendPosition.getPageSize() : totalSize / recommendPosition.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 获得客户端启动推荐图
     * @return
     */
    public Result findStartupShop(){
        return new Result(ResultCode.SYS_SUCCESS,recommendPositionMapper.findStartupShop());
    }

    public Shop findById(Integer shopId){
        return (Shop) iShopMapper.getById(shopId);
    }

    /**
     * 条件分页查询商铺,距离算法参考findByPage
     * @param shopPO
     * @return
     */
    public QueryResult findByShop(ShopPO shopPO, Integer pageNum, Integer type){
        shopPO.setBeginIndex(Long.valueOf((pageNum - 1) * shopPO.getPageSize()));
        //组装查询条件
        HashMap hashMap = DaDaoUtil.objToHash(shopPO);
        Integer isNewShop = null;
        Integer isOnTime = null;
        //1.拆分tags
        if(shopPO.getTags() != null && !shopPO.getTags().trim().equals("")){
            String[] tags = shopPO.getTags().split(",|，");
            //2.提取timeTag(新店，营业中)
            for (int i = 0; i < tags.length; i++){
                if(tags[i] != null && tags[i].equals("新店")){
                    isNewShop = 1;
                    tags[i] = null;
                }
                if (tags[i] != null && tags[i].equals("营业中")){
                    isOnTime = 1;
                    tags[i] = null;
                }
            }
            hashMap.put("tags",tags);
        }
        hashMap.put("type",type);
        hashMap.put("isNewShop",isNewShop);
        hashMap.put("isOnTime", isOnTime);
        Long totalSize = iShopMapper.countNearByShop(hashMap);
        List data = iShopMapper.listNearByShop(hashMap);
        Long totalPage = totalSize % shopPO.getPageSize() == 0 ? totalSize / shopPO.getPageSize() : totalSize / shopPO.getPageSize() + 1;
        //组装结果集
        QueryResult queryResult = new QueryResult();
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(this.dealData(data));
        queryResult.setPageNum(pageNum);
        return queryResult;
    }

    private String getAddressDistance(String shopName, String distance){
        String addressDistance = "";
        Integer index = null;
        //英文括号
        if(shopName.indexOf("（") == -1 && shopName.indexOf("(") != -1){
            index = shopName.indexOf("(") + 1;
        }
        //中文括号
        if(shopName.indexOf("(") == -1 && shopName.indexOf("（") != -1){
            index = shopName.indexOf("（") + 1;
        }
//        //无括号
//        if(index == null){
//            addressDistance = "该店 ";
//        }else{
//            addressDistance =  shopName.substring(index, shopName.length() - 1) + " ";
//        }
//        Double dis = (Double.parseDouble(distance) * 1000);
//        return addressDistance += "距您<" + dis.intValue() + "m";
        Double dis = (Double.parseDouble(distance) * 1000);
        return dis.intValue() + "m";
    }

    private List dealData(List<Shop> list){
        if(list != null && list.size() != 0){
            for (Shop shop: list) {
                //处理距离
                shop.setDistance(this.getAddressDistance(shop.getShopName(),shop.getDistance()));
            }
        }
        return list;
    }


    public Result findShopsByRankType(String longitude, String latitude, Long categoryId, Integer rankType, Integer pageNum, Integer pageSize){
        //1、检查并处理输入参数
        if(categoryId == null){
            return new Result(ResultCode.ENTITY_ID_NULL,"商铺一级分类categoryId为空");
        }
        if(rankType == null || rankType != 1 && rankType != 2){
            return new Result(ResultCode.INPUT_PARAMS_FAIL,"排序类型rankType应该为“1：旺铺推荐”或“2：暖心福利”");
        }
        if(pageNum == null || pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        //2、调用接口，并实现分页
        long totalSize = iShopMapper.countShopsByRankType(longitude, latitude,categoryId,rankType);
        Integer beginIndex = (pageNum - 1) * pageSize;
        long totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        List<RankShop> list = iShopMapper.findShopsByRankType(longitude, latitude,categoryId,rankType,beginIndex,pageSize);

        QueryResult queryResult = new QueryResult();
        queryResult.setTotalSize(totalSize);
        queryResult.setPageNum(pageNum);
        queryResult.setTotalPage(totalPage);
        queryResult.setList(list);
        return new Result(ResultCode.SYS_SUCCESS,queryResult);
    }
    //查询商户所有店铺
    public List<ShopPO> list(ShopPO shopPO){
        return iShopMapper.list(shopPO);
    }

}
