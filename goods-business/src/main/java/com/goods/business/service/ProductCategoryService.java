package com.goods.business.service;

import com.github.pagehelper.PageInfo;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {

    /**
     * 查询物资分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO<ProductCategoryTreeNodeVO> findConsumerList(Integer pageNum, Integer pageSize);

    /**
     * 查询父分类
     * @return
     */
    List<ProductCategoryTreeNodeVO> findParentSort();

    /**
     * 添加分类
     * @param productCategoryVO
     */
    void add(ProductCategoryVO productCategoryVO);

    /**
     * 删除分类信息
     * @param productCategoryId
     */
    void delete(Integer productCategoryId);

    /**
     * 更新分类，数据回显
     * @param productCategoryId
     */
    ProductCategoryVO edit(Integer productCategoryId);

    /**
     * 更新分类数据
     * @param productCategoryVO
     */
    void update(ProductCategoryVO productCategoryVO);
}
