package com.maihaoche.brz.command;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>结算单上车辆的费用明细</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/6/28 上午11:13
 * @since V1.0
 */
@Data
public class BillFeeDetail implements Serializable{

    private static final long serialVersionUID = -1753595367103639631L;

    /**
     * 费用类型
     * com.mhc.bs.loan.enums.AccountSubjectDetailEnum
     */
    private Integer feeType;

    /**
     * 费用描述
     */
    private String feeTypeDesc;

    /**
     * 金额
     */
    private BigDecimal amount;
}
