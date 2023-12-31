package com.goods.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goods.common.model.business.Product;
import com.goods.common.model.business.ProductStock;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class OutStockVO {

    private Long id;

    private String outNum;

    private Integer type;

    private String operator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer productNumber;

    private Integer priority;


    //发放的物资列表
    private List<ProductStock> products=new ArrayList<>();

    private String remark;

    //发放单的状态
    private Integer status;


    /*** 如果consumerId不为空**/

    private Long consumerId;

    //去向名
    private String name;

    //地址
    private String address;

    //联系电话
    private String phone;

    //联系人
    private String contact;

    //排序
    private Integer sort;





}
