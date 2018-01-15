package com.dadao.activities.user.activity;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.user.mapper.UserMapper;
import com.dadao.user.mapper.entity.CurrentIntegral;
import com.dadao.user.mapper.entity.UserDeal;
import com.dadao.user.mapper.entity.UserDealRecord;
import com.dadao.utils.DateUtils;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * created by GUOYU 2017/11/08
 */
@Repository
public class UserDealActivity {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;
    @Autowired
    private IOrderMapper iOrderMapper;
    @Autowired
    private IShopMapper iShopMapper;
    @Autowired
    private IUserTransactionRecodMapper userTransactionRecodMapper;

    /**
     * 根据token值获取相应数据
     * @param
     * @return
     */
    public UserDealRecord userIntegral(UserIntegralRecordingPO userIntegralRecordingPO,String token,Integer pageNum){
        userIntegralRecordingPO.setBeginIndex((pageNum - 1) * userIntegralRecordingPO.getPageSize());
        UserAccount userAccount=new UserAccount();
        userAccount.setToken(token);
        UserDealRecord userDealRecord = new UserDealRecord();
        //根据token值查询userId
        Long userId =  userMapper.findByToken(userAccount).getUserId();
        userIntegralRecordingPO.setUserId(userId);
        //用户获得总积分
        userDealRecord.setTotalInteger(iUserIntegralMapper.findByUserID(userId).getIntegral());
        //用户当日获得积分
        userDealRecord.setCurrentDayInteger(iUserIntegralRecordingMapper.findTodayIntegral(userId));
        //用户当月交易完成数量
        userDealRecord.setCurrentMonthCount(userTransactionRecodMapper.findMonthTransactionCount(userId));
        //查询用户积分记录
        Long totalSize = iUserIntegralRecordingMapper.findCount(userIntegralRecordingPO);
        List list=iUserIntegralRecordingMapper.findByPage(userIntegralRecordingPO);
        Long totalPage = totalSize % userIntegralRecordingPO.getPageSize() == 0 ? totalSize / userIntegralRecordingPO.getPageSize() : totalSize / userIntegralRecordingPO.getPageSize() + 1;
        QueryResult queryResult = new QueryResult();
        queryResult.setTotalSize(totalSize);
        queryResult.setTotalPage(totalPage);
        queryResult.setList(list);
        queryResult.setPageNum(pageNum);
        userDealRecord.setUserDeals(queryResult);

        return userDealRecord;
    }

    /**
     * 获取当月积分使用情况 0表示当前月 1表示前第一个月 2表示前第二个月
     * @param status
     * @return
     */
    public CurrentIntegral integral(Integer status,String token){
        CurrentIntegral currentIntegral = new CurrentIntegral();
        String[] str = new String[4];
        //处理前三个月的时间 会处理四个时间段【前第二个月-前第一个月】【前第一个月 - 当前月】【当前月-下个月】
        for (int i = 0; i < 4; i++) {
            str[i] = DateUtils.date(i - 2);
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setToken(token);
        //根据token值查询userId
        Long userId =  userMapper.findByToken(userAccount).getUserId();

        if(status == 0){
            //获取当月积分
            currentIntegral.setCurrentMonthUseIntegerl(this.currentMonthUserIntegral(str[2],str[3],userId));
            //当月获得的积分
            currentIntegral.setCurrentMonthIntegral(this.currentMonthIntegral(str[2],str[3],userId));
        }
        if(status == 1){
            currentIntegral.setCurrentMonthUseIntegerl(this.currentMonthUserIntegral(str[1],str[2],userId));
            currentIntegral.setCurrentMonthIntegral(this.currentMonthIntegral(str[1],str[2],userId));
        }
        if(status == 2){
            currentIntegral.setCurrentMonthUseIntegerl( this.currentMonthUserIntegral(str[0],str[1],userId));
            currentIntegral.setCurrentMonthIntegral(this.currentMonthIntegral(str[0],str[1],userId));
        }

        return currentIntegral;
    }
    //当月使用掉的积分
    private BigDecimal currentMonthUserIntegral(String start,String end,Long userId){

        UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
        userIntegralRecordingPO.setIntegralStartTime(start);
        userIntegralRecordingPO.setIntegralEndTime(end);
        userIntegralRecordingPO.setUserId(userId);
        userIntegralRecordingPO.setStatus(2);
        //计算用户使用掉的积分
        List<UserIntegralRecordingPO> list = iUserIntegralRecordingMapper.ThreeMonthBefore(userIntegralRecordingPO);
        double sum = 0;
        for(int i = 0; i < list.size(); i++){
            sum = list.get(i).getIntegral().doubleValue();
        }
        BigDecimal bigDecimal = new BigDecimal(sum);
        return bigDecimal;
    }
    //当月获得的积分
    private BigDecimal currentMonthIntegral(String start,String end,Long userId){

        UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
        //方向增加
        userIntegralRecordingPO.setDirection(0);
        userIntegralRecordingPO.setUserId(userId);
        userIntegralRecordingPO.setIntegralStartTime(start);
        userIntegralRecordingPO.setIntegralEndTime(end);
        //获取指定时间指定用户id且发生方向为增加的数据集合
        List<UserIntegralRecordingPO> list = iUserIntegralRecordingMapper.ThreeMonthBefore(userIntegralRecordingPO);
        double sum = 0;
        for(int i = 0;i < list.size();i++){
            userIntegralRecordingPO.setDirection(1);
            userIntegralRecordingPO.setObjectId(list.get(i).getObjectId());
            //如果没有存在相同object 相同userId的数据则表示没有退款
            if(iUserIntegralRecordingMapper.findUserIntegralRecord(userIntegralRecordingPO) == null){
                sum += list.get(i).getIntegral().doubleValue();
            }
        }
        BigDecimal bigDecimal = new BigDecimal(sum);
        return bigDecimal;

    }
  //计算当前月的消费记录
  private List<UserDeal> userDeal(String start,String end,Long userId){

      List<UserDeal> list = new ArrayList<UserDeal>() ;

      UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
      userIntegralRecordingPO.setUserId(userId);
      userIntegralRecordingPO.setIntegralStartTime(start);
      userIntegralRecordingPO.setIntegralEndTime(end);


      //根据条件查询用户积分记录表
      List<UserIntegralRecordingPO> lists = iUserIntegralRecordingMapper.ThreeMonthBefore(userIntegralRecordingPO);
      for(int i = 0;i < lists.size();i++){
          UserDeal userDeal = new UserDeal();

          if(lists.get(i).getStatus() == 0 || lists.get(i).getStatus() == 1){
              //根据ObjectId查询单个订单
              OrderPO orderPO = (OrderPO)iOrderMapper.findById(lists.get(i).getObjectId());
              Long shopId = orderPO.getShopId();
              Shop shop = (Shop) iShopMapper.findById(shopId);
              //'状态‘0.交易、1.退款、2.返利、3.商户积分’',
              userDeal.setStatus(lists.get(i).getStatus());
              //商品积分
              userDeal.setProductIntegral(lists.get(i).getIntegral());
              //商品名称
              userDeal.setProductName(shop.getShopName());
              //发生方向
              userDeal.setDirection(lists.get(i).getDirection());
              //商品交易时间
              userDeal.setCreateTime(lists.get(i).getCreateTime());
              //商品价格
              userDeal.setProductPrice(orderPO.getAmount());
              list.add(userDeal);
          }
      }

      return list;
  }

}
