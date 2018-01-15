package com.dadao.shop.activity;

import com.dadao.cashback.activity.CashbackActivity;
import com.dadao.cashplan.entity.TUserEffectiveIntegral;
import com.dadao.shop.entity.ShopLastIntegralRecordingPO;
import com.dadao.shop.mapper.ShopLastIntegralRecordingMapper;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 累加商户积分
 * Created by guoyu 2017/10/26
 */
@Repository
public class CalculatingShopIntegral {

    @Autowired
    private UserIntegralRecordingMapper userIntegralRecordingMapper;
    @Autowired
    private ShopLastIntegralRecordingMapper shopLastIntegralRecordingMapper;
    @Autowired
    private CalculatingShopIntegral calculatingShopIntegral;
    @Autowired
    private CashbackActivity cashbackActivity;

    //处理达到积分上线的用户
    @Transactional
    public void acceptReachIntegralUser(TUserEffectiveIntegral tUserEffectiveIntegral) {
        //传入一个userId 1000
        Long userId = tUserEffectiveIntegral.getUserId();
        //传入积分上限 300
        BigDecimal intervalsUnit = tUserEffectiveIntegral.getCurrentIntegralUpper();
        //传入市场等级marketId
        Long marketId = tUserEffectiveIntegral.getMarketId();

        //根据市场id和用户id判断是否之前用户达到过积分上限
        ShopLastIntegralRecordingPO shopLastIntegralRecordingPO = new ShopLastIntegralRecordingPO();
        //设置用户id
        shopLastIntegralRecordingPO.setUserId(userId);
        //设置市场等级
        shopLastIntegralRecordingPO.setMarketId(marketId);
        //查询所有集合
        List<ShopLastIntegralRecordingPO> shopLastIntegralRecording = shopLastIntegralRecordingMapper.findByEntity(shopLastIntegralRecordingPO);


        //如果长度等于0 说明之前该用户未达到过积分上限
        if (shopLastIntegralRecording.size() == 0) {

            //根据条件查询所有单笔消费记录（积分记录表）
            UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
            userIntegralRecordingPO.setMarketId(marketId);
            userIntegralRecordingPO.setUserId(userId);
            List<UserIntegralRecordingPO> listUserIntegralRecordingPO = userIntegralRecordingMapper.findByEntity(userIntegralRecordingPO);
            //记录总积分
            BigDecimal sumIntegral = new BigDecimal(0);
            //test(ShopLastIntegralRecordingPO shopLastIntegralRecordingPO,UserIntegralRecordingPO userIntegralRecordingPO, List<UserIntegralRecordingPO> listAll,BigDecimal integral,TUserEffectiveIntegral tUserEffectiveIntegral){

            cashbackActivity.countIntegral(shopLastIntegralRecordingPO, userIntegralRecordingPO, listUserIntegralRecordingPO, sumIntegral, tUserEffectiveIntegral);
            //如果长度大于0 说明之前该用户达到过积分上限
        } else {
            //定义上次累加后的终止时间
            Date endTime = null;
            //上次累加后积分剩余
            BigDecimal endIntegral = new BigDecimal(0);

            //根据传入的用户id userId 市场等级 marketId  计算积分状态status
            //先查询未计算的部分 status 0
            shopLastIntegralRecordingPO.setStatus(0);
            ShopLastIntegralRecordingPO shopLastIntegralRecordingStatus0 = (ShopLastIntegralRecordingPO) shopLastIntegralRecordingMapper.findShopIntegralRecording(shopLastIntegralRecordingPO);
            //如果最后一条记录的积分表中的未计算记录不为null则使用最后一条记录（未计算）的时间（表示累加积分大于积分上限）
            if (shopLastIntegralRecordingStatus0 != null) {

                //把上次最后一次未计算部分的时间赋值给本次的初始时间
                endTime = shopLastIntegralRecordingStatus0.getCreateTime();
                //上次未计算部分的积分
                endIntegral = endIntegral.add(shopLastIntegralRecordingStatus0.getIntegral());
                //将使用过的修改为已计算
                shopLastIntegralRecordingPO.setStatus(1);
                shopLastIntegralRecordingPO.setId(shopLastIntegralRecordingStatus0.getId());
                int a = shopLastIntegralRecordingMapper.update(shopLastIntegralRecordingPO);
                if (a != 1) {
                    throw new RuntimeException("更新最后一条记录状态失败");
                }

                //如果最后一条记录的积分表中的未计算记录为null 使用使用最后一条记录（已计算）的时间（表示累加积分刚好等于积分上限）
            } else {
                //获取上次(刚好满足积分上限的记录)
                shopLastIntegralRecordingPO.setStatus(1);
                ShopLastIntegralRecordingPO shopLastIntegralRecordingStatus1 = (ShopLastIntegralRecordingPO) shopLastIntegralRecordingMapper.findShopIntegralRecording(shopLastIntegralRecordingPO);
                //把上次最后一笔（刚好满足积分上限）记录 的时间赋值给本次初始时间 上次未计算部分的积分为0
                endTime = shopLastIntegralRecordingStatus1.getCreateTime();

            }
            //根据时间查询当次未经计算积分的积分记录集合
            UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
            userIntegralRecordingPO.setMarketId(marketId);
            userIntegralRecordingPO.setUserId(userId);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userIntegralRecordingPO.setIntegralStartTime(formatter.format(endTime));
            List<UserIntegralRecordingPO> listUserIntegralRecordingPO = userIntegralRecordingMapper.findByEntity(userIntegralRecordingPO);

            //记录总积分
            BigDecimal sumIntegral = new BigDecimal(0);
            sumIntegral = sumIntegral.add(endIntegral);

            shopLastIntegralRecordingPO.setId(null);
            cashbackActivity.countIntegral(shopLastIntegralRecordingPO, userIntegralRecordingPO, listUserIntegralRecordingPO, sumIntegral, tUserEffectiveIntegral);


        }

    }

}
