package com.dadao.push.activity;

import com.dadao.push.entity.InformationPO;
import com.dadao.push.mapper.InformationMapper;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author YunQiang
 */
@Repository
public class MessageActivity {

    /**
     * 活动消息状态码
     */
    private static final List<Integer> PROMOTION_MESSAGE_UNREAD = Arrays.asList(6, 7, 8);

    @Autowired
    private InformationMapper informationMapper;

    /**
     * 查询已推送消息列表
     * @param userType -1为用户，-2为商户
     * @param pageNum 起始下标
     * @param pageSize 查询数据量
     * @return
     */
    public QueryResult listInformation(long userType, Long pageNum, Integer pageSize){
        Long totalSize = informationMapper.countByMessageType(userType, PROMOTION_MESSAGE_UNREAD);
        Long totalPage = DaDaoUtil.getTotalPage(totalSize, pageSize);
        pageNum = DaDaoUtil.dealWithPageNum(pageNum, totalPage);
        Long beginIndex = DaDaoUtil.getBeginIndex(pageNum, pageSize);
        List<InformationPO> data = informationMapper.selectByMessageType(userType, PROMOTION_MESSAGE_UNREAD, beginIndex.intValue(), pageSize);

        QueryResult queryResult = new QueryResult();
        queryResult.setList(data);
        queryResult.setPageNum(pageNum.intValue());
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);

        return queryResult;
    }

    /**
     * 增加一条活动消息
     * @param informationPO 消息表实体类
     * @return
     */
    public int addInformation(InformationPO informationPO){
        return informationMapper.insertSelective(informationPO);
    }

    /**
     * 修改一条消息消息
     * @param informationPO 消息表实体类
     */
    public int updateInformation(InformationPO informationPO){
        return informationMapper.updateByPrimaryKeySelective(informationPO);
    }

    /**
     * 删除一条消息（软删除）
     * @param informationPO 消息表实体类
     * @return
     */
    public int deleteInformation(InformationPO informationPO){
        //软删除10为无效数据
        informationPO.setStatus(-10);
        return informationMapper.updateByPrimaryKeySelective(informationPO);
    }

    public InformationPO findMessageById(long infoId){
        return (InformationPO)informationMapper.selectByPrimaryKey(infoId);
    }

}
