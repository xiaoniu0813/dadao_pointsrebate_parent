package com.dadao.push.service;

import com.dadao.push.entity.InformationPO;
import com.dadao.utils.Result;

/**
 * @author YunQiang
 */
public interface IMessageService {

    /**
     * 查询已推送消息列表
     * @param userType -1为用户，-2为商户
     * @param pageNum 起始下标
     * @param pageSize 查询数据量
     * @return
     */
    Result listInformation(long userType, Long pageNum, Integer pageSize);

    /**
     * 增加一条活动消息
     * @param informationPO 消息表实体类
     * @return
     */
    Result addInformation(InformationPO informationPO);

    /**
     * 修改一条消息消息
     * @param informationPO 消息表实体类
     */
    Result updateInformation(InformationPO informationPO);

    /**
     * 删除一条消息（软删除）
     * @param informationPO 消息表实体类
     * @return
     */
    Result deleteInformation(InformationPO informationPO);

    /**
     * 消息详情
     * @param infoId 消息id
     * @return
     */
    Result detailMessage(long infoId);

}
