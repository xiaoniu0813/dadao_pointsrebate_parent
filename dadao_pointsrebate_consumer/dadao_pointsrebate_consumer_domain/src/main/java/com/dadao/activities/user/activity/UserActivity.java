package com.dadao.activities.user.activity;

import com.dadao.shop.entity.BusinessApply;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.entity.UserWalletVO;
import com.dadao.user.mapper.UserInfoMapper;
import com.dadao.user.mapper.UserMapper;
import com.dadao.user.mapper.UserWalletMapper;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.EncryptUtil;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
@Repository
@Transactional
public class UserActivity {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserWalletMapper userWalletMapper;

    @Autowired
    private UserIntegralActivity userIntegralActivity;

    /**
     * @param userAccount
     * @return
     * @author zyq
     * 1.判断手机号是否已经被注册,注册返回true，未注册返回false
     */
    public boolean isExistByPhone(UserAccount userAccount) {
        if (userAccount.getPhone() == null && userAccount.getPhone() == "") {
            return false;
        }
        UserAccount user = new UserAccount();
        user.setPhone(userAccount.getPhone());
        user.setMerchant(userAccount.getMerchant());
        return userMapper.findByEntity(user).size() > 0;
    }

    /**
     * @param
     * @return
     * @author zyq
     * 2.短信验证码,1正确有效，2过期，3错误
     */
    public ResultCode isCodeValid(String phone, String code) {
        //（1）判断手机验证码是否有效
        boolean verification = DaDaoUtil.verification(phone, code);
        if (verification) {
            return null;
        } else {
            return ResultCode.CODE_FAIL;
        }
    }

    /**
     * @author zyq
     * 3.保存注册用户，使用EncryptUtil类加密用户密码
     */
    public Result register(UserAccount userAccount, String code) {
        String token = userAccount.getPhone() + ";" + System.currentTimeMillis();
        userAccount.setToken(EncryptUtil.getEncodeStr(token));
        userAccount.setPassword(EncryptUtil.getEncodeStr(userAccount.getPassword()));
        ResultCode resultCode;
        //（1）如果手机号被注册,返回错误代码4和原因
        if (this.isExistByPhone(userAccount)) {
            return new Result(ResultCode.REGISTER_EXIST_FAIL, userAccount.getPhone());
        }
        //（2）如果验证码不合格,返回相应错误代码和原因
        resultCode = isCodeValid(userAccount.getPhone(), code);
        if (resultCode != null) {
            return new Result(resultCode, null);
        } else {
            //（3）保存用户
            int key = userMapper.save(userAccount);
            //（4）将用户信息保存到userInfo中
            UserInfo userInfo = new UserInfo();
            //(5)传入用户默认昵称、头像
            if (userAccount.getMerchant() == 0) {
                userInfo.setNickname("大道用户" + userAccount.getPhone().substring(7, 11));
                userInfo.setPicture("http://dadao-file.oss-cn-beijing.aliyuncs.com/dadao/1503021445227.png");
            }
            if (userAccount.getMerchant() == 1) {
                userInfo.setNickname("大道商户" + userAccount.getPhone().substring(7, 11));
                userInfo.setPicture("http://dadao-file.oss-cn-beijing.aliyuncs.com/dadao/1503021478055.png");
            }
            userInfo.setUserId(userAccount.getUserId());
            userInfo.setPhone(userAccount.getPhone());
            userInfo.setCreateTime(new Date());
            userInfoMapper.save(userInfo);
            //(6)初始化用户钱包
            UserWalletVO userWalletVO = new UserWalletVO();
            userWalletVO.setStatus(1);
            userWalletVO.setUserId(userAccount.getUserId());
            userWalletMapper.save(userWalletVO);
            //(7)初始化用户商户积分钱包
            userIntegralActivity.saveUserIntrgral(userAccount);
            return new Result(ResultCode.SYS_SUCCESS, userAccount.getPhone());
        }

    }

