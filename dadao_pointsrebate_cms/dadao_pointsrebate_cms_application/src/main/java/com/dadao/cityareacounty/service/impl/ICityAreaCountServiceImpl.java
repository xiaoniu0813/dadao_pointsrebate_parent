package com.dadao.cityareacounty.service.impl;

import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.cityareacounty.activity.CityAreaCountyActivity;
import com.dadao.cityareacounty.service.ICityAreaCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Service
public class ICityAreaCountServiceImpl implements ICityAreaCountService {

    @Autowired
    private CityAreaCountyActivity cityAreaCountyActivity;

    @Override
    public List findAllProvinceList() {
        return cityAreaCountyActivity.findAllProvinceList();
    }

    @Override
    public List findLowerCityById(CityAreaCountyPO cityAreaCountyPO) {
        return cityAreaCountyActivity.findLowerCityById(cityAreaCountyPO);
    }
}
