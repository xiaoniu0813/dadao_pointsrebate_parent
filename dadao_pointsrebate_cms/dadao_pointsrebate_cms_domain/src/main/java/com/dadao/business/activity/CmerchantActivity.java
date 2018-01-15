package com.dadao.business.activity;

import com.dadao.business.entity.Cmerchant;
import com.dadao.business.mapper.CmerchantMapper;
import com.dadao.utils.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by GUOYU 2018/01/09
 */
@Repository
public class CmerchantActivity {

    @Autowired
    private CmerchantMapper cmerchantMapper;

    public BasePage findByAll(Cmerchant merchant){
        System.out.println(merchant);
        BasePage basePage = new BasePage();
        //当前页
        int pageNum = 1;
        if(merchant.getPageNum() != null){
            pageNum = merchant.getPageNum();
        }

        //分页大小
        int pageSize = 10;
        if(merchant.getPageSize() != null){
            pageSize = merchant.getPageSize();
        }
        int beginIndex = (pageNum - 1) * pageSize;
        //数据库索引
        basePage.setBeginIndex(Long.valueOf(beginIndex));
        //当前页
        basePage.setPageNum(pageNum);
        //分页大小
        basePage.setPageSize(pageSize);
        //查找出总数
        int total = cmerchantMapper.findTotal(merchant);
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        //总页数
        basePage.setTotalPage(Long.valueOf(totalPage));
        //总条数
        basePage.setTotalSize(Long.valueOf(total));
        merchant.setBeginIndex(Long.valueOf(beginIndex));
        merchant.setPageSize(pageSize);
        List<Cmerchant> list = cmerchantMapper.findByAll(merchant);
        //数据集合
        basePage.setList(list);
        return basePage;
    }

}
