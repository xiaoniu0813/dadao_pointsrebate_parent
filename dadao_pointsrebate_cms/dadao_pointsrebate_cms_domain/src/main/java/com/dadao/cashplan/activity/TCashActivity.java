package com.dadao.cashplan.activity;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashplan.entity.TCapitalpoolAvailable;
import com.dadao.cashplan.entity.TCashPlan;
import com.dadao.cashplan.entity.TUserEffectiveIntegral;
import com.dadao.cashplan.mapper.ITCashMapper;
import com.dadao.market.entity.MarketGradePO;
import com.dadao.sys.mapper.ISysConstMapper;
import com.dadao.utils.DateUtils;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by YunQiang on 2017/9/15
 */
//TODO 所有关于返利计划的业务都在这里，业务闭环之后处理接口层
@Repository
public class TCashActivity {

    @Autowired
    private ITCashMapper itCashMapper;

    @Autowired
    private ISysConstMapper iSysConstMapper;

    /**
     * 一、创建返利计划
     * @param marketId             市场ID
     * @param cashbackSpecificDate 返利具体时间
     * @return
     */
    public Result saveCashPlan(Long marketId, Date cashbackSpecificDate) {
        //1、利用marketId得到市场等级
        if (marketId == null) {
            return new Result(ResultCode.ENTITY_ID_NULL, "请检查参数marketId");
        }
        Integer grade = itCashMapper.findGradeByMarketId(marketId);
        if (grade == null) {
            return new Result(ResultCode.ENTITY_ID_FAIL);
        }
        //2、判断输入的返利时间是否正确：大于当前市场上次返利日期至少3个周；大于当前时间；如果<=3周提示 间隔短
        if (cashbackSpecificDate == null) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL);
        }
        if (cashbackSpecificDate.getTime() <= new Date().getTime()) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "本次返利时间cashbackSpecificDate必须大于此刻时间");
        }
        //1814400000为三周时间的毫秒数
        Date lastCashTime = itCashMapper.findMaxCashbackSpecificDateByGrade(grade);
        if (lastCashTime != null && cashbackSpecificDate.getTime() <= lastCashTime.getTime() + 1000 * 60 * 60 * 24 * 7 * 3) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "本次返利时间cashbackSpecificDate必须大于上次返利时间至少三周；上次本级市场用戶的返利时间为：" + DateUtils.timeStamp2LocalDate(lastCashTime.getTime()));
        }
        //3、根据市场等级得到积分上限
        Integer currentIntegralUpper = this.backIntegralUpper(grade);
        //4、组装TCashPlan对象,并保存数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");    //格式化日期格式为yyyyMMdd，用于拼接返利编码
        TCashPlan tCashPlan = new TCashPlan();
        CashbackRecordPO cashbackRecordPO = new CashbackRecordPO();
        cashbackRecordPO.setCurrentIntegralUpper(currentIntegralUpper);
        cashbackRecordPO.setMarketID(marketId);
        cashbackRecordPO.setRecordCoding((char) (64 + grade) + sdf.format(cashbackSpecificDate));  //返现编码：格式为A20170816
        cashbackRecordPO.setStatus(2);    //返现状态为2表示未开始
        cashbackRecordPO.setCashbackSpecificDate(sdf.format(cashbackSpecificDate));
        cashbackRecordPO.setCreateTime(new Date());    //返利计划创建时间
        cashbackRecordPO.setCashbackMultiple(iSysConstMapper.selectByPrimaryKey(2).getValue().doubleValue());    //默认0倍返现
        cashbackRecordPO.setCashbackPeriod(3);  //本次返现周期，默认3个月
        cashbackRecordPO.setPeriodUnit(0);  //返现周期单位，0月
        cashbackRecordPO.setIntervals(1); //分期间隔为1
        cashbackRecordPO.setIntervalsUnit(0);   //分期间隔单位为0月
        int insertCount = itCashMapper.insertCashbackRecord(cashbackRecordPO);
        return insertCount == 1 ? new Result(ResultCode.SYS_SUCCESS) : new Result(ResultCode.SYS_FAIL);
    }

    /**
     * 二、查询达到积分上限用户积分信息
     * 注意：该方法执行时，如果当前时间小于返利具体时间，则
     * @param marketId  市场id
     * @param cashbackSpecificDate 返利时间
     * @return
     */
    public List<TUserEffectiveIntegral> findUserEffectiveIntegralByUserIds(Long marketId, Date cashbackSpecificDate,Integer currentIntegralUpper) {
        //无效积分开始时间,返利时间减去7天：
        Date expirationDate = new Date(cashbackSpecificDate.getTime() - 1000 * 60 * 60 * 24 * 7);
        //1、初始化用户有效积分集合为null
        List<TUserEffectiveIntegral> list = null;
        //2、找到可能达到积分上限用户userId集合
        List<Long> userIds = itCashMapper.findAllUserId(marketId,currentIntegralUpper);
        //3、遍历userIds，查询用户有效积分,并将结果加入集合
        if (userIds != null && userIds.size() > 0) {
            list = new ArrayList<TUserEffectiveIntegral>();
            TUserEffectiveIntegral tUserEffectiveIntegral = new TUserEffectiveIntegral();
            for (long userId : userIds) {
                //4、查到用户积分信息
                tUserEffectiveIntegral = itCashMapper.findEffectiveIntegralByUserId(marketId, userId, expirationDate);
                int flag = tUserEffectiveIntegral.getEffectiveIntegral().compareTo(new BigDecimal(currentIntegralUpper));
                //5、如果用户有效积分(tUserEffectiveIntegral.effectiveIntegral)大于等于积分上限(input:currentIntegralUpper),将结果加入集合
                if(tUserEffectiveIntegral != null && flag == 1 || flag == 0){
                    tUserEffectiveIntegral.setMarketId(marketId);
                    tUserEffectiveIntegral.setCurrentIntegralUpper(new BigDecimal(currentIntegralUpper));
                    list.add(tUserEffectiveIntegral);
                }
            }
        }
        return list;
    }

    /**
     * 三、查询资金池有效余额
     * @param marketId
     * @param cashbackSpecificDate 具体返利时间
     * @return
     */
    public TCapitalpoolAvailable findCapitalpoolAvailable(Long marketId, Date cashbackSpecificDate) {
        if (marketId == null) {
            return null;
        }
        if (cashbackSpecificDate == null) {
            return null;
        }
        //无效资金池余额开始时间,返利时间减去7天：
        Date expirationDate = new Date(cashbackSpecificDate.getTime() - 1000 * 60 * 60 * 24 * 7);
        return itCashMapper.findCapitalpoolAvailable(marketId, expirationDate);
    }
    /**
     * 根据市场等级查询该级市场信息 并获取积分上限和积分下限的一个随机值
     * @param grade
     * @return
     */
    private Integer backIntegralUpper(Integer grade) {
        MarketGradePO mg = (MarketGradePO) itCashMapper.findById(grade);
        int max = mg.getIntegralUpper();
        int min = mg.getIntegralLower();
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s/100*100;
    }

    /**
     * 创建商户返利计划
     * @param cashbackSpecificDate
     * @return
     */
    public Result createBusinessCashbackPlan(Long marketId, Date cashbackSpecificDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //recordCoding 返现编码；商户返利没有市场等级的区分，返现编码统一以shop的首字母 S + 返现时间生成(S20171201);
        String recordCoding = "S" + sdf.format(cashbackSpecificDate);
        //cashbackMultiple 返现倍数；返现倍数取sys_const的常量businessCashRate，其constId=1；
        BigDecimal cashbackMultiple = iSysConstMapper.selectByPrimaryKey(1).getValue();
        //cashbackPeriod 本次返现周期 3
        int cashbackPeriod = 3;
        //periodUnit 返现周期单位 0
        int periodUnit = 0;
        //intervals 分期间隔 1
        int intervals = 1;
        //intervalsUnit 分期间隔单位 0
        int intervalsUnit = 0;
        //cashbackSpecificDate 返现具体日期；（大于当前日期至少1天，大于上次返利日期至少3周）
        if (cashbackSpecificDate.getTime() <= new Date().getTime()) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "本次返利时间cashbackSpecificDate必须大于此刻时间");
        }
        Date lastCashTime = itCashMapper.findMaxBusinessCashbackSpecificDate(marketId);
        if (lastCashTime != null && cashbackSpecificDate.getTime() <= lastCashTime.getTime() + 1000 * 60 * 60 * 24 * 7 * 3) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "本次返利时间cashbackSpecificDate必须大于上次返利时间至少三周；上次本级市场商戶的返利时间为：" + DateUtils.timeStamp2LocalDate(lastCashTime.getTime()));
        }
        //status 返现状态（未开始7）
        Integer status = 7;
        CashbackRecordPO cashbackRecordPO = new CashbackRecordPO(recordCoding, cashbackMultiple.doubleValue(), cashbackPeriod, periodUnit, intervals, intervalsUnit, sdf.format(cashbackSpecificDate), status);
        cashbackRecordPO.setMarketID(marketId);
        int insertCount = itCashMapper.insertCashbackRecord(cashbackRecordPO);
        return new Result(ResultCode.SYS_SUCCESS,"插入" + insertCount + "条数据成功！");
    }


}
