package com.goods.controller.consumer;

import com.goods.business.service.ConsumerService;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ConsumerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName:com.goods.controller.consumer
 *
 * @author:张昊
 * @time:2023/10/18 14:09
 * description:
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerAddController {
    @Autowired
    private ConsumerService consumerService;

    @PostMapping("add")
    public ResponseBean add(@RequestBody ConsumerVO consumerVO) {
        consumerService.add(consumerVO);
        return ResponseBean.success();
    }
}
