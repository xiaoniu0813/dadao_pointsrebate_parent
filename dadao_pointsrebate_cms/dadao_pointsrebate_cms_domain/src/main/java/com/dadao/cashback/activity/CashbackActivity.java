package com.dadao.cashback.activity;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.capitalpool.entity.CapitalpoolVO;
import com.dadao.capitalpool.mapper.ICapitalpoolMapper;
import com.dadao.capitalpool.mapper.ICapitalpoolRecordMapper;
import com.dadao.cashback.entity.CashbackDetailsPO;
import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.entity.CashbackRecordVO;
import com.dadao.cashback.mapper.CashbackDetailsMapper;
import com.dadao.cashback.mapper.CashbackRecordMapper;
import com.dadao.cashplan.activity.TCashActivity;
import com.dadao.cashplan.entity.TCapitalpoolAvailable;
import com.dadao.cashplan.entity.TUserEffectiveIntegral;
import com.dadao.cashplan.mapper.ITCashMapper;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.OrderMapper;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.shop.activity.CalculatingShopIntegral;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopLastIntegralRecordingPO;
import com.dadao.shop.mapper.ShopLastIntegralRecordingMapper;
import com.dadao.shop.mapper.ShopMapper;
import com.dadao.user.entity.*;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.UserIntegralRecordingMapper;
import com.dadao.user.mapper.UserTransactionRecodMapper;
import com.dadao.user.mapper.UserWalletMapper;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.QueryResult;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 返利方法实现类
 *
 * @auther NFY niufuyang
 * @create 2017-10-23
 */
@Repository
public class CashbackActivity {


    @Autowired
    private ShopLastIntegralRecordingMapper shopLastIntegralRecordingMapper;

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CashbackDetailsMapper cashbackDetailsMapper;
    @Autowired
    private CashbackRecordMapper cashbackRecordMapper;
    @Autowired
    private ICapitalpoolMapper capitalpoolMapper;
    @Autowired
    private ICapitalpoolRecordMapper capitalpoolRecordMapper;
    @Autowired
    private IUserIntegralMapper userIntegralMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private UserIntegralRecordingMapper userIntegralRecordingMapper;
    @Autowired
    private UserTransactionRecodMapper userTransactionRecodMapper;
    @Autowired
    private TCashActivity tCashActivity;
    @Autowired
    private ITCashMapper itCashMapper;
    @Autowired
    private CalculatingShopIntegral calculatingShopIntegral;
    @Autowired
    private IPushService iPushService;

