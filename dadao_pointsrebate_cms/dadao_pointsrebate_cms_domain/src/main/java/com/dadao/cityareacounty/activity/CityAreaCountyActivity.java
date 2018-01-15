package com.dadao.cityareacounty.activity;

import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.city.mapper.IPubCityAreaCountyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询省市信息
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Repository
public class CityAreaCountyActivity {

    @Autowired
    private IPubCityAreaCountyMapper pubCityAreaCountyMapper;

    /**
     * 查询省、直辖市信息
     * @return
     */
    public List findAllProvinceList(){
        return pubCityAreaCountyMapper.findAllProvinceList();
    }

    /**
     * 根据ID查询下级市信息
     * @param cityAreaCountyPO
     * @return
     */
    public List findLowerCityById(CityAreaCountyPO cityAreaCountyPO){
        return pubCityAreaCountyMapper.findLowerCityById(cityAreaCountyPO);
    }
}