    /**
     * 4.用户登录
     *
     * @return
     * @author zyq
     */
    public Result login(UserAccount userAccount, String code) {
        //0为用户，1为商户,否则就是非法登录
        if (userAccount.getMerchant() == null) {
            return new Result(ResultCode.USER_LOGIN_FAIL, null);
        }
        //同时输入code,和password
        if (userAccount.getPassword() != null && code != null) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, null);
        }
        if (code == null) {
            return this.loginByPassword(userAccount);
        } else {
            return this.loginByCode(userAccount, code);
        }
    }

    /**
     * 5.密码登录,供login调用
     *
     * @return
     * @author zyq
     */
    public Result loginByPassword(UserAccount userAccount) {
        String phone = userAccount.getPhone();
        String password = userAccount.getPassword();
        String token = "";
        Long userId = -1L;
        ResultCode resultCode = null;
        if (phone == null || "".equals(phone.trim()) || !phone.trim().matches("1\\d{10}")) {
            resultCode = ResultCode.LOGIN_PHONE_FAIL;
        }
        if (password == null || "".equals(password.trim())) {
            resultCode = ResultCode.LOGIN_PASSWORD_NULL;
        }
        //判断手机号是否已注册
        if (resultCode == null && !this.isExistByPhone(userAccount)) {
            resultCode = ResultCode.LOGIN_PHONE_NULL;
        }
        if (resultCode != null) {
            return new Result(resultCode, null);
        }
        userAccount.setPassword(EncryptUtil.getEncodeStr(userAccount.getPassword()));
        List list = userMapper.findByEntity(userAccount);
        boolean flag = list.size() == 1;
        if (!flag) {
            resultCode = ResultCode.LOGIN_ACCOUNT_FAIL;
        } else {
            token = ((UserAccount) list.get(0)).getToken();
            userId = ((UserAccount) list.get(0)).getUserId();
        }
        //判断登录是否成功
        if (resultCode == null) {
            return new Result(ResultCode.SYS_SUCCESS, dealLoginSuccess(userId, token, userAccount.getMerchant()));
        } else {
            return new Result(resultCode, null);
        }
    }

    /**
     * 6.验证码登录,供login调用
     *
     * @return
     * @author zyq
     */
    public Result loginByCode(UserAccount userAccount, String code) {
        String phone = userAccount.getPhone();
        String token = "";
        Long userId = -1L;
        ResultCode resultCode = null;
        //(1)判断手机号格式
        if (phone == null || "".equals(phone.trim()) || !phone.trim().matches("1\\d{10}")) {
            resultCode = ResultCode.LOGIN_PHONE_FAIL;
        }
        //(2)密码不能为空
        if (code == null || "".equals(code.trim())) {
            resultCode = ResultCode.CODE_NULL;
        }
        boolean verification = DaDaoUtil.verification(userAccount.getPhone(), code);
        //(3)判断验证码
        if (!verification) {
            resultCode = ResultCode.CODE_FAIL;
        } else {
            //(4)判断用户是否注册
            UserAccount user = (UserAccount) userMapper.findByEntity(userAccount).get(0);
            if (user == null) {
                //用户未注册
                resultCode = ResultCode.LOGIN_PHONE_NULL;
            } else {
                token = user.getToken();
                userId = user.getUserId();
            }
        }
        //（5）登录成功返回token,失败返回错误码
        if (resultCode == null) {
            return new Result(ResultCode.SYS_SUCCESS, dealLoginSuccess(userId, token, userAccount.getMerchant()));
        } else {
            return new Result(resultCode, null);
        }
    }

    /**
     * 登录成功处理
     * @param userId 用户id
     * @param token 用户token
     * @param merchant 0用户、1商户
     * @return
     */
    private HashMap<String,Object> dealLoginSuccess(long userId, String token, Integer merchant) {
        UserInfo userInfoLogin = (UserInfo) userInfoMapper.findById(userId);
        HashMap hashMap = new HashMap();
        hashMap.put("userId", userInfoLogin.getUserId());
        hashMap.put("token", token);
        hashMap.put("phone", userInfoLogin.getPhone());
        hashMap.put("nickname", userInfoLogin.getNickname());
        hashMap.put("picture", userInfoLogin.getPicture());
        hashMap.put("createTime", userInfoLogin.getCreateTime());
        //如果是商户登录成功，返回信息增加店铺状态
        if (merchant == 1){
            BusinessApply businessApply =  userMapper.findBusinessShopByUserId(userId);
            if (businessApply == null){
                hashMap.put("status", 0);
                hashMap.put("description", "还没有提交店铺资质");
            } else {
                int status = businessApply.getStatus();
                //审核中
                if (status == 0 || status == 1){
                    hashMap.put("status", 1);
                    hashMap.put("description", "审核中");
                }
                //审核失败
                if (status == 3 || status == 4 || status == 5) {
                    hashMap.put("status", 2);
                    hashMap.put("description", "审核失败");
                }
                //审核成功
                if(status == 2){
                    hashMap.put("status", 3);
                    hashMap.put("description", "可正常登入");
                }
            }
        }
        return hashMap;
    }


    /**
     * 重置登陆密码  ---niufy
     *
     * @return
     */
    public boolean resetPassword(UserAccount userAccount, String code) {
        //获得验证码验证
        boolean VResults = DaDaoUtil.verification(userAccount.getPhone(), code);
        if (VResults) {
            //根据token获取用户对象
            UserAccount uAccount = this.userMapper.findByToken(userAccount);
            if (uAccount != null) {
                uAccount.setPassword(EncryptUtil.getEncodeStr(userAccount.getPassword()));
                int results = this.userMapper.update(uAccount);
                return results == 1 ? true : false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 重置登陆密码(忘记密码)  ---niufy
     *
     * @return
     */
    public boolean forgetPassword(UserAccount userAccount, String code) {
        //获得验证码验证
        boolean VResults = DaDaoUtil.verification(userAccount.getPhone(), code);
        if (VResults) {
            //根据电话号码获取用户对象
            UserAccount uAccount = this.userMapper.findByPhone(userAccount);
            if (uAccount != null) {
                uAccount.setPassword(EncryptUtil.getEncodeStr(userAccount.getPassword()));
                int results = this.userMapper.update(uAccount);
                return results == 1 ? true : false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 设置支付密码  ---niufy
     *
     * @param userAccount
     * @return
     * @throws Exception
     */
    public boolean setupPayPassword(UserAccount userAccount) {

        //根据token获取用户对象
        UserAccount uAccount = this.userMapper.findByToken(userAccount);
        if (uAccount != null) {
            //判断设置支付密码之前支付密码必须为空
            if (uAccount.getPayPassword() == null || uAccount.getPayPassword() == "" || uAccount.getPayPassword().equals("0")) {
                uAccount.setPayPassword(EncryptUtil.getEncodeStr(userAccount.getPayPassword()));
                int results = this.userMapper.update(uAccount);
                return results == 1 ? true : false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 更换绑定手机  ---niufy
     *
     * @param userAccount
     * @param code
     * @return
     */
    public ResultCode updatePhone(UserAccount userAccount, String code) {
        if (userMapper.findByPhone(userAccount) != null) {
            return ResultCode.UPDATEPHONE_FAIL;
        }
        boolean VResults = DaDaoUtil.verification(userAccount.getPhone(), code);
        if (VResults) {
            //根据token获取用户对象
            UserAccount uAccount = this.userMapper.findByToken(userAccount);
            if (uAccount != null) {
                uAccount.setPhone(userAccount.getPhone());
                //更新用户账户信息
                int userUpdateResults = userMapper.update(uAccount);
                //用户信息赋值
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(uAccount.getUserId());
                userInfo.setPhone(userAccount.getPhone());
                //更新用户信息
                int userInfoUpdateResults = userInfoMapper.update(userInfo);

                if (userUpdateResults == 1 && userInfoUpdateResults == 1) {
                    return ResultCode.SYS_SUCCESS;
                } else {
                    return ResultCode.SYS_FAIL;
                }
            } else {
                return ResultCode.SYS_FAIL;
            }
        } else {
            return ResultCode.CODE_FAIL;
        }
    }

    /**
     * 重置支付密码  ---niufy
     *
     * @param userAccount
     * @param code
     * @return
     */
    public boolean resetPayPassword(UserAccount userAccount, String code) {
        boolean VResults = DaDaoUtil.verification(userAccount.getPhone(), code);
        if (VResults) {
            //根据token获取用户对象
            UserAccount uAccount = this.userMapper.findByToken(userAccount);
            if (uAccount != null) {
                uAccount.setPayPassword(EncryptUtil.getEncodeStr(userAccount.getPayPassword()));
                int results = this.userMapper.update(uAccount);
                return results == 1 ? true : false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 根据token查询用户信息判断是否已经设置了支付密码
     *
     * @param token
     * @return
     */
    public boolean judgmentPayPassword(String token) {
        UserAccount account = new UserAccount();
        account.setToken(token);
        UserAccount uAccount = userMapper.findByToken(account);
        if (uAccount != null) {
            if (uAccount.getPayPassword() != null && uAccount.getPayPassword() != "" && !uAccount.getPayPassword().equals("0")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 验证支付密码
     * @param token
     * @param payPassword
     * @return
     */
    public boolean verificationPayPassword(String token,String payPassword){
        UserAccount account = new UserAccount();
        account.setToken(token);
        UserAccount uAccount = userMapper.findByToken(account);
        if(uAccount!=null){
            if(EncryptUtil.getEncodeStr(payPassword).equals(uAccount.getPayPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean checkShopExist(String phone){
        if (phone == null || phone.trim().equals("")){
            return false;
        }
        return userMapper.findBusinessShop(phone) > 0 ? true : false;
    }

}
