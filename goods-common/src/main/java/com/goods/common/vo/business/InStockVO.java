package com.goods.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goods.common.model.business.ProductStock;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class InStockVO {

    private Long id;

    private String inNum;

    @NotNull(message = "入库单类型不能为空")
    private Integer type;

    private String operator;

    private Long supplierId;

    private String supplierName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date modified;

    /** 该入库单的总数**/
    private Integer productNumber;


    @NotBlank(message = "入库备注不能为空")
    private String remark;

    private List<Object>objects=new ArrayList<>();

    private List<ProductStock>products=new ArrayList<>();

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    /** 如果supplierId不存在需要添加供应商信息**/

    private String name;

    private String address;

    private String email;

    private String phone;

    private Integer sort;

    private String contact;
}
