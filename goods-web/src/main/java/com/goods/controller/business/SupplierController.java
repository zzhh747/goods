package com.goods.controller.business;

import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGUpdateStatement;
import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Supplier;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/17 19:51
 * description:
 */
@RestController
@RequestMapping("/business/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("findSupplierList")
    public ResponseBean<PageVO<Supplier>> findSupplierList(Integer pageNum,
                                                           Integer pageSize,
                                                           @RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "contact", required = false) String contact,
                                                           @RequestParam(value = "address", required = false) String address) {
        PageVO<Supplier> supplierPageVO = supplierService.findSupplierList(pageNum, pageSize, name, contact, address);
        return ResponseBean.success(supplierPageVO);
    }

    @PostMapping("add")
    public ResponseBean add(@RequestBody SupplierVO supplierVO) {
        supplierService.add(supplierVO);
        return ResponseBean.success();
    }

    @GetMapping("edit/{id}")
    public ResponseBean edit(@PathVariable Integer id) {
        Supplier supplier = supplierService.edit(id);
        return ResponseBean.success(supplier);
    }

    @PutMapping("update/{id}")
    public ResponseBean update(@RequestBody SupplierVO supplierVO,
                               @PathVariable Integer id) {
        supplierService.update(supplierVO);
        return ResponseBean.success();
    }

    @DeleteMapping("delete/{id}")
    public ResponseBean delete(@PathVariable Integer id) {
        supplierService.delete(id);
        return ResponseBean.success();
    }

    @GetMapping("findAll")
    public ResponseBean findAll(){
        List<Supplier> list = supplierService.findAll();
        return ResponseBean.success(list);
    }

}
