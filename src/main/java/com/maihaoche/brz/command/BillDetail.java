package com.maihaoche.brz.command;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>开放平台结算单明细</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/6/28 上午11:03
 * @since V1.0
 */
@Data
public class BillDetail implements Serializable{

    private static final long serialVersionUID = -3645734307798953379L;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 单车金额的汇总
     */
    private BigDecimal totalAmount;

    /**
     * 结算单费用列表
     */
    private List<BillFeeDetail> platformBillFeeDetails;

}
