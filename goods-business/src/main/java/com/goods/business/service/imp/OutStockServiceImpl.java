package com.goods.business.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.goods.business.mapper.InStockMapper;
import com.goods.business.mapper.OutStockMapper;
import com.goods.business.service.ConsumerService;
import com.goods.business.service.OutStockService;
import com.goods.common.model.business.Consumer;
import com.goods.common.model.business.OutStock;
import com.goods.common.model.business.Product;
import com.goods.common.model.business.ProductStock;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.utils.ObjToList;
import com.goods.common.vo.business.ConsumerVO;
import com.goods.common.vo.business.InStockVO;
import com.goods.common.vo.business.OutStockVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * projectName:com.goods.business.service.imp
 *
 * @author:张昊
 * @time:2023/10/23 18:06
 * description:
 */
@Service
public class OutStockServiceImpl implements OutStockService {
    @Autowired
    private OutStockMapper outStockMapper;

    @Autowired
    private InStockMapper inStockMapper;

    @Autowired
    private ConsumerService consumerService;

    @Override
    public PageVO<OutStockVO> findOutStockList(Integer pageNum, Integer pageSize, String status, String outNum, Integer type) {
        List<OutStock> outStockList = outStockMapper.selectOutStockList(status,outNum,type);
        List<OutStockVO> outStockVOList = outStockList.stream().map(outStock -> {
            OutStockVO outStockVO = new OutStockVO();
            BeanUtils.copyProperties(outStock, outStockVO);

            return outStockVO;
        }).collect(Collectors.toList());


        if (CollectionUtils.isEmpty(outStockVOList)){
            return new PageVO<>(0, null);
        }
        List<OutStockVO> page = ListPageUtils.page(outStockVOList, pageSize, pageNum);
        return new PageVO<>(page.size(), page);
    }

    @Override
    public void addOutStock(OutStockVO outStockVO) {
        // 物资去向
        if (outStockVO.getConsumerId() == null){
            ConsumerVO consumerVo = new ConsumerVO();
            BeanUtils.copyProperties(outStockVO,consumerVo);
            consumerService.add(consumerVo);
        }

        OutStock outStock = new OutStock();
        outStock.setId(outStockVO.getId());
        outStock.setOutNum(outStockVO.getOutNum());
        outStock.setType(outStock.getType());
        outStock.setOperator(outStockVO.getOperator());
        outStock.setCreateTime(new Date());
        outStock.setProductNumber(outStock.getProductNumber());
        outStock.setConsumerId(outStockVO.getConsumerId());
        outStock.setRemark(outStockVO.getRemark());
        outStock.setStatus(0);
        outStock.setPriority(outStockVO.getPriority());
        outStockMapper.addOutStock(outStock);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(Integer id) {
        outStockMapper.publish(id);
        List<ProductStock> productStockPNum = outStockMapper.selectProductStockById(id);
        productStockPNum.forEach(productStock -> {
            // 更新库存数量
            outStockMapper.updateProductStock(Math.toIntExact(productStock.getProductNumber()),productStock.getPNum());

        });
    }
}
