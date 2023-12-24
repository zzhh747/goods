package com.goods.controller.business;

import com.goods.business.service.ConsumerService;
import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Consumer;
import com.goods.common.model.business.Supplier;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ConsumerVO;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * projectName:com.goods.controller.business
 *
 * @author:张昊
 * @time:2023/10/18 11:26
 * description:
 */
@RestController
@RequestMapping("/business/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("findConsumerList")
    public ResponseBean findConsumerList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "contact", required = false) String contact,
                                         @RequestParam(value = "address", required = false) String address) {
        PageVO<Consumer> consumerPageVO = consumerService.findConsumerList(pageNum,pageSize,name,contact,address);
        return ResponseBean.success(consumerPageVO);

    }

    @GetMapping("edit/{id}")
    public ResponseBean edit(@PathVariable Integer id) {
        Consumer consumer = consumerService.edit(id);
        return ResponseBean.success(consumer);
    }

    @PutMapping("update/{id}")
    public ResponseBean update(@RequestBody ConsumerVO consumerVO,
                               @PathVariable Integer id) {
        consumerService.update(consumerVO);
        return ResponseBean.success();
    }

    @DeleteMapping("delete/{id}")
    public ResponseBean delete(@PathVariable Integer id) {
        consumerService.delete(id);
        return ResponseBean.success();
    }

    @GetMapping("findAll")
    public ResponseBean findAll(){
        List<Supplier> list = supplierService.findAll();
        return ResponseBean.success(list);
    }

}
