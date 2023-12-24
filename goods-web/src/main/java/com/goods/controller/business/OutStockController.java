package com.goods.controller.business;

import com.goods.business.service.OutStockService;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.InStockVO;
import com.goods.common.vo.business.OutStockVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName:com.goods.controller.business
 *
 * @author:张昊
 * @time:2023/10/23 18:04
 * description:
 */
@RestController
@RequestMapping("/business/outStock")
public class OutStockController {
    @Autowired
    private OutStockService outStockService;

    @GetMapping("findOutStockList")
    public ResponseBean findOutStockList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(value = "status", required = false) String status,
                                         @RequestParam(value = "outNum", required = false) String outNum,
                                         @RequestParam(value = "type", required = false) Integer type){
        PageVO<OutStockVO> outStockVOPageVO = outStockService.findOutStockList(pageNum, pageSize, status, outNum, type);

        return ResponseBean.success(outStockVOPageVO);
    }

    /**
     * 出库保存
     * @param outStockVO
     * @return
     */
    @PostMapping("addOutStock")
    public ResponseBean addOutStock(@RequestBody OutStockVO outStockVO){
        outStockService.addOutStock(outStockVO);
        return ResponseBean.success();
    }

    /**
     * 出库审批
     * @param id
     * @return
     */
    @PutMapping("publish/{id}")
    public ResponseBean publish(@PathVariable Integer id){
        outStockService.publish(id);
        return ResponseBean.success();
    }

}