    /**
     * 处理具体返现日期是今天的返现计划（未开始的）
     *
     * @param list
     */
    @Transactional
    public void dealwithNotStartedCashback(List list) throws Exception{
        try {
            //遍历返现计划
            for (Object object : list) {
                //当前返利计划
                CashbackRecordPO cashbackRecordPO = new CashbackRecordPO();
                CashbackRecordVO cashbackRecordVO = (CashbackRecordVO) object;
                BeanUtils.copyProperties(cashbackRecordPO,cashbackRecordVO);
                //设置日期格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                //查询最后一次返利日期
                String last=cashbackRecordMapper.findLastRebateDate();
                //查询达到积分上限的用户
                List<TUserEffectiveIntegral> tUserEffectiveIntegrals = itCashMapper.findEffectiveIntegrals(cashbackRecordPO.getMarketID().intValue(), cashbackRecordPO.getCurrentIntegralUpper(),"'"+last+"'");
                if (tUserEffectiveIntegrals != null && tUserEffectiveIntegrals.size() > 0) {
                    //返现人数
                    cashbackRecordPO.setCashbackNumber(tUserEffectiveIntegrals.size());
                    //计算具体返现倍数
                    Double cashbackMultiple = ArithUtil.div(ArithUtil.div(cashbackRecordPO.getCashbackSumMoney(), new BigDecimal(tUserEffectiveIntegrals.size()), 2), new BigDecimal(cashbackRecordPO.getCurrentIntegralUpper()), 2).doubleValue();
                    cashbackRecordPO.setCashbackMultiple(cashbackMultiple);
                    //计算实际返现总金额
                    BigDecimal cashbackSumMoney = ArithUtil.mul(ArithUtil.mul(new BigDecimal(cashbackMultiple), new BigDecimal(tUserEffectiveIntegrals.size())), new BigDecimal(cashbackRecordPO.getCurrentIntegralUpper()));
                    cashbackRecordPO.setCashbackSumMoney(cashbackSumMoney);
                    if(cashbackRecordPO.getCashbackPeriod()>1) {
                        //设置返现状态为已开始但未完成
                        cashbackRecordPO.setStatus(0);
                    }else{
                        //如果返利期数就一期状态直接设置为已完成
                        cashbackRecordPO.setStatus(1);
                    }
                    //更新返现记录
                    int saveRecordResult = cashbackRecordMapper.update(cashbackRecordPO);
                    if (saveRecordResult != 1) {
                        throw new Exception("更新返现记录失败");
                    }
                    //用户每期返现金额
                    BigDecimal userPeriodMoney = cashbackRecordPO.getCashbackSumMoney().divide(new BigDecimal(cashbackRecordPO.getCashbackPeriod()), 2, BigDecimal.ROUND_HALF_DOWN).divide(new BigDecimal(cashbackRecordPO.getCashbackNumber()), 2, BigDecimal.ROUND_HALF_DOWN);
                    //用户每期税后实到金额
                    BigDecimal really = ArithUtil.sub(userPeriodMoney, userPeriodMoney.multiply(new BigDecimal("0.2")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    //更新资金池金额
                    TCapitalpoolAvailable tCapitalpoolAvailable = tCashActivity.findCapitalpoolAvailable(cashbackRecordPO.getMarketID(), new Date());
                    CapitalpoolPO capitalpoolPO = new CapitalpoolPO();
                    capitalpoolPO.setMarketId(cashbackRecordPO.getMarketID());
                    if (tCapitalpoolAvailable.getAvailableMoney().compareTo(cashbackRecordPO.getCashbackSumMoney()) != -1) {
                        //**** 如果当前资金池金额满足返现的情况下，做如下操作
                        //计算本次资金池剩余金额
                        BigDecimal thisTimeMoney = ArithUtil.sub(tCapitalpoolAvailable.getMoney(), tCapitalpoolAvailable.getAvailableMoney());
                        capitalpoolPO.setMoney(thisTimeMoney);
                        //计算当前备用资金池金额
                        BigDecimal thisTimeSpareMoney = ArithUtil.add(tCapitalpoolAvailable.getSpareMoney(), ArithUtil.sub(tCapitalpoolAvailable.getAvailableMoney(), cashbackRecordPO.getCashbackSumMoney()));
                        capitalpoolPO.setSpareMoney(thisTimeSpareMoney);
                        if (cashbackRecordPO.getCashbackPeriod()>1) {
                            capitalpoolPO.setToBeBackMoney(ArithUtil.add(tCapitalpoolAvailable.getToBeBackMoney(), ArithUtil.sub(cashbackRecordPO.getCashbackSumMoney(),ArithUtil.mul(new BigDecimal(tUserEffectiveIntegrals.size()),userPeriodMoney))));
                        }
                        //更新备用金额
                        int updateCapitalpool = capitalpoolMapper.updateMoneyByMarketId(capitalpoolPO);
                        CapitalpoolRecordPO capitalpoolRecordPO = new CapitalpoolRecordPO();
                        capitalpoolRecordPO.setObjectId(cashbackRecordPO.getRecordId());
                        capitalpoolRecordPO.setStatus(3);
                        capitalpoolRecordPO.setMoney(cashbackRecordPO.getCashbackSumMoney());
                        capitalpoolRecordPO.setMarketId(cashbackRecordPO.getMarketID());
                        capitalpoolRecordPO.setDescription(df.format(new Date()) + "奖励扣除金额");
                        int saveCapitalpoolRecord = capitalpoolRecordMapper.save(capitalpoolRecordPO);
                        if (ArithUtil.sub(tCapitalpoolAvailable.getAvailableMoney(), cashbackRecordPO.getCashbackSumMoney()).compareTo(new BigDecimal("0")) == 1) {
                            //**** 如果本期资金池扣除返现后有余额，更新备用资金池流水
                            capitalpoolRecordPO.setStatus(4);
                            capitalpoolRecordPO.setMoney(ArithUtil.sub(tCapitalpoolAvailable.getAvailableMoney(), cashbackRecordPO.getCashbackSumMoney()));
                            capitalpoolRecordPO.setDescription(df.format(new Date()) + "奖励结余");
                            int saveCR = capitalpoolRecordMapper.save(capitalpoolRecordPO);
                            if (saveCR != 1) {
                                throw new Exception("更新资金池结余失败");
                            }
                        }
                        if (updateCapitalpool != 1 || saveCapitalpoolRecord != 1) {
                            throw new Exception("更新资金池失败");
                        }
                    } else if (ArithUtil.add(tCapitalpoolAvailable.getAvailableMoney(), tCapitalpoolAvailable.getSpareMoney()).compareTo(cashbackRecordPO.getCashbackSumMoney()) != -1) {
                        //**** 如果当前资金池金额加备用金额满足返现的情况下，，做如下操作
                        //计算当前备用资金池金额
                        BigDecimal thisTimeSpareMoney = ArithUtil.sub(tCapitalpoolAvailable.getSpareMoney(), ArithUtil.sub(cashbackRecordPO.getCashbackSumMoney(), tCapitalpoolAvailable.getAvailableMoney()));
                        capitalpoolPO.setSpareMoney(thisTimeSpareMoney);
                        if (cashbackRecordPO.getCashbackPeriod()>1) {
                            capitalpoolPO.setToBeBackMoney(ArithUtil.add(tCapitalpoolAvailable.getToBeBackMoney(), ArithUtil.sub(cashbackRecordPO.getCashbackSumMoney(),ArithUtil.mul(new BigDecimal(tUserEffectiveIntegrals.size()),userPeriodMoney))));
                        }
                        //计算本次资金池剩余金额
                        BigDecimal thisTimeMoney = ArithUtil.sub(tCapitalpoolAvailable.getMoney(), tCapitalpoolAvailable.getAvailableMoney());
                        capitalpoolPO.setMoney(thisTimeMoney);
                        //更新备用金额
                        int updateCapitalpool = capitalpoolMapper.updateMoneyByMarketId(capitalpoolPO);
                        CapitalpoolRecordPO capitalpoolRecordPO = new CapitalpoolRecordPO();
                        capitalpoolRecordPO.setObjectId(cashbackRecordPO.getRecordId());
                        capitalpoolRecordPO.setStatus(3);
                        capitalpoolRecordPO.setMoney(tCapitalpoolAvailable.getAvailableMoney());
                        capitalpoolRecordPO.setMarketId(cashbackRecordPO.getMarketID());
                        capitalpoolRecordPO.setDescription(df.format(new Date()) + "奖励扣除金额");
                        int saveCapitalpoolRecord = capitalpoolRecordMapper.save(capitalpoolRecordPO);
                        capitalpoolRecordPO.setStatus(5);
                        capitalpoolRecordPO.setMoney(ArithUtil.sub(tCapitalpoolAvailable.getSpareMoney(), ArithUtil.sub(tCapitalpoolAvailable.getSpareMoney(), ArithUtil.sub(cashbackRecordPO.getCashbackSumMoney(), tCapitalpoolAvailable.getAvailableMoney()))));
                        int saveCR = capitalpoolRecordMapper.save(capitalpoolRecordPO);
                        if (saveCR != 1) {
                            throw new Exception("更新资金池结余失败");
                        }
                        if (updateCapitalpool != 1 || saveCapitalpoolRecord != 1) {
                            throw new Exception("更新资金池失败");
                        }
                    } else {
                        throw new Exception("当前资金池金额小于返现总金额");
                    }
                    for (TUserEffectiveIntegral tUserEffectiveIntegral : tUserEffectiveIntegrals) {
                        //为商户积分开始
                       // calculatingShopIntegral.acceptReachIntegralUser(tUserEffectiveIntegral);
                        //为商户积分结束
                        UserIntegral userIntegral = new UserIntegral();
                        userIntegral.setUserId(tUserEffectiveIntegral.getUserId());
                        userIntegral.setMarketId(tUserEffectiveIntegral.getMarketId());
                        userIntegral.setIntegral(ArithUtil.sub(tUserEffectiveIntegral.getCurrentIntegral(), new BigDecimal(cashbackRecordPO.getCurrentIntegralUpper() + 0.0)).doubleValue());
                        //更新用户积分
                        int updateCashbackUserIntegral = userIntegralMapper.update(userIntegral);
                        UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
                        userIntegralRecordingPO.setUserId(tUserEffectiveIntegral.getUserId());
                        userIntegralRecordingPO.setDirection(1);
                        userIntegralRecordingPO.setMarketId(cashbackRecordPO.getMarketID());
                        userIntegralRecordingPO.setObjectId(cashbackRecordPO.getRecordId());
                        userIntegralRecordingPO.setIntegral(new BigDecimal(cashbackRecordPO.getCurrentIntegralUpper()));
                        int saveUserIntegralRecording = userIntegralRecordingMapper.save(userIntegralRecordingPO);
                        if (updateCashbackUserIntegral != 1 || saveUserIntegralRecording != 1) {
                            throw new Exception("更新返现用户积分失败");
                        }
                        //返现周期时间基数
                        Date date = new Date();
                        //生成每一期返现记录
                        for (int i = 0; i < cashbackRecordPO.getCashbackPeriod(); i++) {
                            CashbackDetailsPO cashbackDetailsPO = new CashbackDetailsPO();
                            cashbackDetailsPO.setRecordId(cashbackRecordPO.getRecordId());
                            cashbackDetailsPO.setUserId(tUserEffectiveIntegral.getUserId());
                            //计算具体返现日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            if (cashbackRecordPO.getIntervalsUnit() == 0) {
                                //**** 分期间隔单位如果是月，根据分期间隔后推精准计算返现日期
                                if (i != 0) {
                                    //**** 首次返现就用当前时间，否则就推算时间
                                    if (cashbackRecordPO.getIntervals() == 0) {
                                        //**** 如果返现间隔为0，根据返现周期和单位依次后推时间
                                        calendar.add(Calendar.MONTH, 1);
                                    } else {
                                        calendar.add(Calendar.MONTH, cashbackRecordPO.getIntervals() + 1);
                                    }
                                }
                            } else {
                                //**** 分期间隔单位如果是周
                                if (i != 0) {
                                    if (cashbackRecordPO.getIntervals() == 0) {
                                        calendar.add(Calendar.DATE, 7);
                                    } else {
                                        calendar.add(Calendar.DATE, (cashbackRecordPO.getIntervals() + 1) * 7);
                                    }
                                }
                            }
                            //给返现周期时间基数赋予当前推算时间
                            date = calendar.getTime();
                            cashbackDetailsPO.setCashbackSpecificDate(sdf.format(date));
                            //本期返现金额
                            cashbackDetailsPO.setCashbackMoney(userPeriodMoney);
                            //代缴税
                            BigDecimal taxPayment = cashbackDetailsPO.getCashbackMoney().multiply(new BigDecimal("0.2")).setScale(2, BigDecimal.ROUND_HALF_UP);
                            cashbackDetailsPO.setTaxPayment(taxPayment);
                            //实到金额
                            cashbackDetailsPO.setReally(really);
                            if (i == 0) {
                                //**** 操作返现后，第一次直接为用户返现
                                //更新用户钱包金额
                                UserWalletPO userWalletPO = (UserWalletPO) userWalletMapper.findById(cashbackDetailsPO.getUserId());
                                userWalletPO.setBalance(ArithUtil.add(userWalletPO.getBalance(), really));
                                int updateUserWallet = userWalletMapper.update(userWalletPO);
                                //更新用户钱包流水
                                UserTransactionRecodPO userTransactionRecodPO = new UserTransactionRecodPO();
                                userTransactionRecodPO.setTransactionType(0);
                                userTransactionRecodPO.setTransactionAmount(cashbackDetailsPO.getCashbackMoney());
                                userTransactionRecodPO.setTransactionDetails("获得奖励金额");
                                userTransactionRecodPO.setExpenditureIncome(1);
                                userTransactionRecodPO.setUserId(userWalletPO.getUserId());
                                userTransactionRecodPO.setPayMethod(4);
                                userTransactionRecodPO.setCommission(cashbackDetailsPO.getTaxPayment());
                                userTransactionRecodPO.setActualIncome(cashbackDetailsPO.getReally());
                                int saveUserTransactionRecod = userTransactionRecodMapper.save(userTransactionRecodPO);
                                if (updateUserWallet != 1 || saveUserTransactionRecod != 1) {
                                    throw new Exception("首次返现时更新用户钱包金额失败");
                                }
                                //推送消息实体(用户)
                                InformationPO informationPO = new InformationPO();
                                informationPO.setTitle("奖励消息");
                                informationPO.setContent("获得奖励金额 " + userTransactionRecodPO.getTransactionAmount() + " 元");
                                informationPO.setUserId(userTransactionRecodPO.getUserId());
                                informationPO.setStatus(5);
                                informationPO.setObjectId(userTransactionRecodPO.getTransactionId());
                                //推送
                                iPushService.pushForConsumer(informationPO.getContent(),informationPO);
                                cashbackDetailsPO.setStatus(1);
                            } else {
                                cashbackDetailsPO.setStatus(0);
                            }
                            int saveCashbackDetails = cashbackDetailsMapper.save(cashbackDetailsPO);
                            if (saveCashbackDetails != 1) {
                                throw new Exception("持久化用户返现明细失败");
                            }
                        }
                    }
                } else {
                    throw new Exception("当前期没有达到积分上线的用户");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 处理待返现日期等于今天的返现明细（已开始）
     *
     * @param list
     */
    @Transactional()
    public void dealwithAwaitCashback(List list) {
        try {
            //遍历返现明细
            for (Object object : list) {
                //得到返现明细对象
                CashbackDetailsVO cashbackDetailsVO = (CashbackDetailsVO) object;
                CashbackDetailsPO cashbackDetailsPO = new CashbackDetailsPO();
                BeanUtils.copyProperties(cashbackDetailsPO,cashbackDetailsVO);
                //更新用户钱包金额
                UserWalletPO userWalletPO = (UserWalletPO) userWalletMapper.findById(cashbackDetailsPO.getUserId());
                userWalletPO.setBalance(ArithUtil.add(userWalletPO.getBalance(), cashbackDetailsPO.getReally()));
                int updateUserWallet = userWalletMapper.update(userWalletPO);
                //更新用户钱包流水
                UserTransactionRecodPO userTransactionRecodPO = new UserTransactionRecodPO();
                userTransactionRecodPO.setTransactionType(0);
                userTransactionRecodPO.setTransactionAmount(cashbackDetailsPO.getCashbackMoney());
                userTransactionRecodPO.setTransactionDetails("获得奖励金额");
                userTransactionRecodPO.setExpenditureIncome(1);
                userTransactionRecodPO.setUserId(userWalletPO.getUserId());
                userTransactionRecodPO.setPayMethod(4);
                userTransactionRecodPO.setCommission(cashbackDetailsPO.getTaxPayment());
                userTransactionRecodPO.setActualIncome(cashbackDetailsPO.getReally());
                int saveUserTransactionRecod = userTransactionRecodMapper.save(userTransactionRecodPO);

                if (updateUserWallet != 1 || saveUserTransactionRecod != 1) {
                    throw new Exception("更新用户钱包失败");
                }
                //推送消息实体(用户)
                InformationPO informationPO = new InformationPO();
                informationPO.setTitle("奖励消息");
                informationPO.setContent("获得奖励金额 " + userTransactionRecodPO.getTransactionAmount() + " 元");
                informationPO.setUserId(userTransactionRecodPO.getUserId());
                informationPO.setStatus(5);
                informationPO.setObjectId(userTransactionRecodPO.getTransactionId());
                //推送
                iPushService.pushForConsumer(informationPO.getContent(),informationPO);
                //更新返现明细状态
                cashbackDetailsPO.setStatus(1);
                int saveCashbackDetails = cashbackDetailsMapper.update(cashbackDetailsPO);
                if (saveCashbackDetails != 1) {
                    throw new Exception("更新用户返现明细状态失败");
                }
                // 查询同一返现计划下其他未返利的条数
                int tobebackCashbackCount = cashbackDetailsMapper.findTobebackCashbackCount(cashbackDetailsPO.getRecordId()).intValue();
                if (tobebackCashbackCount == 0) {
                    //***如果同一返利计划下没有未返利记录就更新返利计划为已返完
                    CashbackRecordPO cashbackRecordPO = new CashbackRecordPO();
                    cashbackRecordPO.setRecordId(cashbackDetailsPO.getRecordId());
                    cashbackRecordPO.setStatus(1);
                    int updateCashbackStatus = cashbackRecordMapper.update(cashbackRecordPO);
                    if (updateCashbackStatus != 1) {
                        throw new Exception("更新返利计划状态失败");
                    }
                }
                CashbackRecordPO cashbackRecordPO = (CashbackRecordPO) cashbackRecordMapper.findById(cashbackDetailsPO.getRecordId());
                //更新资金池待返金额
                CapitalpoolVO capitalpoolVO = capitalpoolMapper.findByMarketId(cashbackRecordPO.getMarketID());
                CapitalpoolPO capitalpoolPO = new CapitalpoolPO();
                BeanUtils.copyProperties(capitalpoolPO,capitalpoolVO);
                capitalpoolPO.setToBeBackMoney(ArithUtil.sub(capitalpoolVO.getToBeBackMoney(), cashbackDetailsPO.getCashbackMoney()));
                int updateCapitalpool = capitalpoolMapper.updateMoneyByMarketId(capitalpoolPO);
                if (updateCapitalpool!=1) {
                    throw new Exception("更新资金池待返金额失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 商户积分处理
     * @param shopLastIntegralRecordingPO
     * @param userIntegralRecordingPO
     * @param listAll
     * @param integral
     * @param tUserEffectiveIntegral
     */
    @Transactional
    public void countIntegral(ShopLastIntegralRecordingPO shopLastIntegralRecordingPO, UserIntegralRecordingPO userIntegralRecordingPO, List<UserIntegralRecordingPO> listAll, BigDecimal integral, TUserEffectiveIntegral tUserEffectiveIntegral) {
        Long marketId = tUserEffectiveIntegral.getMarketId();
        BigDecimal intervalsUnit = tUserEffectiveIntegral.getCurrentIntegralUpper();
        BigDecimal sumIntegral = new BigDecimal(0);
        sumIntegral = sumIntegral.add(integral);
        BigDecimal sum = new BigDecimal(0);
        //循环遍历每一条数据
        for (int i = 0; i < listAll.size(); i++) {
            //获取当前记录
            UserIntegralRecordingPO currentUserIntegralRecording = listAll.get(i);

            //根据条件查询当前objectId
            Long currentObjectId = currentUserIntegralRecording.getObjectId();
            userIntegralRecordingPO.setObjectId(currentObjectId);

            //查找是否存在当前objectId 且 发生方向为增加的记录
            userIntegralRecordingPO.setDirection(0);
            UserIntegralRecordingPO incrementRecord = (UserIntegralRecordingPO) userIntegralRecordingMapper.findByObject(userIntegralRecordingPO);
            //查找是否存在当前objectId 且 发生方向为减少的记录
            userIntegralRecordingPO.setDirection(1);
            UserIntegralRecordingPO reduceRecord = (UserIntegralRecordingPO) userIntegralRecordingMapper.findByObject(userIntegralRecordingPO);

            //如果只存在增加方向的记录而没有减少方向的记录 我们就操作这条数据
            if (incrementRecord != null && reduceRecord == null) {

                //根据objectId查找出单条订单表记录
                OrderPO orderPO = (OrderPO) orderMapper.findById(currentObjectId);

                //查找出shopId(商户的子商户id)
                Long currentShopId = orderPO.getShopId();

                //根据子商户id查询出这个商户的userId
                Shop shop = (Shop) shopMapper.findById(currentShopId);

                //当前商户id
                Long currentUserId = shop.getFk_user_id();

                //获得当前一条记录的用户积分（即商户积分）
                BigDecimal currentIntegral = currentUserIntegralRecording.getIntegral();

                //累积积分
                sumIntegral = sumIntegral.add(currentIntegral);

                //更新积分表中的商户积分
                //根据条件查询出本次商户总积分
                UserIntegral userIntegral = new UserIntegral();
                //当前市场等级
                userIntegral.setMarketId(marketId);
                //当前商户id
                userIntegral.setUserId(currentUserId);
                userIntegral.setIntegral(null);
                UserIntegral findUserIntegral = (UserIntegral) iUserIntegralMapper.findUserIntegralByMarketId(userIntegral);
                //获得当前商户积分(商户总积分表) 积分记录表
                Double currentShopIntegral = findUserIntegral.getIntegral();
                //创建最后一笔积分记录表

                //获取当前商户id
                shopLastIntegralRecordingPO.setShopId(currentUserId);
                //订单编号
                shopLastIntegralRecordingPO.setOrderId(orderPO.getOrderId());
                //创建时间
                shopLastIntegralRecordingPO.setCreateTime(orderPO.getCreateTime());

                //创建商户积分记录
                UserIntegralRecordingPO userIntegralRecordingPO1 = new UserIntegralRecordingPO();
                //发生方向
                userIntegralRecordingPO1.setDirection(0);
                //商户id
                userIntegralRecordingPO1.setUserId(currentUserId);
                //市场等级
                userIntegralRecordingPO1.setMarketId(marketId);
                //对象id
                userIntegralRecordingPO1.setObjectId(orderPO.getId());
                //状态 3为商户积分
                userIntegralRecordingPO1.setStatus(3);

                //如果总积分等于积分上限
                if (sumIntegral.compareTo(intervalsUnit) == 0) {

                    //更新商户积分 并把最后一笔存到最后一笔积分记录表
                    //currentShopIntegral currentIntegral integral
                    userIntegral.setIntegral(currentShopIntegral + currentIntegral.doubleValue() + integral.doubleValue());
                    int a = iUserIntegralMapper.update(userIntegral);
                    if(a != 1){
                        throw new RuntimeException("用户单笔总积分和等于积分上限状态时商户积分更新失败");
                    }

                    //获取最后一笔积分消费记录
                    shopLastIntegralRecordingPO.setIntegral(currentIntegral);
                    //发生方向
                    shopLastIntegralRecordingPO.setStatus(1);
                    int b = shopLastIntegralRecordingMapper.save(shopLastIntegralRecordingPO);
                    if(b != 1){
                        throw new RuntimeException("用户单笔总积分和等于积分上限状态时最后一条记录保存失败");
                    }

                    //商户使用的积分
                    userIntegralRecordingPO1.setIntegral(currentIntegral);
                    //描述
                    userIntegralRecordingPO1.setDescription("商户获得积分");
                    int c = userIntegralRecordingMapper.save(userIntegralRecordingPO1);
                    if(c != 1){
                        throw new RuntimeException("商户积分记录保存失败");
                    }
                    break;

                }
                //如果总积分大于积分上限
                if (sumIntegral.compareTo(intervalsUnit) > 0) {

                    //未计算积分 总积分-积分上限
                    shopLastIntegralRecordingPO.setIntegral(sumIntegral.subtract(intervalsUnit));
                    //状态为未计算 0
                    shopLastIntegralRecordingPO.setStatus(0);
                    int a = shopLastIntegralRecordingMapper.save(shopLastIntegralRecordingPO);
                    if(a != 1){
                        throw new RuntimeException("用户单笔总积分和大于积分上限状态时最后一条未计算记录保存失败");
                    }
                    //已计算积分 = 最后一笔积分- （总积分-积分上限）
                    shopLastIntegralRecordingPO.setIntegral(currentIntegral.subtract(sumIntegral.subtract(intervalsUnit)));
                    //更新商户积分 并把最后一笔已计算部分存到最后一笔积分记录表
                    shopLastIntegralRecordingPO.setStatus(1);
                    int b = shopLastIntegralRecordingMapper.save(shopLastIntegralRecordingPO);
                    if(b != 1){
                        throw new RuntimeException("用户单笔总积分和大于积分上限状态时最后一条已计算记录保存失败");
                    }
                    //该商户更新积分 当前商户积分等于 当前商户积分+ 已计算部分积分
                    //currentShopIntegral + (currentIntegral - (sumIntegral - intervalsUnit))
                    userIntegral.setIntegral(currentShopIntegral + (currentIntegral.doubleValue() - (sumIntegral.doubleValue() - intervalsUnit.doubleValue())));
                    int c = iUserIntegralMapper.update(userIntegral);
                    if(c != 1){
                        throw new RuntimeException("用户单笔总积分和大于积分上限状态时商户积分更新失败");
                    }

                    //商户使用的积分
                    userIntegralRecordingPO1.setIntegral(currentIntegral.subtract(sumIntegral.subtract(intervalsUnit)));
                    //描述
                    userIntegralRecordingPO1.setDescription("商户最后一笔达到积分上限的部分");
                    int d = userIntegralRecordingMapper.save(userIntegralRecordingPO1);
                    if(d != 1){
                        throw new RuntimeException("商户积分记录保存失败");
                    }
                    break;
                }

                //如果总积分小于积分上限
                if (sumIntegral.compareTo(intervalsUnit) < 0) {
                    //更新商户积分.add(currentIntegral)
                    userIntegral.setIntegral(currentShopIntegral + currentIntegral.doubleValue());
                    int a = iUserIntegralMapper.update(userIntegral);
                    if(a != 1){
                        throw new RuntimeException("用户单笔总积分和小于积分上限状态时商户积分更新失败");
                    }

                    //商户使用的积分
                    userIntegralRecordingPO1.setIntegral(currentIntegral);
                    //描述
                    userIntegralRecordingPO1.setDescription("商户获得积分");
                    int c = userIntegralRecordingMapper.save(userIntegralRecordingPO1);
                    if(c != 1){
                        throw new RuntimeException("商户积分记录保存失败");
                    }

                }
            }
        }
    }

    /**
     * 商户返利
     * @param id    积分表主键
     * @param recordId  返现记录表主键
     * @param cashbackMultiple  返现倍数
     */
    @Transactional(rollbackFor = Exception.class)
    public void cashToBusiness(Long id, Long recordId, BigDecimal cashbackMultiple){
        //1、查询当前商户积分信息，并利用返现倍数计算商户实际所得金额
        BusinessCashIntegral businessCashIntegral = iUserIntegralMapper.findIntegralById(id);
        businessCashIntegral.setActualSalary(businessCashIntegral.getIntegral().multiply(cashbackMultiple));
        //2、消减商户积分
        this.minusUserIntegral(businessCashIntegral.getUserId(),businessCashIntegral.getMarketId(),recordId, id,businessCashIntegral.getIntegral());
        //3、消减资金池待返余额
        Long capitalpoolId = capitalpoolMapper.findByMarketId(businessCashIntegral.getMarketId()).getId();
        this.minusCapitalpool(capitalpoolId,recordId,businessCashIntegral.getMarketId(),businessCashIntegral.getActualSalary());
        //4、增加商户钱包金额
        this.addUserWallet(businessCashIntegral.getUserId(),businessCashIntegral.getActualSalary());
    }

    /**
     * 返利业务分解1：消减用户积分
     * @param userId 用户id
     * @param marketId 市场表id
     * @param objectId 减少积分该字段就是返现记录表gt_cashback_record.recordId
     * @param id 积分表user_integral.id
     * @param integral 消减积分的数量
     */
    private void minusUserIntegral(Long userId, Long marketId, Long objectId, Long id, BigDecimal integral){
        //1、消减用户积分
        userIntegralMapper.minusIntegralById(id, integral);
        //2、保存一条积分流水记录
        UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
        //消减谁的积分
        userIntegralRecordingPO.setUserId(userId);
        //发生方向减少积分
        userIntegralRecordingPO.setDirection(1);
        //市场表marketId
        userIntegralRecordingPO.setMarketId(marketId);
        //返现记录表id
        userIntegralRecordingPO.setObjectId(objectId);
        //积分交易额
        userIntegralRecordingPO.setIntegral(integral);
        userIntegralRecordingPO.setDescription("商户返利");
        //状态2代表返利
        userIntegralRecordingPO.setStatus(2);
        userIntegralRecordingMapper.save(userIntegralRecordingPO);
    }

    /**
     * 返利业务分解2：消减资金池余额
     * @param id    资金池表主键
     * @param recordId  返现记录表主键
     * @param marketId  市场表主键
     * @param money 消减金额大小
     */
    private void minusCapitalpool(Long id,Long recordId, Long marketId , BigDecimal money){
        //1、消减资金池余额
        capitalpoolMapper.minusCapitalpoolById(id, money);
        //2、产生一条资金池流水记录
        CapitalpoolRecordPO capitalpoolRecordPO = new CapitalpoolRecordPO();
        //返现记录表ID
        capitalpoolRecordPO.setObjectId(recordId);
        //3扣除返利
        capitalpoolRecordPO.setStatus(3);
        //发生金额
        capitalpoolRecordPO.setMoney(money);
        //市场表ID
        capitalpoolRecordPO.setMarketId(marketId);
        //记录描述
        capitalpoolRecordPO.setDescription("商户返利");
        capitalpoolRecordMapper.save(capitalpoolRecordPO);
    }

    /**
     * 返利业务分解3：增加用户钱包余额
     */
    private void addUserWallet(Long userId, BigDecimal actualSalary){
        //1、增加用户钱包余额
        userWalletMapper.updateUserWalletByUserId(userId, actualSalary);
        //2、产生一条流水记录
        UserTransactionRecodPO userTransactionRecodPO = new UserTransactionRecodPO();
        //交易类型为返利
        userTransactionRecodPO.setTransactionType(0);
        //交易金额（积分）
        userTransactionRecodPO.setTransactionAmount(actualSalary);
        //交易详情
        userTransactionRecodPO.setTransactionDetails("商户返利");
        //支出收入类型
        userTransactionRecodPO.setExpenditureIncome(0);
        userTransactionRecodMapper.save(userTransactionRecodPO);
    }

    /**
     * 查询返利计划列表
     * @param cashbackRecordPO
     * @param pageNum
     * @return
     */
    public QueryResult findCashbackPlanList(CashbackRecordPO cashbackRecordPO, Integer pageNum){
        cashbackRecordPO.setBeginIndex((pageNum - 1) * cashbackRecordPO.getPageSize());
        Long totalSize = cashbackRecordMapper.findCount(cashbackRecordPO);
        List data = cashbackRecordMapper.findByPage(cashbackRecordPO);
        Long totalPage = totalSize % cashbackRecordPO.getPageSize() == 0 ? totalSize / cashbackRecordPO.getPageSize() : totalSize / cashbackRecordPO.getPageSize() + 1;
        //组装结果集
        QueryResult queryResult = new QueryResult();
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(data);
        queryResult.setPageNum(pageNum);
        return queryResult;
    }
}
