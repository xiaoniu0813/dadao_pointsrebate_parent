package com.dadao.category.activity;

import com.dadao.category.entity.Category;
import com.dadao.category.entity.CategoryPO;
import com.dadao.category.mapper.CategoryMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guoyu on 2017/7/17.
 */
@Repository
public class CategoryActivity {

    @Autowired
    private CategoryMapper categroyMapper;


    /**
     * 分类查询分页
     * @param category
     * @return
     */
    public QueryResult findByPage(Category category){
        QueryResult queryResult = new QueryResult();
        Long totalSize = categroyMapper.findCount(category);
        List list = categroyMapper.findByPage(category);
        Long totalPage = totalSize % category.getPageSize() == 0 ? totalSize / category.getPageSize() : totalSize / category.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    public Integer updateById(Category category){
        return categroyMapper.update(category);
    }

    /**
     * 添加种类
     * @param categoryPo
     * @return
     */
    public Integer save(CategoryPO categoryPo){
        //判断添加的是父类还是子类 如果是父类
        if(categoryPo.getParentId() == -1){
            //找出父类的最大sequence
            int max = categroyMapper.MaxSequence(categoryPo);
            System.out.println("最大的值：" + (max + 1));
            categoryPo.setSequence(max + 1);
            int a = categroyMapper.save(categoryPo);
            return a;
        }
        //子类没有排序为0
        categoryPo.setSequence(0);
        //添加
        int a = categroyMapper.save(categoryPo);
        return a;
    }

    /**
     * 修改该分类的图标
     * @param category
     * @return
     */
    public int updateCategoryPicture(Category category){
        int a = categroyMapper.updateCategoryPicture(category);
        return a;
    }

    /**
     * 上移
     * @param categoryId
     * @return
     */
    @Transactional
    public int moveUP(int categoryId){
        //根据当前categoryId查找出当前Sequence
        int atPresentSequence = categroyMapper.findCategoryBySequence(categoryId);
        //根据当前sequence查找上一个sequence
        int previousSequence = atPresentSequence - 1;
        //查询上一个categoryId
        int previousCategoryId = categroyMapper.findCategoryByCategoryId(previousSequence);
        Category category = new Category();
        //将当前的sequence修改为上一个sequence值
        category.setCategoryId(categoryId);
        category.setSequence(previousSequence);
        int updateAtPresent = categroyMapper.updateCategorySequence(category);
        //将上一条记录的sequence修改为当前sequence
        category.setCategoryId(previousCategoryId);
        category.setSequence(atPresentSequence);
        int updatePrevious = categroyMapper.updateCategorySequence(category);
        if(updateAtPresent == 1 && updatePrevious == 1){
            return 1;
        }else{
            return 0;
        }

    }

}

