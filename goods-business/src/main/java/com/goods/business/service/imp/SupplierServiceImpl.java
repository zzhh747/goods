package com.goods.business.service.imp;

import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Supplier;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/17 19:53
 * description:
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public PageVO<Supplier> findSupplierList(Integer pageNum, Integer pageSize, String name,String contact,String address) {
        //查询所有地址
        List<Supplier> allSupplierList = supplierMapper.findAllSupplier(name,contact,address);
        //分页
        List<Supplier> page = ListPageUtils.page(allSupplierList, pageSize, pageNum);
        PageInfo<Supplier> pageInfo = new PageInfo<>(allSupplierList);
        PageVO<Supplier> supplierPageVO = new PageVO<>(pageInfo.getTotal(), page);
        return supplierPageVO;
    }



    @Override
    public void add(SupplierVO supplierVO) {
        supplierMapper.add(supplierVO);
    }

    @Override
    public Supplier edit(Integer id) {
        return supplierMapper.edit(id);
    }

    @Override
    public void update(SupplierVO supplierVO) {
        supplierMapper.update(supplierVO);
    }

    @Override
    public void delete(Integer id) {
        supplierMapper.delete(id);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierMapper.findAll();
    }
}
