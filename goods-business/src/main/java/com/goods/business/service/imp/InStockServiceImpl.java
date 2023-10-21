package com.goods.business.service.imp;

import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.InStockMapper;
import com.goods.business.mapper.ProductMapper;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.InStockService;
import com.goods.common.dto.UserLoginDTO;
import com.goods.common.model.business.InStock;
import com.goods.common.model.business.InStockInfo;
import com.goods.common.model.business.Product;
import com.goods.common.model.business.Supplier;
import com.goods.common.response.ActiveUser;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.InStockDetailVO;
import com.goods.common.vo.business.InStockItemVO;
import com.goods.common.vo.business.InStockVO;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public PageVO<InStock> findInStockList(Integer pageNum, Integer pageSize, String status, String type, String inNum, String startTime, String endTime) {
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
        return inStockPageVO;
    }

//    @Override
//    public InStock findStockDetail(Integer id) {
//        return inStockMapper.selectStockDetail(id);
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIntoStock(InStockVO inStockVO) {
        //如果没有地址，新增地址
        Long supplierId;
        if (null == inStockVO.getSupplierId()){
            supplierMapper.addSupplier(inStockVO);
            supplierId = inStockVO.getId();
        }else {
            supplierId = inStockVO.getSupplierId();
        }


        //入库
        //biz_in_stock
        List<Object> products = inStockVO.getProducts();
        Map productNumberMap = (Map) products.get(0);
        Integer productId = (Integer) productNumberMap.get("productId");
        Integer productNumber = (Integer) productNumberMap.get("productNumber");
        String inNum = UUID.randomUUID().toString().replace("-", "");
        inStockVO.setInNum(inNum);
        inStockVO.setSupplierId(supplierId);
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        String operator = activeUser.getUser().getUsername();
        inStockVO.setOperator(operator);
        inStockVO.setProductNumber(productNumber);

        inStockMapper.addIntoStock(inStockVO);

        //给biz_in_stock_info赋值
        InStockInfo inStockInfo = new InStockInfo();
        inStockInfo.setProductNumber(productNumber);
        inStockInfo.setPNum(UUID.randomUUID().toString().replace("-",""));
        inStockInfo.setInNum(inNum);
        inStockMapper.addStockInfo(inStockInfo);

    }

    @Override
    public void publish(Integer id) {
        inStockMapper.publish(id);
    }

    @Override
    public InStockDetailVO findInStockDetail(Long id, Long pageNum) {
        //得到InStock对象
//        InStock inStock = inStockMapper.selectInStock(id);
//
//        SupplierVO supplierVO = supplierMapper.selectSupplierVOById(inStock.getSupplierId());
//        List<InStockItemVO> list = productMapper.selectProductListByPNum();
        return inStockMapper.selectInStockDetail(id);
    }
}
