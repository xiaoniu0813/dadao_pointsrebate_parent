package com.dadao.user;

import com.dadao.activities.user.activity.UserIntegralActivity;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
public class TestIntegral extends BaseTest {

    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;

    private UserAccount userAccount;

    private UserIntegralVO userIntegralVO;
    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;
    @Autowired
    private IShopMapper iShopMapper;
    @Autowired
    private UserIntegralActivity userIntegralActivity;
    @Autowired
    private IOrderMapper iOrderMapper;


    @Before
    public void Init() {
        userAccount = new UserAccount();
        userAccount.setToken("faf9105720d000f7bcea972fabb4b518");

        userIntegralVO = new UserIntegralVO();
        userIntegralVO.setMarketId(21L);
        userIntegralVO.setUserId(44L);
    }

    @Test
    public void test1() {
        System.out.println(iUserIntegralMapper.ifIntegralNull(userAccount) > 0);
    }

    @Test
    public void test2() {
        iUserIntegralMapper.findIntegralNotNull(userAccount);
    }

    @Test
    public void test3() {
        iUserIntegralMapper.findIntegralIsNull();
    }

    @Test
    public void add() {
        iUserIntegralMapper.save(userIntegralVO);
    }

    @Test
    public void findMarketIds() {
        iUserIntegralMapper.findMarketIds();
    }

    @Test
    public void findIntegral() {
        //根据市场等级id 和用户id得到用户的总积分
        UserIntegralVO userIntegralVO = new UserIntegralVO();
        userIntegralVO.setMarketId(21L);
        userIntegralVO.setUserId(1L);
        UserIntegralVO userIntegralVO1 = (UserIntegralVO) iUserIntegralMapper.findIntegral(userIntegralVO);
        System.out.println("用户所得积分:" + userIntegralVO1);
    }

    //获取三个月的日期
    @Test
    public void currentDayUserIntegral() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2017-11-02 11:25:23";
        Date date1 = dateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        System.out.println(calendar.get(Calendar.MONTH));
    }

    @Test
    public void test() {
        ShopPO shopPO = new ShopPO();
        shopPO.setFk_user_id(2L);
        List<ShopPO> list = iShopMapper.list(shopPO);
        for (ShopPO lists:list) {
            System.out.println(lists);
        }
    }
    @Test
    public void t1(){
        OrderPO orderPO = (OrderPO)iOrderMapper.findById(1L);
        System.out.println("数据:" + orderPO.getProduct_name());
    }

}
