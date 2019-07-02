package com.maihaoche.brz.command;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>开放平台 结算单信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/6/28 上午10:56
 * @since V1.0
 */
@Data
public class BillInfo implements Serializable{

    private static final long serialVersionUID = 8365201294241189742L;

    /**
     * 申请单
     */
    private String applyNo;

    /**
     * 放款时间
     */
    private Date loanStartTime;

    /**
     * 垫资金额
     */
    private BigDecimal loanAmount;

    /**
     * 保证金金额
     */
    private BigDecimal bailAmount;

    /**
     * 业务类型
     */
    private Integer kindType;

    /**
     * 经销商
     */
    private String companyName;

    /**
     * 收款时间
     */
    private Date paymentTime;

    /**
     * 结算单
     */
    private String settlementNo;

    /**
     * 结算单类型
     */
    private Integer settlementType;

    /**
     * 结算单类型描述
     */
    private String settlementTypeDesc;

    /**
     * 收款路径
     */
    private String paymentPath;

    /**
     * 收款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 银行流水号
     */
    private String cashNo;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 支付状态描述
     */
    private String payStatusDesc;

    /**
     * 结算单明细
     */
    private List<BillDetail> platformBillDetails;

}
