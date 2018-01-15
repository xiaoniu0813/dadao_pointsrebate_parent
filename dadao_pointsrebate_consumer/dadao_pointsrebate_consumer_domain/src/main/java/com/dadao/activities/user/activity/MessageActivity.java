package com.dadao.activities.user.activity;

import com.dadao.dto.MessageUnread;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.entity.MessageCountPO;
import com.dadao.push.mapper.InformationMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author YunQiang
 */
@Repository
public class MessageActivity {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InformationMapper informationMapper;

    /**
     * 系统消息状态码
     */
    private static final List<Integer> SYSTEM_MESSAGE_UNREAD = Arrays.asList(4, 5);

    /**
     * 交易消息状态码
     */
    private static final List<Integer> TRADE_MESSAGE_UNREAD = Arrays.asList(0, 1, 2, 3);

    /**
     * 活动消息状态码
     */
    private static final List<Integer> PROMOTION_MESSAGE_UNREAD = Arrays.asList(6, 7, 8);

    /**
     * 统计用户对消息的阅读情况
     *
     * @param token 用户token
     * @return
     */
    public MessageUnread findMessageUnread(String token) {
        //拿到userId
        long userId = userMapper.findByToken(new UserAccount(token)).getUserId();

        MessageCountPO messageCountPO = new MessageCountPO();
        messageCountPO.setUserId(userId);
        messageCountPO.setHaveRead(0);

        //系统消息：SystemMessageUnread（标识：status为“4、5 ”）
        messageCountPO.setStatusList(SYSTEM_MESSAGE_UNREAD);
        long systemMessageUnread = informationMapper.countMessageUnread(messageCountPO);

        //交易消息：TradeMessageUnread （标识：status为“0、1、2、3 ”）
        messageCountPO.setStatusList(TRADE_MESSAGE_UNREAD);
        long tradeMessageUnread = informationMapper.countMessageUnread(messageCountPO);

        //活动消息：PromotionMessageUnread（标识：status为“6、7、8 ”）
        Date maxPromotionMessageTime = informationMapper.selectMaxCreateTimeMessageByType(PROMOTION_MESSAGE_UNREAD);
        long promotionMessageUnread = maxPromotionMessageTime == null ? 0 : maxPromotionMessageTime.getTime();

        return new MessageUnread(systemMessageUnread, tradeMessageUnread, promotionMessageUnread);
    }

    /**
     * 用户消息列表查询
     * @param token    用户token
     * @param status   系统消息：status为0、交易消息：status为1、活动消息：status为2
     * @param pageNum  第几页
     * @param pageSize 页面大小
     * @return 记录集合
     */
    public QueryResult findMessageList(String token, int status, Long pageNum, int pageSize) {
        List<Integer> messageType = new ArrayList<>();
        //拿到userId
        UserAccount userAccount = userMapper.findByToken(new UserAccount(token));

        if (status == 0) {
            messageType = SYSTEM_MESSAGE_UNREAD;
            //更新所有系统消息为已读
            informationMapper.updateMessageByType(userAccount.getUserId(), messageType);
        }
        if (status == 1) {
            messageType = TRADE_MESSAGE_UNREAD;
            //更新所有交易消息为已读
            informationMapper.updateMessageByType(userAccount.getUserId(), messageType);
        }
        if (status == 2) {
            messageType = PROMOTION_MESSAGE_UNREAD;
            //用户活动
            userAccount.setUserId(-1L);
            //商户活动
            if(userAccount.getMerchant() == 1){
                userAccount.setUserId(-2L);
            }
        }

        long totalSize = informationMapper.countByMessageType(userAccount.getUserId(), messageType);
        long totalPage = DaDaoUtil.getTotalPage(totalSize, pageSize);
        pageNum = DaDaoUtil.dealWithPageNum(pageNum, totalPage);
        Long beginIndex = DaDaoUtil.getBeginIndex(pageNum, pageSize);
        List<InformationPO> data = informationMapper.selectByMessageType(userAccount.getUserId(), messageType, beginIndex.intValue(), pageSize);

        QueryResult queryResult = new QueryResult();
        queryResult.setTotalSize(totalSize);
        queryResult.setTotalPage(totalPage);
        queryResult.setPageNum(pageNum.intValue());
        queryResult.setList(data);

        return queryResult;
    }

    /**
     * 消息详情
     * @param token 用户token
     * @param infoId 消息infoId
     * @return
     */
    public InformationPO detailMessage(String token, long infoId) {
        long userId = userMapper.findByToken(new UserAccount(token)).getUserId();
        InformationPO information = (InformationPO) informationMapper.selectByPrimaryKey(infoId);

        //userId为-1时代表消息是公共消息，无需权限
        if (information.getUserId() == userId || information.getUserId() == -1) {
            //如果消息为未读，标记为已读
            if(information.getHaveRead() == 0){
                InformationPO informationPO = new InformationPO();
                informationPO.setHaveRead(1);
                informationPO.setInfoId(infoId);
                informationMapper.updateByPrimaryKeySelective(informationPO);
            }
            return information;
        } else {
            return null;
        }
    }

    /**
     * 更新消息状态为已读
     * @param token
     * @param infoId
     * @return
     */
    public int updateMessageHaveRead(String token, long infoId) {
        long userId = userMapper.findByToken(new UserAccount(token)).getUserId();
        InformationPO information = (InformationPO) informationMapper.selectByPrimaryKey(infoId);

        if (information.getUserId() == userId){
            InformationPO informationPO = new InformationPO();
            informationPO.setHaveRead(1);
            informationPO.setInfoId(infoId);
            return informationMapper.updateByPrimaryKeySelective(informationPO);
        }

        return 0;
    }

}
