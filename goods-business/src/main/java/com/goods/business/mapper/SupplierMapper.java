package com.goods.business.mapper;

import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.SupplierVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SupplierMapper {

    /**
     * 查询所有地址
     * @return
     */
    List<Supplier> findAllSupplier(@Param("name") String name,@Param("contact") String contact,@Param("address") String address);

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
