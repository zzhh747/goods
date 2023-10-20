package com.goods.business.service.imp;

import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.ConsumerMapper;
import com.goods.business.service.ConsumerService;
import com.goods.common.model.business.Consumer;
import com.goods.common.model.business.Supplier;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ConsumerVO;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/18 11:27
 * description:
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public PageVO<Consumer> findConsumerList(Integer pageNum, Integer pageSize, String name, String contact, String address) {
        List<Consumer> list = consumerMapper.findConsumerList(name,contact,address);
        List<Consumer> page = ListPageUtils.page(list, pageSize, pageNum);
        PageInfo<Consumer> consumerPageInfo = new PageInfo<>(list);
        PageVO<Consumer> pageInfo = new PageVO<>(consumerPageInfo.getTotal(),page);
        return pageInfo;
    }

    @Override
    public void add(ConsumerVO consumerVO) {
        consumerMapper.add(consumerVO);
    }

    @Override
    public Consumer edit(Integer id) {
        return consumerMapper.edit(id);
    }

    @Override
    public void update(ConsumerVO consumerVO) {
        consumerMapper.update(consumerVO);
    }

    @Override
    public void delete(Integer id) {
        consumerMapper.delete(id);
    }
}
