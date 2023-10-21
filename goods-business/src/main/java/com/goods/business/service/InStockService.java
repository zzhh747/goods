package com.goods.business.service;

import com.goods.common.model.business.InStock;
import com.goods.common.model.business.InStockInfo;
import com.goods.common.vo.business.InStockDetailVO;
import com.goods.common.vo.business.InStockVO;
import com.goods.common.vo.system.PageVO;

public interface InStockService {
    /**
     * 查询入库记录
     * @param pageNum
     * @param pageSize
     * @param status
     * @param type
     * @param inNum
     * @param startTime
     * @param endTime
     * @return
     */
    PageVO<InStock> findInStockList(Integer pageNum, Integer pageSize, String status, String type, String inNum, String startTime, String endTime);



    /**
     * 入库
     * @param inStockVO
     */
    void addIntoStock(InStockVO inStockVO);

    /**
     * 审核
     * @param id
     */
    void publish(Integer id);

    /**
     * 入库明细
     * @param id
     * @param pageNum
     * @return
     */
     InStockDetailVO findInStockDetail(Long id, Long pageNum);
}
