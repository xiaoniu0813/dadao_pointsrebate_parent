package com.dadao.refunds.activity;

import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.RefundsApplicationMapper;
import com.dadao.refunds.mapper.entity.RefundsMessage;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefundsApplicationActivity {

    @Autowired
    private RefundsApplicationMapper refundsApplicationMapper;

    /**
     * 根据orderId查询出退款详情
     * @param orderId
     * @return
     */
    public RefundsApplicationPO findByRefundsInfo(long orderId){
        return (RefundsApplicationPO) refundsApplicationMapper.findById(orderId);
    }

    /**
     *用户退款申请列表
     * @param refundsMessage 退款专用实体类
     * @param pageNum 起始页
     * @param pageSize 页面大小
     * @return
     */
    public Result findByConditions(RefundsMessage refundsMessage, Long pageNum, int pageSize){
        //1、总记录数
        long totalRecord = refundsApplicationMapper.findByConditionsCount(refundsMessage);
        //2、总页数
        long totalPage = DaDaoUtil.getTotalPage(totalRecord, refundsMessage.getPageSize());
        //3、处理pageNum
        pageNum = DaDaoUtil.dealWithPageNum(pageNum, totalPage);
        //4、limit下标
        Long beginIndex = DaDaoUtil.getBeginIndex(pageNum, refundsMessage.getPageSize());
        refundsMessage.setBeginIndex(beginIndex.intValue());
        //数据
        List<RefundsMessage> list = refundsApplicationMapper.findByConditions(refundsMessage);
        QueryResult queryResult = new QueryResult();
        queryResult.setTotalSize(totalRecord);
        queryResult.setTotalPage(totalPage);
        queryResult.setPageNum(pageNum.intValue());
        queryResult.setList(list);
        queryResult.setTotalSize(totalRecord);
        return new Result(ResultCode.SYS_SUCCESS, queryResult);
    }

}
