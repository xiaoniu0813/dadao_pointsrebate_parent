package com.dadao.activities.cashback.activity;

import com.dadao.cashback.mapper.ICashbackDetailsMapper;
import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CashbackDetailsActivity {

    @Autowired
    private ICashbackDetailsMapper cashbackDetailsMapper;

    /**
     * 查找返现记录
     * @param cashbackDetailsVO
     * @return
     */
    public QueryResult findByStatus(CashbackDetailsVO cashbackDetailsVO, String token, Integer pageNum){
        HashMap hashMap = new HashMap();
        hashMap.put("cashDetails",cashbackDetailsVO);
        hashMap.put("token",token);
        hashMap.put("beginIndex", (pageNum - 1) * cashbackDetailsVO.getPageSize());
        hashMap.put("pageSize", cashbackDetailsVO.getPageSize());
        Integer totalSize = cashbackDetailsMapper.findStatusCount(hashMap);
        List<Map> data = cashbackDetailsMapper.findByStatus(hashMap);
        Integer totalPage = totalSize % cashbackDetailsVO.getPageSize() == 0 ? totalSize / cashbackDetailsVO.getPageSize() : totalSize / cashbackDetailsVO.getPageSize() + 1;
        QueryResult queryResult = new QueryResult();
        queryResult.setPageNum(pageNum);
        queryResult.setTotalPage(totalPage.longValue());
        queryResult.setTotalSize(totalSize.longValue());
        queryResult.setList(data);
        return queryResult;
    }

    /**
     * 累计返利金额、等返期数
     * @param cashbackDetailsVO
     * @return
     */
    public Map findMessageByStatus(CashbackDetailsVO cashbackDetailsVO, String token){
        HashMap hashMap = new HashMap();
        hashMap.put("cashDetails",cashbackDetailsVO);
        hashMap.put("token",token);
        BigDecimal countReally = cashbackDetailsMapper.countReally(hashMap);
        Integer waitCashback = cashbackDetailsMapper.countWaitCashback(hashMap);
        if(countReally == null)
            countReally = new BigDecimal(0);
        if (waitCashback == null)
            waitCashback = 0;
        Map map = new HashMap();
        map.put("countReally",countReally);
        map.put("waitCashback",waitCashback);
        return map;
    }

}
