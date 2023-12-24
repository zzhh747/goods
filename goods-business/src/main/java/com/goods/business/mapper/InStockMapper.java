package com.goods.business.mapper;

import com.goods.common.model.business.InStock;
import com.goods.common.model.business.InStockInfo;
import com.goods.common.model.business.ProductStock;
import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.InStockDetailVO;
import com.goods.common.vo.business.InStockVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface InStockMapper {
    /**
     * 查询库存
     * @param status
     * @param type
     * @param inNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<InStock> selectInStockList(@Param("status") String status,
                                    @Param("type") String type,
                                    @Param("inNum") String inNum,
                                    @Param("startTime") Date startTime,
                                    @Param("endTime") Date endTime);

    InStockDetailVO selectInStockDetail(Long id);


    /**
     * 添加库存
     * @param inStock
     */
    void addIntoStock(InStock inStock);

    /**
     * 添加库存信息
     * @param inStockInfo
     */
    void addStockInfo(InStockInfo inStockInfo);

    /**
     * 审核
     * @param id
     */
    void publish(Integer id);

    /**
     * 查询提供方
     * @param supplierId
     * @return
     */
    Supplier selectSupplier(Long supplierId);

    /**
     * 放入回收站
     * @param id
     */
    void remove(Integer id);


    InStock selectInStock(Long id);

    List<InStockInfo> selectInStockInfoList(String inNum);

    ProductStock selectProductStock(String pNum);

    void updateProductStock(Integer stock, String pNum);

    void addProductStock(Integer stock, String pNum);


    List<ProductStock> selectProductStockById(Integer id);


    /**
     * 通过
     * @param inStock
     * @return
     */
}
