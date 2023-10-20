package com.goods.business.service.imp;

import com.github.pagehelper.PageInfo;
import com.goods.business.mapper.InStockMapper;
import com.goods.business.service.InStockService;
import com.goods.common.model.business.InStock;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public InStock findStockDetail(Integer id) {
        return inStockMapper.selectStockDetail(id);
    }
}
