package com.dadao.cashtemp;

import com.dadao.cashplan.activity.TCashActivity;
import com.dadao.cashplan.entity.TCapitalpoolAvailable;
import com.dadao.cashplan.entity.TUserEffectiveIntegral;
import com.dadao.cashplan.mapper.ITCashMapper;
import com.dadao.pub.BaseTest;
import com.dadao.sys.mapper.ISysConstMapper;
import com.dadao.utils.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YunQiang on 2017/9/16
 */
public class ITCashTest extends BaseTest{

    @Autowired
    private ITCashMapper itCashMapper;

    @Autowired
    private ISysConstMapper iSysConstMapper;

    @Autowired
    private TCashActivity tCashActivity;

    SimpleDateFormat sdf =   null;

    @Before
    public void Init(){
        sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
    }


    /**
     * 返利业务分解一：创建一条返利计划
     */
    @Test
    @Transactional
    public void saveCashPlan() throws ParseException {
        Long marketId = 22L;    //参数1：marketId
        Date cashbackSpecificDate =  sdf.parse("2017-10-1 00:00:00"); //参数2：cashbackSpecificDate大于当前时间，大于最后一次返利时间至少3周
        Result result = tCashActivity.saveCashPlan(marketId,cashbackSpecificDate);
        System.out.println(result.getCode());
        System.out.println(result.getDescirption());
        System.out.println(result.getObject());
    }

    /**
     * 返利业务分解二：查询用户有效积分
     */
    @Test //单元测试1：找到所有用户ID
    public void findUserIds(){
        itCashMapper.findAllUserId(21L,50);
    }
    @Test //单元测试2：查询单个用户的有效积分
    public void findEffectiveIntegralByUserId(){
        try {
            Long marketId = 21L; //市场id
            Long userId = 171L; //用户ID
            Date expirationDate = sdf.parse("2017-08-24 00:00:00"); //无效积分开始统计时间
            Date cashbackSpecificDate = sdf.parse("2017-08-31 00:00:00"); //返利时间
            itCashMapper.findEffectiveIntegralByUserId(marketId,userId,expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返利业务分解三：查询资金池有效余额
     */
    @Test
    public void findAvailableMoney() throws ParseException {
        TCapitalpoolAvailable tCapitalpoolAvailable = itCashMapper.findCapitalpoolAvailable(21,sdf.parse("2017-08-24 00:00:00"));
        System.out.println(tCapitalpoolAvailable);
    }

    @Test
    public void findCapitalpoolAvailable() throws ParseException {
        tCashActivity.findCapitalpoolAvailable(22L,sdf.parse("2017-08-31 00:00:00"));
    }



    /**
     * 业务整合测试一：查询用户有效积分列表
     */
    @Test
    public void findEffectiveIntegralByUserIds() {
        try {
           tCashActivity.findUserEffectiveIntegralByUserIds(21L,sdf.parse("2017-08-31 00:00:00"),500);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //-------------------商户返利开始-------------------
    /**
     * 01、查询最近一次返利时间
     */
    @Test
    public void selectMaxBusinessCashbackSpecificDate(){
        itCashMapper.findMaxBusinessCashbackSpecificDate(21L);
    }

    /**
     * 02、插入一条商户的返利计划
     */
    @Test
    @Transactional
    public void createBusinessCashbackPlan(){
        Result result = tCashActivity.createBusinessCashbackPlan(21L, new Date(new Date().getTime() + 212151));
        System.out.println(result.getDescirption());
        System.out.println(result.getObject());
    }

    @Test
    public void findEffectiveIntegrals(){
        List<TUserEffectiveIntegral> tUserEffectiveIntegrals=itCashMapper.findEffectiveIntegrals(21,1000,"'2017-11-01'");
        System.out.println(tUserEffectiveIntegrals);
    }
}
