package com.goods.business.service;

import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;

import java.util.List;

public interface SupplierService {
    /**
     * 查询地址
     * @param pageNum
     * @param pageSize
     * @param name
     * @param contact
     * @param address
     * @return
     */
    PageVO<Supplier> findSupplierList(Integer pageNum, Integer pageSize, String name ,String contact,String address);
    /**
     * 查询带条件的地址
     * @param name
     * @param contact
     * @return
     */

    /**
     * 添加地址
     * @param supplierVO
     */
    void add(SupplierVO supplierVO);

    /**
     * 地址回显
     * @param id
     * @return
     */
    Supplier edit(Integer id);

    /**
     * 更新地址
     * @param supplierVO
     */
    void update(SupplierVO supplierVO);

    /**
     * 删除地址
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取物资列表
     * @return
     */
    List<Supplier> findAll();
}
