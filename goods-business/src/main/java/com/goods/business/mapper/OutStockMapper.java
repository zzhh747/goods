package com.goods.business.mapper;

import com.goods.common.model.business.OutStock;
import com.goods.common.model.business.ProductStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutStockMapper {

    /**
     * 查询出库记录
     * @param status
     * @param type
     * @return
     */
    List<OutStock> selectOutStockList(@Param("status") String status,@Param("outNum") String outNum,@Param("type") Integer type);

    void addOutStock(OutStock outStock);

    void publish(Integer id);

    void updateProductStock(int stock, String pNum);

    List<ProductStock> selectProductStockById(Integer id);
}
