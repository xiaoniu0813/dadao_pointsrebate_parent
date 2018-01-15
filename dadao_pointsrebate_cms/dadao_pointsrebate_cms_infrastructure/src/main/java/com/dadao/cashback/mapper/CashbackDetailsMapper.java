package com.dadao.cashback.mapper;

import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.cashback.entity.CashbackList;
import com.dadao.pub.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CashbackDetailsMapper extends BaseMapper {

    List findEntityByUserId(Serializable id);

    Long findUserIdCount(Object object);

    Double findUserCashBackMoney(Object object);

    List findThedayCashback();

    Long findTobebackCashbackCount(Long recordId);

}
