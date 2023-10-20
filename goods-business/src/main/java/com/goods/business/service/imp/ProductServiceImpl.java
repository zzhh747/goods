package com.goods.business.service.imp;

import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.ProductMapper;
import com.goods.business.service.ProductService;
import com.goods.common.model.business.Product;
import com.goods.common.utils.CreateVerifyCode;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ProductVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/18 14:17
 * description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageVO<Product> findProductList(Integer pageNum, Integer pageSize, String name, String categoryId, String supplier, String status, String categorys) {
        String category1 = "";
        String category2 = "";
        String category3 = "";
        if (null != categorys) {
            String[] split = categorys.split(",");
            category1 = split[0];
            category2 = split[1];
            category3 = split[2];
        }

        //查询物资
        List<Product> productList = productMapper.selectProductList(name, supplier, status, category1, category2, category3);
        List<Product> page = ListPageUtils.page(productList, pageSize, pageNum);
        PageInfo<Product> productPageInfo = new PageInfo<>();
        if (null != page){
            productPageInfo = new PageInfo<>(page);
        }
        PageVO<Product> productPageVO = new PageVO<>(productPageInfo.getTotal(), page);
        return productPageVO;
    }

    @Override
    public void add(ProductVO productVO) {
        CreateVerifyCode createVerifyCode = new CreateVerifyCode();
        productVO.setPNum(createVerifyCode.randomStr(32));
        @NotNull(message = "分类不能为空") Long[] categoryKeys = productVO.getCategoryKeys();
        productVO.setOneCategoryId(categoryKeys[0]);
        productVO.setTwoCategoryId(categoryKeys[1]);
        productVO.setThreeCategoryId(categoryKeys[2]);
        productVO.setCreateTime(new Date());
        productVO.setModifiedTime(new Date());
        productVO.setStatus(2);
        productMapper.add(productVO);
    }

    @Override
    public Product edit(Integer id) {
        return productMapper.edit(id);
    }

    @Override
    public void update(ProductVO productVO) {
        productMapper.update(productVO);
    }

    @Override
    public void publish(Integer id) {
        productMapper.publish(id);
    }

    @Override
    public void remove(Integer id) {
        productMapper.remove(id);
    }

    @Override
    public void delete(Integer id) {
        productMapper.delete(id);
    }

}
