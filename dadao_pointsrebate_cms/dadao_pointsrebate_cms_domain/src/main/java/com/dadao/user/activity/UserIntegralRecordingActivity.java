package com.dadao.user.activity;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.mapper.CashbackRecordMapper;
import com.dadao.market.entity.MarketGradePO;
import com.dadao.market.mapper.MarketGradeMapper;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.OrderMapper;
import com.dadao.user.entity.BusinessCashIntegral;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.UserIntegralRecordingMapper;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class UserIntegralRecordingActivity {

    @Autowired
    private UserIntegralRecordingMapper userIntegralRecordingMapper;
    @Autowired
    private MarketGradeMapper marketGradeMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CashbackRecordMapper cashbackRecordMapper;
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;

    /**
     * 分页查询用户积分信息
     *
     * @param userIntegralRecordingPO
     * @return
     */
    public QueryResult findUserIntegralRecordingByPage(UserIntegralRecordingPO userIntegralRecordingPO) {
        QueryResult queryResult = new QueryResult();
        //默认为第一页
        if (userIntegralRecordingPO.getPageNum() == null) {
            userIntegralRecordingPO.setPageNum(1);
        }
        //默认大小为10
        if (userIntegralRecordingPO.getPageSize() == null) {
            userIntegralRecordingPO.setPageSize(10);
        }
        //默认市场等级为一级
        if (userIntegralRecordingPO.getMarketGrade() == null) {
            userIntegralRecordingPO.setMarketGrade(1);
        }
        //数据分页查询起始
        userIntegralRecordingPO.setBeginIndex((userIntegralRecordingPO.getPageNum() - 1) * userIntegralRecordingPO.getPageSize());
        //根据传入的市场等级 在market表中查询对应的marketId
        MarketGradePO marketGradePO = (MarketGradePO) marketGradeMapper.findById(userIntegralRecordingPO.getMarketGrade());
        //传入市场登记对应的marketId
        userIntegralRecordingPO.setMarketId(marketGradePO.getMarketId());
        //当前页
        queryResult.setPageNum(userIntegralRecordingPO.getPageNum());
        //根据条件查询出需要分页的总记录数
        long totalSize = userIntegralRecordingMapper.findCount(userIntegralRecordingPO);
        queryResult.setTotalSize(totalSize);
        //根据查询的总记录数计算总页数
        long totalPage = totalSize % userIntegralRecordingPO.getPageSize() == 0 ? totalSize / userIntegralRecordingPO.getPageSize() : totalSize / userIntegralRecordingPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        //查询分页了的数据
        List<UserIntegralRecordingPO> list = userIntegralRecordingMapper.findByPage(userIntegralRecordingPO);
        //將前台数据放在分页的数据中
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMarketGrade(userIntegralRecordingPO.getMarketGrade());
            list.get(i).setPageNum(userIntegralRecordingPO.getPageNum());
            list.get(i).setBeginIndex(userIntegralRecordingPO.getBeginIndex());
            list.get(i).setIntegralStartTime(userIntegralRecordingPO.getIntegralStartTime());
            list.get(i).setIntegralEndTime(userIntegralRecordingPO.getIntegralEndTime());
            list.get(i).setPageSize(userIntegralRecordingPO.getPageSize());
        }
        queryResult.setList(list);
        return queryResult;
    }

    public List<UserIntegralRecordingPO> exportUserIntegralRecordingPO(UserIntegralRecordingPO userIntegralRecordingPO) {
        //根据传入的市场等级 在market表中查询对应的marketId
        MarketGradePO marketGradePO = (MarketGradePO) marketGradeMapper.findById(userIntegralRecordingPO.getMarketGrade());
        //传入市场登记对应的marketId
        userIntegralRecordingPO.setMarketId(marketGradePO.getMarketId());
        //查询满足条件的记录
        List<UserIntegralRecordingPO> list = userIntegralRecordingMapper.findByEntity(userIntegralRecordingPO);
        //将市场等级保存
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMarketGrade(userIntegralRecordingPO.getMarketGrade());
        }
        return list;
    }

    //根据传进来的direction (发生方向(0增加、1减少)),objectId(对象ID(如果是增加积分该字段就是订单表ID，如果是减少积分该字段就是返现记录表ID))
    public Object objectDetails(int direction, long objectId) {
        if (direction == 0) {
            OrderPO orderPO = (OrderPO) orderMapper.findById(objectId);
            return orderPO;
        }
        if (direction == 1) {
            CashbackRecordPO cashbackRecordPO = (CashbackRecordPO) cashbackRecordMapper.findById(objectId);
            return cashbackRecordPO;
        }
        return null;
    }

    /**
     * 查询商户积分信息
     * @param pageNum 第几页
     * @param pageSize 页面大小
     * @param marketId 市場id
     * @param currentIntegralUpper  当前积分上限
     * @return  带有商户返利积分信息的处理结果
     */
    public QueryResult findBusinessesIntegral(Long marketId, BigDecimal currentIntegralUpper, Integer pageNum, Integer pageSize){
        QueryResult queryResult = new QueryResult();
        long totalSize = iUserIntegralMapper.findBusinessesIntegralCount(marketId, currentIntegralUpper);
        int beginIndex = (pageNum - 1) * pageSize;
        long totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        List<BusinessCashIntegral> list = iUserIntegralMapper.findBusinessesIntegral(marketId,currentIntegralUpper,beginIndex, pageSize);
        //调用calculateBonus方法处理奖金
        this.calculateBonus(list);
        queryResult.setTotalSize(totalSize);
        queryResult.setPageNum(pageNum);
        queryResult.setTotalPage(totalPage);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 处理奖金：实发奖金 = 积分 * 奖金倍数
     * @param data  商户返利积分列表
     */
    private void calculateBonus(List<BusinessCashIntegral> data){
        for (BusinessCashIntegral businessCashIntegral: data) {
            businessCashIntegral.setActualSalary(businessCashIntegral.getBusinessCashRate().multiply(businessCashIntegral.getIntegral()));
        }
    }

}
