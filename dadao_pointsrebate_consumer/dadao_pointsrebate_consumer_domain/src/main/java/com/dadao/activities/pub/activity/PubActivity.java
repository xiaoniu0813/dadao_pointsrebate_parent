package com.dadao.activities.pub.activity;

import com.dadao.category.entity.Category;
import com.dadao.category.mapper.CategoryMapper;
import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.city.mapper.IPubCityAreaCountyMapper;
import com.dadao.city.mapper.PubCityMapper;
import com.dadao.constants.GeneralConstants;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
@Repository
public class PubActivity {

    @Autowired
    private PubCityMapper pubCityMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private IPubCityAreaCountyMapper pubCityAreaCountyMapper;

    /**
     * @Author: yangrui
     * @Description: 省市
     * @Date: 下午2:45 2017/7/16
     */
    public List findByPid(int pid) {
        return pubCityMapper.findByPid(pid);
    }

    public QueryResult findByPage(Category category) {
//        category.setType(GeneralConstants.INDEX_CATEGORY);
        QueryResult queryResult = new QueryResult();
        Long totalSize = categoryMapper.findCount(category);
        List list = categoryMapper.findByPage(category);
        Long totalPage = totalSize % category.getPageSize() == 0 ? totalSize / category.getPageSize() : totalSize / category.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }
    /**
     * 返回所有城市列表(带经纬度)
     * @return
     */
    public List findAllCityList(){
        return pubCityAreaCountyMapper.findAllCityList();
    }


    /**
     * 查找下级市区
     * @return
     */
    public Result findLowerCity(CityAreaCountyPO cityAreaCountyPO){
        cityAreaCountyPO.getAreaname();
        //1.如果前台输入areaname,且参数id为空
        if(cityAreaCountyPO.getAreaname() != null){
            return new Result(ResultCode.SYS_SUCCESS,pubCityAreaCountyMapper.findLowerCityByName(cityAreaCountyPO));
        }
        //2.如果前台输入为空,且参数id为空,默认参数为北京
        if(cityAreaCountyPO.getId() != null){
            //3.按照城市id
            return new Result(ResultCode.SYS_SUCCESS, pubCityAreaCountyMapper.findLowerCityById(cityAreaCountyPO));
        }
        cityAreaCountyPO.setAreaname("北京");
        return new Result(ResultCode.SYS_SUCCESS,pubCityAreaCountyMapper.findLowerCityByName(cityAreaCountyPO));
    }

}
