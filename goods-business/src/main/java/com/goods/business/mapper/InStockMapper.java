package com.goods.business.mapper;

import com.goods.common.model.business.InStock;
import com.goods.common.model.business.InStockInfo;
import com.goods.common.vo.business.InStockDetailVO;
import com.goods.common.vo.business.InStockVO;
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

    InStockDetailVO selectInStockDetail(Long id);


    /**
     * 添加库存
     * @param inStockVO
     */
    void addIntoStock(InStockVO inStockVO);

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
}
