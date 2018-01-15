package com.dadao.push.mapper;

import com.dadao.pub.mapper.PubAutoBaseMapper;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.entity.MessageCountPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author YunQiang
 */
public interface InformationMapper extends PubAutoBaseMapper {

    /**
     * 统计用户对消息的阅读情况
     * @param messageCountPO 用来查询消息的类，类中有详细信息
     * @return
     */
    long countMessageUnread(MessageCountPO messageCountPO);

    /**
     * 用户消息列表查询
     * @param userId 用户id
     * @param statusList 分类
     * @param beginIndex 起始下标
     * @param pageSize 数据量
     * @return 记录集合
     */
    List<InformationPO> selectByMessageType(@Param(value = "userId") long userId, @Param(value = "statusList") List<Integer> statusList, @Param(value = "beginIndex") int beginIndex,  @Param(value = "pageSize") int pageSize);

    /**
     * 总记录条数统计
     * @param userId 用户id
     * @param statusList 状态
     * @return 记录条数
     */
    long countByMessageType(@Param(value = "userId") long userId, @Param(value = "statusList") List<Integer> statusList);

    /**
     * 修改一类消息为已读
     * @param userId 用户Id
     * @param statusList 状态
     * @return 修改条数
     */
    int updateMessageByType(@Param(value = "userId") long userId, @Param(value = "statusList") List<Integer> statusList);

    /**
     * 找到最新创建活动消息
     * @param statusList 消息状态
     * @return
     */
    Date selectMaxCreateTimeMessageByType(@Param(value = "statusList") List<Integer> statusList);

}
