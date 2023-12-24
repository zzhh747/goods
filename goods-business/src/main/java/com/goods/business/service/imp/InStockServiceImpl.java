package com.goods.business.service.imp;

import com.goods.business.mapper.InStockMapper;
import com.goods.business.mapper.ProductMapper;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.InStockService;
import com.goods.common.model.business.*;
import com.goods.common.response.ActiveUser;
import com.goods.common.utils.JWTUtils;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.*;
import com.goods.common.vo.system.PageVO;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/18 19:29
 * description:
 */
@Service
public class InStockServiceImpl implements InStockService {
    @Autowired
    private InStockMapper inStockMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageVO<InStockVO> findInStockList(Integer pageNum, Integer pageSize, String status,
                                             String type, String inNum,
                                             String startTimeStr, String endTimeStr) {
        /*
        查询入库记录信息不全，应返回InStockVO

        List<InStock> inStockList = inStockMapper.selectInStockList(status,type,inNum,startTime,endTime);
        List<InStock> page = ListPageUtils.page(inStockList, pageSize, pageNum);
        PageInfo<InStock> inStockPageInfo = new PageInfo<>();
        if (null != page){
            inStockPageInfo = new PageInfo<>(page);
            PageVO<InStock> inStockPageVO = new PageVO<>(inStockPageInfo.getTotal(), page);
            return inStockPageVO;
        }
        inStockPageInfo = new PageInfo<>();
        PageVO<InStock> inStockPageVO = new PageVO<>(inStockPageInfo.getTotal(), page);
        return inStockPageVO;*/
        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != startTimeStr) {
            try {
                startTime = simpleDateFormat.parse(startTimeStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (null != endTimeStr) {
            try {
                endTime = simpleDateFormat.parse(endTimeStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        List<InStock> inStockList = inStockMapper.selectInStockList(status, type, inNum, startTime, endTime);

        List<InStockVO> inStockVOList = inStockList.stream().map(inStock -> {
            InStockVO inStockVO = new InStockVO();
            BeanUtils.copyProperties(inStock, inStockVO);
            Supplier supplier = inStockMapper.selectSupplier(inStock.getSupplierId());
            inStockVO.setSupplierName(supplier.getPhone());
            inStockVO.setPhone(supplier.getPhone());
            return inStockVO;
        }).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(inStockVOList)) {
            return new PageVO<>(0, null);
        }

        List<InStockVO> page = ListPageUtils.page(inStockVOList, pageSize, pageNum);
        return new PageVO<>(page.size(), page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIntoStock(InStockVO inStockVO) {
        //如果没有地址，新增地址
        Long supplierId;
        if (null == inStockVO.getSupplierId()) {
            supplierMapper.addSupplier(inStockVO);
            supplierId = inStockVO.getId();
        } else {
            supplierId = inStockVO.getSupplierId();
        }

        String inNum = UUID.randomUUID().toString().replaceAll("-","");
        AtomicInteger total = new AtomicInteger();
        //新增入库的物资
        List<ProductStock> products = inStockVO.getProducts();
        products.forEach(productStock -> {
            InStockInfo inStockInfo = new InStockInfo();
            Product product = productMapper.selectProduct(productStock.getProductId());
            inStockInfo.setPNum(product.getPNum());
            inStockInfo.setInNum(inNum);
            inStockInfo.setProductNumber(productStock.getProductNumber().intValue());
            total.addAndGet(productStock.getProductNumber().intValue());
            inStockMapper.addStockInfo(inStockInfo);


        });

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        String operator = activeUser.getUser().getUsername();

        InStock inStock = new InStock();
        inStock.setInNum(inNum);
        inStock.setOperator(operator);
        inStock.setRemark(inStockVO.getRemark());
        inStock.setStatus(inStockVO.getStatus());
        inStock.setSupplierId(supplierId);
        inStock.setType(inStockVO.getType());
        inStock.setProductNumber(total.intValue());
        inStockMapper.addIntoStock(inStock);




    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public void publish(Integer id) {
        inStockMapper.publish(id);
        List<ProductStock> productStockPNum = inStockMapper.selectProductStockById(id);
        productStockPNum.forEach(productStock -> {
            ProductStock productStock1 = inStockMapper.selectProductStock(productStock.getPNum());
            if (productStock1 != null) {
                // 更新库存数量
                inStockMapper.updateProductStock(Math.toIntExact(productStock.getProductNumber()),productStock.getPNum());
            } else {
                // 添加新的库存
                inStockMapper.addProductStock(Math.toIntExact(productStock.getProductNumber()),productStock.getPNum());
            }
        });

    }

    @Override
    public InStockDetailVO findInStockDetail(Long id, Integer pageNum) {
        //创建返回对象
        InStockDetailVO inStockDetailVO = new InStockDetailVO();
        //得到InStock对象
        InStock inStock = inStockMapper.selectInStock(id);
        BeanUtils.copyProperties(inStock,inStockDetailVO);
        //biz_in_stock  biz_in_stock_info  biz_supplier
        SupplierVO supplierVO = new SupplierVO();
        Supplier supplier = inStockMapper.selectSupplier(inStock.getSupplierId());
        BeanUtils.copyProperties(supplier,supplierVO);
        inStockDetailVO.setSupplierVO(supplierVO);
        List<InStockInfo> inStockInfoList = inStockMapper.selectInStockInfoList(inStock.getInNum());
        List<InStockItemVO> inStockItemVOList = inStockInfoList.stream().map(inStockInfo -> {
            InStockItemVO inStockItemVO = new InStockItemVO();
            Product product = productMapper.selectProductByPNum(inStockInfo.getPNum());
            inStockItemVO.setPNum(inStockInfo.getPNum());
            inStockItemVO.setName(product.getName());
            inStockItemVO.setModel(product.getModel());
            inStockItemVO.setUnit(product.getUnit());
            inStockItemVO.setImageUrl(product.getImageUrl());
            inStockItemVO.setCount(inStockInfo.getProductNumber());
            return inStockItemVO;
        }).collect(Collectors.toList());

        List<InStockItemVO> page = ListPageUtils.page(inStockItemVOList, 5, pageNum);
        inStockDetailVO.setTotal(page.size());
        inStockDetailVO.setItemVOS(page);
        return inStockDetailVO;
    }

    @Override
    public void remove(Integer id) {
        inStockMapper.remove(id);
    }
}
