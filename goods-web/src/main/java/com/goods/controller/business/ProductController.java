package com.goods.controller.business;

import com.goods.business.service.ProductService;
import com.goods.common.model.business.Product;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * projectName:com.goods.controller.business
 *
 * @author:张昊
 * @time:2023/10/18 14:16
 * description:
 */
@RestController
@RequestMapping("/business/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("findProductList")
    public ResponseBean findProductList(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "categoryId", required = false) String categoryId,
                                        @RequestParam(value = "supplier", required = false) String supplier,
                                        @RequestParam(value = "status", required = false) String status,
                                        @RequestParam(value = "categorys", required = false) String categorys) {
        PageVO<Product> productPageVO = productService.findProductList(pageNum, pageSize, name, categoryId, supplier, status, categorys);
        return ResponseBean.success(productPageVO);

    }

    @PostMapping("/add")
    public ResponseBean add(@RequestBody ProductVO productVO) {
        productService.add(productVO);
        return ResponseBean.success();
    }

    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Integer id) {
        Product product = productService.edit(id);
        return ResponseBean.success(product);
    }

    @PutMapping("update/{id}")
    public ResponseBean update(@RequestBody ProductVO productVO,
                               @PathVariable Integer id) {
        productService.update(productVO);
        return ResponseBean.success();
    }

    @PutMapping("publish/{id}")
    public ResponseBean publish(@PathVariable Integer id) {
        productService.publish(id);
        return ResponseBean.success();
    }


    @PutMapping("remove/{id}")
    public ResponseBean remove(@PathVariable Integer id) {
        productService.remove(id);
        return ResponseBean.success();
    }

    @DeleteMapping("delete/{id}")
    public ResponseBean delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseBean.success();
    }

    @GetMapping("/findProducts")
    public ResponseBean findProducts(@RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "categoryId", required = false) String categoryId,
                                     @RequestParam(value = "supplier", required = false) String supplier,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "categorys", required = false) String categorys) {
        PageVO<Product> productPageVO = productService.findProductList(pageNum, pageSize, name, categoryId, supplier, status, categorys);
        return ResponseBean.success(productPageVO);
    }

    @GetMapping("/findProductStocks")
    public ResponseBean findProductStocks(@RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "categoryId", required = false) String categoryId,
                                     @RequestParam(value = "supplier", required = false) String supplier,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "categorys", required = false) String categorys) {
        PageVO<Product> productPageVO = productService.findProductList(pageNum, pageSize, name, categoryId, supplier, status, categorys);
        return ResponseBean.success(productPageVO);
    }
}
