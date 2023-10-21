package com.goods.controller.business;

import com.goods.business.service.InStockService;
import com.goods.common.model.business.InStock;
import com.goods.common.model.business.InStockInfo;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.InStockDetailVO;
import com.goods.common.vo.business.InStockVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName:com.goods.controller.business.stock
 *
 * @author:张昊
 * @time:2023/10/18 19:27
 * description:
 */
@RestController
@RequestMapping("/business/inStock")
public class InStockController {
    @Autowired
    private InStockService inStockService;

    @GetMapping("findInStockList")
    public ResponseBean findInStockList(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "status", required = false) String status,
                                        @RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "inNum", required = false) String inNum,
                                        @RequestParam(value = "startTime", required = false) String startTime,
                                        @RequestParam(value = "endTime", required = false) String endTime) {
        PageVO<InStock> inStockPageVO = inStockService.findInStockList(pageNum, pageSize, status, type, inNum, startTime, endTime);
        return ResponseBean.success(inStockPageVO);
    }

    @PostMapping("addIntoStock")
    public ResponseBean addIntoStock(@RequestBody InStockVO inStockVO) {
        inStockService.addIntoStock(inStockVO);
        return ResponseBean.success();
    }

    /*@GetMapping("detail/{id}")
    public ResponseBean detail(@PathVariable Integer id) {
        InStock inStock = inStockService.findStockDetail(id);
        return ResponseBean.success(inStock);
    }*/

    @PutMapping("publish/{id}")
    public ResponseBean publish(@PathVariable Integer id) {
        inStockService.publish(id);
        return ResponseBean.success();
    }

    @GetMapping("detail/{id}")
    public ResponseBean detail(@PathVariable Long id,
                               @RequestParam Long pageNum) {
        InStockDetailVO inStockDetailVO = inStockService.findInStockDetail(id, pageNum);
        return ResponseBean.success(inStockDetailVO);
    }
}
