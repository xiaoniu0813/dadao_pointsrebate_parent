package com.dadao.merchants.service;

import com.dadao.merchants.entity.MerchantsSort;

import java.util.List;

/**
 * Created by NFY on 2017-11-13.
 */
public interface IMerchantsService {
    List findSort(MerchantsSort merchantsSort);
}
