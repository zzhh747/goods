package com.goods.business.service;

import com.goods.common.model.business.Product;
import com.goods.common.vo.business.ProductVO;
import com.goods.common.vo.system.PageVO;

import java.util.List;

public interface ProductService {
    /**
     * 查询物资资料
     * @param pageNum
     * @param pageSize
     * @param name
     * @param categoryId
     * @param supplier
     * @param status
     * @return
     */
    PageVO<Product> findProductList(Integer pageNum, Integer pageSize, String name, String categoryId, String supplier, String status,String categorys);

    /**
     * 添加物资
     * @param productVO
     */
    void add(ProductVO productVO);

    /**
     * 数据回显
     * @param id
     * @return
     */
    Product edit(Integer id);

    /**
     * 修改数据
     * @param productVO
     */
    void update(ProductVO productVO);

    /**
     * 审核
     * @param id
     */
    void publish(Integer id);

    /**
     * 放入回收站
     * @param id
     */
    void remove(Integer id);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);


}
