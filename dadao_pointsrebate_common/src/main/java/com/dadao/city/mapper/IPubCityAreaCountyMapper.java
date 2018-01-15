package com.dadao.city.mapper;

import com.dadao.city.entity.CityAreaCountyPO;

import java.util.List;

/**
 * Created by NFY on 2017-08-04.
 */
public interface IPubCityAreaCountyMapper {

    List findAllCityList();

    /**
     * 通过名称查询下级市区
     * @param cityAreaCountyPO
     * @return
     */
    List findLowerCityById(CityAreaCountyPO cityAreaCountyPO);

    /**
     * 通过id查询下级市区
     * @param cityAreaCountyPO
     * @return
     */
    List findLowerCityByName(CityAreaCountyPO cityAreaCountyPO);

    /**
     * 查询省、直辖市列表
     * @return
     */
    List findAllProvinceList();

}
