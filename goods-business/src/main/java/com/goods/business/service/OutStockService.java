package com.goods.business.service;

import com.goods.common.vo.business.OutStockVO;
import com.goods.common.vo.system.PageVO;

public interface OutStockService {
    /**
     * 查询发放记录
     * @param pageNum
     * @param pageSize
     * @param status
     * @param outNum
     * @param type
     * @return
     */
    PageVO<OutStockVO> findOutStockList(Integer pageNum, Integer pageSize, String status, String outNum, Integer type);

    /**
     * 出库保存
     * @param outStockVO
     */
    void addOutStock(OutStockVO outStockVO);

    /**
     * 出库审批
     * @param id
     */
    void publish(Integer id);
}
