package com.dadao.shop.service.impl;

import com.dadao.activities.pub.activity.PubActivity;
import com.dadao.activities.shop.activity.ShopActivity;
import com.dadao.category.entity.Category;
import com.dadao.common.service.impl.BaseService;
import com.dadao.shop.entity.RecommendPosition;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.mapper.IRecommendShopMapper;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
@Service
public class ShopServiceImpl extends BaseService implements ShopService {

    @Autowired
    private IShopMapper shopMapper;
    @Autowired
    private IRecommendShopMapper recommendShopMapper;

    @Autowired
    private ShopActivity shopActivity;


    public void setBaseMapper(IShopMapper shopMapper) {
        super.setBaseMapper(shopMapper);
    }

    public QueryResult findByPage(RecommendPosition recommendPosition) {
        return shopActivity.findByPage(recommendPosition);
    }

    /**
     * 获取附近商铺
     *
     * @param shop
     * @return
     */
    public QueryResult getNearShops(Shop shop) {
        QueryResult queryResult = new QueryResult();
        Long totalSize = this.shopMapper.countNear(shop);
        List list = this.shopMapper.listNear(shop);
        Long totalPage = totalSize % shop.getPageSize() == 0 ? totalSize / shop.getPageSize() : totalSize / shop.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(this.dealData(list));
        return queryResult;
    }

    public Result findShopById(Integer shopId) {
        ResultCode result = null;
        com.dadao.shop.entity.vo.Shop shop = null;
        if (shopId == null) {
            return new Result(ResultCode.ENTITY_ID_NULL, "");
        }
        shop = shopActivity.findById(shopId);
        if (shop == null) {
            return new Result(ResultCode.SYS_SUCCESS, "查无此店铺");
        } else {
            return new Result(ResultCode.SYS_SUCCESS, shop);
        }

    }

    /**
     * 查询推荐店铺
     *
     * @return
     */
    public QueryResult listRecommendShop(Shop shop) {
        QueryResult queryResult = new QueryResult();
        List list = recommendShopMapper.listRecommendShop(shop);
        Long totalSize = new Long(list.size());

        Long totalPage = totalSize % shop.getPageSize() == 0 ? totalSize / shop.getPageSize() : totalSize / shop.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 条件分页查询店铺,基于用户当前位置
     *
     * @param shopPO
     * @return
     */
    public Result getShopByConditions(ShopPO shopPO, Integer pageNum, Integer type) {
        return new Result(ResultCode.SYS_SUCCESS, shopActivity.findByShop(shopPO, pageNum, type));
    }

    /**
     * 获得客户端启动推荐图
     *
     * @return
     */
    public Result findStartupShop() {
        return shopActivity.findStartupShop();
    }

    public Result findShopsByRankType(String longitude, String latitude, Long categoryId, Integer rankType, Integer pageNum, Integer pageSize) {
        return shopActivity.findShopsByRankType(longitude, latitude, categoryId, rankType, pageNum, pageSize);
    }

    /**
     * 查询商户所有店铺
     * @param shopPO
     * @return
     */
    @Override
    public List<ShopPO> list(ShopPO shopPO) {
        return shopActivity.list(shopPO);
    }

    /**
     * 处理distance显示问题
     *
     * @param shopName
     * @param distance
     * @return
     */
    private String getAddressDistance(String shopName, String distance) {
        String addressDistance = "";
        Integer index = null;
        //英文括号
        if (shopName.indexOf("（") == -1 && shopName.indexOf("(") != -1) {
            index = shopName.indexOf("(") + 1;
        }
        //中文括号
        if (shopName.indexOf("(") == -1 && shopName.indexOf("（") != -1) {
            index = shopName.indexOf("（") + 1;
        }
        //无括号
//        if (index == null) {
//            addressDistance = "该店 ";
//        } else {
//            addressDistance = shopName.substring(index, shopName.length() - 1) + " ";
//        }
        Double dis = (Double.parseDouble(distance) * 1000);
        return dis.intValue() + "m";
    }

    private List dealData(List<com.dadao.shop.entity.vo.Shop> list) {
        if (list != null && list.size() != 0) {
            for (com.dadao.shop.entity.vo.Shop shop : list) {
                //处理距离
                shop.setDistance(this.getAddressDistance(shop.getShopName(), shop.getDistance()));
            }
        }
        return list;
    }
}
