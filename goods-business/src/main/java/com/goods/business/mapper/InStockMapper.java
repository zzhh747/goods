package com.goods.business.mapper;

import com.goods.common.model.business.InStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
                                    @Param("startTime") String startTime,
                                    @Param("endTime") String endTime);

    InStock selectStockDetail(Integer id);
}
