package com.goods.business.mapper;

import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProductCategoryMapper {

    /**
     * 查询所有分类
     * @return
     */
    List<ProductCategoryTreeNodeVO> findAllProductCategory();

    /**
     * 查询所有一级分类数据列表
     * @return
     */
    List<ProductCategoryTreeNodeVO> findProductCategory1List();

    /**
     * 查询所有二级分类数据列表
     * @return
     */
    ArrayList<ProductCategoryTreeNodeVO> findProductCategory2List();

    /**
     * 查询所有三级分类数据列表
     * @return
     */
    ArrayList<ProductCategoryTreeNodeVO> findProductCategory3List();

    /**
     * 添加分类
     * @param productCategoryVO
     */
    void insert(ProductCategoryVO productCategoryVO);

    /**
     * 删除分类
     * @param productCategoryId
     */
    void delete(Integer productCategoryId);

    /**
     * 根据id查询数据
     * @param productCategoryId
     * @return
     */
    ProductCategoryVO findProductCategoryVOById(Integer productCategoryId);

    /**
     * 更新分类信息
     * @param productCategoryVO
     */
    void update(ProductCategoryVO productCategoryVO);
}
