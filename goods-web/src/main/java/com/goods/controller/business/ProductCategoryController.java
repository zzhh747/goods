package com.goods.controller.business;

import com.github.pagehelper.PageInfo;
import com.goods.business.service.ProductCategoryService;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * projectName:com.goods.controller.business
 *
 * @author:张昊
 * @time:2023/10/17 14:10
 * description:
 */
@RestController
@RequestMapping("/business/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/categoryTree")
    public ResponseBean<PageVO<ProductCategoryTreeNodeVO>> findConsumerList(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize){

        PageVO<ProductCategoryTreeNodeVO> pageInfo = productCategoryService.findConsumerList(pageNum,pageSize);

        return ResponseBean.success(pageInfo);
    }

    @GetMapping("/getParentCategoryTree")
    public ResponseBean<List<ProductCategoryTreeNodeVO>> getParentCategoryTree(){
        List<ProductCategoryTreeNodeVO> pageInfoList = productCategoryService.findParentSort();
        return ResponseBean.success(pageInfoList);
    }

    @PostMapping("/add")
    public ResponseBean add(@RequestBody ProductCategoryVO productCategoryVO){
        productCategoryService.add(productCategoryVO);
        return ResponseBean.success();
    }

    @DeleteMapping("/delete/{productCategoryId}")
    public ResponseBean delete(@PathVariable Integer productCategoryId){
        productCategoryService.delete(productCategoryId);
        return ResponseBean.success();
    }

    @GetMapping("/edit/{productCategoryId}")
    public ResponseBean edit(@PathVariable Integer productCategoryId){
        ProductCategoryVO productCategoryVO = productCategoryService.edit(productCategoryId);
        return ResponseBean.success(productCategoryVO);
    }

    @PutMapping("/update/{productCategoryId}")
    public ResponseBean update(@RequestBody ProductCategoryVO productCategoryVO){
        productCategoryService.update(productCategoryVO);
        return ResponseBean.success();
    }
}
