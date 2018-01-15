package com.dadao.cashback.activity;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.capitalpool.entity.CapitalpoolVO;
import com.dadao.capitalpool.mapper.ICapitalpoolMapper;
import com.dadao.capitalpool.mapper.ICapitalpoolRecordMapper;
import com.dadao.cashback.entity.CashbackDetailsPO;
import com.dadao.cashback.entity.CashbackList;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.entity.ScanningCashbackLogPO;
import com.dadao.cashback.mapper.CashbackDetailsMapper;
import com.dadao.cashback.mapper.CashbackRecordMapper;
import com.dadao.cashback.mapper.IScanningCashbackLogMapper;
import com.dadao.market.mapper.MarketGradeMapper;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.mapper.*;
import com.dadao.utils.DateUtils;
import com.dadao.utils.POPage;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CashbackDetailsActivity {
    @Autowired
    private CashbackDetailsMapper cashbackDetailsMapper;
    @Autowired
    private CashbackRecordMapper cashbackRecordMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ICapitalpoolMapper capitalpoolMapper;
    @Autowired
    private ICapitalpoolRecordMapper capitalpoolRecordMapper;
    @Autowired
    private MarketGradeMapper marketGradeMapper;
    @Autowired
    private IUserIntegralMapper userIntegralMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private UserIntegralRecordingMapper userIntegralRecordingMapper;
    @Autowired
    private UserTransactionRecodMapper userTransactionRecodMapper;
    @Autowired
    private IScanningCashbackLogMapper iScanningCashbackLogMapper;
    @Autowired
    private CashbackActivity cashbackActivity;

    /**
     * 查询所有返现记录
     *
     * @param cashbackDetailsPO
     * @return
     */
    public POPage showCashbackDetailsByPage(CashbackDetailsPO cashbackDetailsPO) {
        if (cashbackDetailsPO.getPageNum() == null) {
            cashbackDetailsPO.setPageNum(1);
        }
        if (cashbackDetailsPO.getPageSize() == null) {
            cashbackDetailsPO.setPageSize(6);
        }
        if (cashbackDetailsPO.getStatus() == null) {
            cashbackDetailsPO.setStatus(0);
        }
        cashbackDetailsPO.setBeginIndex((cashbackDetailsPO.getPageNum() - 1) * cashbackDetailsPO.getPageSize());
        POPage poPage = new POPage();
        poPage.setBeginIndex((cashbackDetailsPO.getPageNum() - 1) * cashbackDetailsPO.getPageSize());
        poPage.setEndTime(cashbackDetailsPO.getEndTime());
        poPage.setStartTime(cashbackDetailsPO.getStartTime());
        poPage.setPageNum(cashbackDetailsPO.getPageNum());
        poPage.setPageSize(cashbackDetailsPO.getPageSize());
        poPage.setList(cashbackDetailsMapper.findByPage(cashbackDetailsPO));
        poPage.setCount(cashbackDetailsMapper.findCount(cashbackDetailsPO));
        poPage.setTotalPage(cashbackDetailsMapper.findCount(cashbackDetailsPO) % cashbackDetailsPO.getPageSize() == 0 ? cashbackDetailsMapper.findCount(cashbackDetailsPO) / cashbackDetailsPO.getPageSize() : (cashbackDetailsMapper.findCount(cashbackDetailsPO) / cashbackDetailsPO.getPageSize() + 1));
        poPage.setList(cashbackDetailsMapper.findByPage(cashbackDetailsPO));
        return poPage;
    }

    /**
     * 查询记录
     *
     * @param cashbackDetailsPO
     * @return
     */
    public List exportCashbackDetails(CashbackDetailsPO cashbackDetailsPO) {
        return cashbackDetailsMapper.findByEntity(cashbackDetailsPO);
    }

    //查询返现记录表单个记录
    public CashbackRecordPO findCashbackDetailsPOById(long id) {
        return (CashbackRecordPO) cashbackRecordMapper.findById(id);
    }

    //根据返现记录表查询本期返现详情
    public List showCashbackList(long recordId) {
        //根据recordId查询出返现记录表中与之对应的数据该集合包含不重复userId和recorId
        List<CashbackList> list = cashbackDetailsMapper.findEntityByUserId(recordId);
        //循环添加数据
        for (int i = 0; i < list.size(); i++) {
            //添加用户昵称
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(list.get(i).getUserId());
            //获取UserInfo对象
            UserInfo userInfo1 = (UserInfo) userInfoMapper.findUser(userInfo);
            //添加名称
            list.get(i).setNickname(userInfo1.getNickname());
            //添加手机号
            list.get(i).setPhone(userInfo1.getPhone());
            //返现份数即本期返现用户的返现次数
            list.get(i).setCashbackCount(cashbackDetailsMapper.findUserIdCount(list.get(i)));
            //返现金额
            list.get(i).setCashbackMoney(cashbackDetailsMapper.findUserCashBackMoney(list.get(i)));
        }
        return list;

    }

    /**
     * 扣除平台成本
     *
     * @param capitalpoolRecordPO
     * @return
     */
    @Transactional
    public ResultCode deductionPlatformCost(CapitalpoolRecordPO capitalpoolRecordPO) {
        try {
            //持久化资金池流水
            capitalpoolRecordPO.setStatus(2);
            capitalpoolRecordPO.setDescription("扣除平台运营成本");
            int saveResult = capitalpoolRecordMapper.save(capitalpoolRecordPO);
            if (saveResult == 1) {
                //查询处理当前资金池金额
                CapitalpoolVO capitalpoolVO = capitalpoolMapper.findByMarketId(capitalpoolRecordPO.getMarketId());
                CapitalpoolPO capitalpoolPO = new CapitalpoolPO();
                capitalpoolPO.setMoney(capitalpoolVO.getMoney().subtract(capitalpoolRecordPO.getMoney()));
                capitalpoolPO.setMarketId(capitalpoolVO.getMarketId());
                //更新资金池金额
                int updateResult = capitalpoolMapper.updateMoneyByMarketId(capitalpoolPO);
                if (updateResult == 1) {
                    return ResultCode.SYS_SUCCESS;
                } else {
                    return ResultCode.DEDUCTIONPLATFORMCOST_FAIL;
                }
            } else {
                return ResultCode.DEDUCTIONPLATFORMCOST_FAIL;
            }
        } catch (Exception e) {
            System.out.println("deductionPlatformCostFAIL:");
            e.printStackTrace();
        }
        return ResultCode.SYS_FAIL;
    }

    /**
     * 定时扫描返现  niufy
     */
    public void timingScanningCashback() throws Exception {
        //记录处理条数
        int notStartedCashbackNum = 0;
        //记录处理状态
        boolean notStartedCashbackBool = false;
        //记录处理条数
        int awaitCashbackNum = 0;
        //记录处理状态
        boolean awaitCashbackBool = false;
        //扫描开始的时候记录开始时间
        ScanningCashbackLogPO scanningCashbackLogPO = new ScanningCashbackLogPO();
        scanningCashbackLogPO.setStartTime(DateUtils.getCurrentTime());
        iScanningCashbackLogMapper.save(scanningCashbackLogPO);
        try {
            //查询未开始的，具体返现日期是今天的返现计划
            List listNotStartedCashback = cashbackRecordMapper.findNotStartedCashback();
            if (listNotStartedCashback != null && listNotStartedCashback.size() > 0) {
                //-->处理具体返现日期是今天的返现计划（未开始的）
                cashbackActivity.dealwithNotStartedCashback(listNotStartedCashback);
                notStartedCashbackBool = true;
                notStartedCashbackNum = listNotStartedCashback.size();
            } else {
                notStartedCashbackBool = true;
            }
            //查询具体返现日期等于当天的返现明细
            List listAwaitCashback = cashbackDetailsMapper.findThedayCashback();
            if (listAwaitCashback != null && listAwaitCashback.size() > 0) {
                //-->处理具体返现日期是今天的返现明细（未开始的）
                cashbackActivity.dealwithAwaitCashback(listAwaitCashback);
                awaitCashbackBool = true;
                awaitCashbackNum = listAwaitCashback.size();
            } else {
                awaitCashbackBool = true;
            }
        } finally {
            //扫描结束的时候记录时间、记录描述
            String scanningDescription = "";
            if (notStartedCashbackBool && awaitCashbackBool) {
                scanningDescription = "日期：" + DateUtils.getCurrentDate() + "已经成功扫描，该日处理" + notStartedCashbackNum + "条返利计划，处理了" + awaitCashbackNum + "条返利用户数据！";
            } else {
                scanningDescription = "日期：" + DateUtils.getCurrentDate() + "扫描成功，但是处理失败！";
            }
            scanningCashbackLogPO.setEndTime(DateUtils.getCurrentTime());
            scanningCashbackLogPO.setDescription(scanningDescription);
            iScanningCashbackLogMapper.update(scanningCashbackLogPO);
        }
    }
}