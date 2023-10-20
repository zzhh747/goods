package com.goods.business.service;

import com.goods.common.model.business.Consumer;
import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.ConsumerVO;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;

public interface ConsumerService {
    /**
     * 查询物资去处列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param contact
     * @param address
     * @return
     */
    PageVO<Consumer> findConsumerList(Integer pageNum, Integer pageSize, String name, String contact, String address);

    /**
     * 添加物资去处地址
     * @param consumerVO
     */
    void add(ConsumerVO consumerVO);

    /**
     * 物资去处地址回显
     * @param id
     * @return
     */
    Consumer edit(Integer id);

    /**
     * 更新物资去处地址
     * @param consumerVO
     */
    void update(ConsumerVO consumerVO);

    /**
     * 删除物资去处地址
     * @param id
     */
    void delete(Integer id);
}
