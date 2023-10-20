package com.goods.business.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.ProductCategoryMapper;
import com.goods.business.service.ProductCategoryService;
import com.goods.common.model.system.Department;
import com.goods.common.utils.CategoryTreeBuilder;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:com.goods.service.impl
 *
 * @author:张昊
 * @time:2023/10/17 14:30
 * description:
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public PageVO<ProductCategoryTreeNodeVO> findConsumerList(Integer pageNum, Integer pageSize) {
        //查询所有分类
        List<ProductCategoryTreeNodeVO> list = productCategoryMapper.findAllProductCategory();
        //PageHelper.startPage(pageNum,pageSize);
        List<ProductCategoryTreeNodeVO> build = CategoryTreeBuilder.build(list);
        if (null == pageNum && null == pageSize){
            PageInfo<ProductCategoryTreeNodeVO> info = new PageInfo<>(build);
            PageVO<ProductCategoryTreeNodeVO> pageInfo = new PageVO<>(info.getTotal(),build);
            return pageInfo;
        }
        List<ProductCategoryTreeNodeVO> page = ListPageUtils.page(build, pageSize, pageNum);

        PageInfo<ProductCategoryTreeNodeVO> info = new PageInfo<>(build);
        PageVO<ProductCategoryTreeNodeVO> pageInfo = new PageVO<>(info.getTotal(),page);
        return pageInfo;
    }

    @Override
    public List<ProductCategoryTreeNodeVO> findParentSort() {
        //查询所有分类
        List<ProductCategoryTreeNodeVO> list = productCategoryMapper.findAllProductCategory();
        //返回一级二级分类
        List<ProductCategoryTreeNodeVO> parentList = CategoryTreeBuilder.buildParent(list);
        return parentList;
    }

    @Override
    public void add(ProductCategoryVO productCategoryVO) {
        productCategoryMapper.insert(productCategoryVO);
    }

    @Override
    public void delete(Integer productCategoryId) {
        productCategoryMapper.delete(productCategoryId);
    }

    @Override
    public ProductCategoryVO edit(Integer productCategoryId) {
        return productCategoryMapper.findProductCategoryVOById(productCategoryId);
    }

    @Override
    public void update(ProductCategoryVO productCategoryVO) {
        productCategoryMapper.update(productCategoryVO);
    }
}
