package com.goods.business.mapper;

import com.goods.common.model.business.Product;
import com.goods.common.vo.business.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询物资
     * @param name
     * @param supplier
     * @param status
     * @return
     */
    List<Product> selectProductList(@Param("name") String name,
                                    @Param("supplier") String supplier,
                                    @Param("status") String status,
                                    @Param("category1") String category1,
                                    @Param("category2") String category2,
                                    @Param("category3") String category3
                                    );

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
    void publish(@Param("id") Integer id);

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

    /**
     * 查找物资
     * @param productId
     * @return
     */
    Product selectProduct(Long productId);

    Product selectProductByPNum(String pNum);
}
