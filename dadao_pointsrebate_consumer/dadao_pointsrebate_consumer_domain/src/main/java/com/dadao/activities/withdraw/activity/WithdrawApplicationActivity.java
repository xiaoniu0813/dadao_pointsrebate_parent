package com.dadao.activities.withdraw.activity;

import com.dadao.cashback.entity.BalanceAndFreeze;
import com.dadao.constants.GeneralConstants;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.user.mapper.UserMapper;
import com.dadao.user.mapper.UserWalletMapper;
import com.dadao.utils.*;
import com.dadao.withdraw.entity.WithdrawApplicationPO;
import com.dadao.withdraw.entity.WithdrawList;
import com.dadao.withdraw.mapper.IWithdrawApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现申请操作
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
@Repository
public class WithdrawApplicationActivity {
    @Autowired
    private IWithdrawApplicationMapper withdrawApplicationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;

    @Autowired
    private IUserTransactionRecodMapper userTransactionRecodMapper;

    /**
     * 查询提现记录列表
     *
     * @param token
     * @return
     */
    public QueryResult findWithdrawList(WithdrawList withdrawList, String token) {
        UserAccount account = new UserAccount();
        account.setToken(token);
        account = userMapper.findByToken(account);
        if (account == null) {
            return null;
        }
        withdrawList.setUserId(account.getUserId());
        QueryResult queryResult = new QueryResult();
        Long totalSize = withdrawApplicationMapper.findCount(withdrawList);
        List list = withdrawApplicationMapper.findByPage(withdrawList);
        Long totalPage = totalSize % withdrawList.getPageSize() == 0 ? totalSize / withdrawList.getPageSize() : totalSize / withdrawList.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 用户提现操作
     *
     * @param withdrawApplicationPO
     * @return
     */
    @Transactional
    public Result withdrawApplication(WithdrawApplicationPO withdrawApplicationPO, String token,String payPassword) {
        try {
            //根据token获取用户对象
            UserAccount account = new UserAccount();
            account.setToken(token);
            account = userMapper.findByToken(account);
            if (account == null) {
                return new Result(ResultCode.USER_TOKEN_INVALID);
            }
            //验证支付密码
            if(!account.getPayPassword().equals(EncryptUtil.getEncodeStr(payPassword))){
                return new Result(ResultCode.USER_PAYPASSWOED_ERROR);
            }
            //获取用户余额信息
            BalanceAndFreeze balanceAndFreeze = userWalletMapper.findBalance(token);
            if (balanceAndFreeze == null) {
                return new Result(ResultCode.USER_BALANCE_FAIL);
            }
            //提现金额与用户余额进行比对
            if (balanceAndFreeze.getBalance().compareTo(withdrawApplicationPO.getApplicationMoney()) == -1) {
                return new Result(ResultCode.USER_WITHDRAW_FAIL);
            }
            //判断最小提现限额
            if (withdrawApplicationPO.getApplicationMoney().compareTo(GeneralConstants.MINIMAL_WITHDRAW_QUOTA) == -1) {
                return new Result(ResultCode.WITHDRQW_MINIMAL_QUOTA);
            }
            //处理平台手续费与扣税金额最终得到实到金额
            BigDecimal reallyMoney = withdrawApplicationPO.getApplicationMoney();
            //如果提现金额大于等于2000手续费是1块，小于2000就是5块
            if (withdrawApplicationPO.getApplicationMoney().compareTo(GeneralConstants.WITHDRAW_QUOTA) == 1 || withdrawApplicationPO.getApplicationMoney().compareTo(GeneralConstants.WITHDRAW_QUOTA) == 0) {
                withdrawApplicationPO.setPlatformFee(GeneralConstants.FEE_ONE);
                reallyMoney = ArithUtil.sub(reallyMoney, GeneralConstants.FEE_ONE);
            } else {
                withdrawApplicationPO.setPlatformFee(GeneralConstants.FEE_FIVE);
                reallyMoney = ArithUtil.sub(reallyMoney, GeneralConstants.FEE_FIVE);
            }
            //处理扣税（保留小数后两位）
            BigDecimal taxMoney = ArithUtil.round(ArithUtil.mul(withdrawApplicationPO.getApplicationMoney(), GeneralConstants.WITHDRAW_TAX_PROPORTION), 2);
            withdrawApplicationPO.setTaxMoney(taxMoney);
            //得到实到金额
            reallyMoney = ArithUtil.sub(reallyMoney, taxMoney);
            withdrawApplicationPO.setReallyMoney(reallyMoney);
            //更新账户余额
            UserWalletPO userWalletPO = new UserWalletPO();
            userWalletPO.setUserId(account.getUserId());
            userWalletPO.setBalance(ArithUtil.sub(balanceAndFreeze.getBalance(), withdrawApplicationPO.getApplicationMoney()));
            int result = userWalletMapper.update(userWalletPO);
            //提交提现申
            withdrawApplicationPO.setUserId(account.getUserId());
            int rel = withdrawApplicationMapper.save(withdrawApplicationPO);
            //添加用户交易流水
            UserTransactionRecodPO userTransactionRecodPO=new UserTransactionRecodPO();
            userTransactionRecodPO.setUserId(account.getUserId());
            userTransactionRecodPO.setTransactionType(3);
            userTransactionRecodPO.setTransactionAmount(withdrawApplicationPO.getApplicationMoney());
            userTransactionRecodPO.setExpenditureIncome(0);
            userTransactionRecodPO.setPayMethod(4);
            userTransactionRecodPO.setTransactionDetails("用户申请提现");
            //支付通道费用
            //userTransactionRecodPO.setPayCanalFee();
            //第三方流水号，退款时使用
            //userTransactionRecodPO.setSerialNumber();
            int UTRMRel = userTransactionRecodMapper.save(userTransactionRecodPO);

            //返回处理结果
            if (result == 1 && rel == 1&&UTRMRel==1) {
                return new Result(ResultCode.SYS_SUCCESS);
            } else {
                return new Result(ResultCode.SYS_FAIL);
            }
        } catch (Exception e) {
            System.out.println("ERROR TO WITHDRAWAPPLICATOINACTIVITY OF:" + e);
        }
        return new Result(ResultCode.SYS_FAIL);
    }
}
