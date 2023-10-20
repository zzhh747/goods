package com.goods.business.service;

import com.goods.common.model.business.InStock;
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
     * 查看明细
     * @return
     */
    InStock findStockDetail(Integer id);
}
