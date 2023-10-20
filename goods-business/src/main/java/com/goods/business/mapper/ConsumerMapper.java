package com.goods.business.mapper;

import com.goods.common.model.business.Consumer;
import com.goods.common.vo.business.ConsumerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsumerMapper {
    /**
     * 查询物资去处列表
     * @param name
     * @param contact
     * @param address
     * @return
     */
    List<Consumer> findConsumerList(@Param("name") String name,@Param("contact") String contact,@Param("address") String address);

    /**
     * 添加物资去处列表
     * @param consumerVO
     */
    void add(ConsumerVO consumerVO);

    /**
     * 修改回显
     * @param id
     * @return
     */
    Consumer edit(Integer id);

    /**
     * 修改
     * @param consumerVO
     */
    void update(ConsumerVO consumerVO);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);
}
