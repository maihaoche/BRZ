CREATE TABLE `fp_debts` (
  `debt_id`            BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '借据id',
  `order_no`           VARCHAR(32)    NOT NULL           DEFAULT ''
  COMMENT '订单编号',
  `city_id`            BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '业务所在城市，根据借款人城市获取',
  `service_manager_id` BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '业务经理id',
  `pledge_id`          BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '质押物id',
  `pricing_amount`     DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
  COMMENT '核价金额',
  `pledge_rate`        BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '质押率',
  `borrower_id`        BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '借款人(经销商)id',
  `lender_id`          BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '出借人id',
  `amount`             DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
  COMMENT '实际垫资金额(核价金额*质押率)',
  `repayment_time`     DATETIME       NOT NULL           DEFAULT '0000-00-00 00:00'
  COMMENT '应还款日期',
  `repo_time`          DATETIME       NOT NULL           DEFAULT '0000-00-00 00:00'
  COMMENT '回购期限(资金方截止期限)',
  `is_settle`          TINYINT(4)                        DEFAULT '0'
  COMMENT '是否结清 0 未结清 1 结清',
  `reason`             TINYINT(4)                        DEFAULT '0'
  COMMENT '借据生成原因，1 垫资出款，2 资方放款 3 回购 4 转让',
  `status`             TINYINT(4)     NOT NULL           DEFAULT '1'
  COMMENT '0 不可用，1 可用',
  `gmt_create`         DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified`       DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`debt_id`),
  KEY `i_borrower_id` (`borrower_id`),
  KEY `i_lender_id` (`lender_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '借据表';

CREATE TABLE `fp_investor_loan` (
  `loan_id`      BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '账目id',
  `investor_id`  BIGINT(20)     NOT NULL           DEFAULT '0'
  COMMENT '资方id',
  `amount`       DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
  COMMENT '金额',
  `start_time`   DATETIME       NOT NULL           DEFAULT '0000-00-00 00:00'
  COMMENT '起息时间',
  `status`       TINYINT(4)     NOT NULL           DEFAULT '1'
  COMMENT '0 不可用，1 可用',
  `gmt_create`   DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`loan_id`),
  KEY `i_investor_id` (`investor_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '资方放款记录';

CREATE TABLE `fp_debt_clearing_rule_ref` (
  `ref_id`       BIGINT(20) NOT NULL           AUTO_INCREMENT
  COMMENT '回款id',
  `debt_id`      BIGINT(20) NOT NULL           DEFAULT '1'
  COMMENT '债权id',
  `user_id`      BIGINT(20) NOT NULL           DEFAULT '0'
  COMMENT '适用此规则的 用户 经销商、资金方 id',
  `rule_id`      BIGINT(20) NOT NULL           DEFAULT '0'
  COMMENT '规则id',
  `stage`        TINYINT(4) NOT NULL           DEFAULT '1'
  COMMENT '回款方式 1 正常 2 展期 3 逾期',
  `status`       TINYINT(4) NOT NULL           DEFAULT '1'
  COMMENT '0 不可用，1 可用',
  `gmt_create`   DATETIME   NOT NULL           DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` DATETIME   NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`ref_id`),
  INDEX `i_debt_id` (`debt_id`),
  INDEX `i_user_id` (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '借据-用户-清分规则';
#
# CREATE TABLE `fp_clearing` (
#   `clearing_id`    BIGINT(20)     NOT NULL           AUTO_INCREMENT
#   COMMENT '清分id',
#   `debt_id`        BIGINT(20)     NOT NULL           DEFAULT '1'
#   COMMENT '债权id',
#   `user_id`        BIGINT(20)     NOT NULL           DEFAULT '0'
#   COMMENT '用户id',
#   `principal`      DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '本金',
#   `rate`           DECIMAL(8, 6)  NOT NULL           DEFAULT '0.00'
#   COMMENT '利率',
#   `interest`       DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '利息',
#   `days`           DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '计息天数',
#   `subject`        TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '科目 从平台角度记，应收 应付',
#   `subject_detail` TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '科目 利息 平台服务费',
#   `status`         TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '0 不可用，1 可用',
#   `gmt_create`     DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP
#   COMMENT '创建时间',
#   `gmt_modified`   DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
#   COMMENT '修改时间',
#   PRIMARY KEY (`clearing_id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COMMENT = '清分记录';
#
# CREATE TABLE `fp_debt_settle_record` (
#   `record_id`     BIGINT(20)     NOT NULL           DEFAULT '1'
#   COMMENT '回款id',
#   `settlement_no` VARCHAR(32)    NOT NULL           DEFAULT ''
#   COMMENT '结算单编号',
#   `debt_id`       BIGINT(20)     NOT NULL           DEFAULT '1'
#   COMMENT '债权id',
#   `principal`     DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '回款本金',
#   `interest`      DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '回款利息',
#   `cost`          DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '结清成本，如果提存，这里就会有成本，清偿是0',
#   `way`           TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '回款方式 1 清偿 2 提存 3替换',
#   `status`        TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '0 不可用，1 可用',
#   `gmt_create`    DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP
#   COMMENT '创建时间',
#   `gmt_modified`  DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
#   COMMENT '修改时间',
#   PRIMARY KEY (`record_id`),
#   INDEX `i_debt_id` (`debt_id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COMMENT = '回款记录';
#
#
# CREATE TABLE `fp_clearing_rule` (
#   `rule_id`        BIGINT(20)     NOT NULL           AUTO_INCREMENT
#   COMMENT '清分id',
#   `rate`           DECIMAL(8, 6)  NOT NULL           DEFAULT '0.00'
#   COMMENT '利率',
#   `days`           DECIMAL(20, 2) NOT NULL           DEFAULT '0.00'
#   COMMENT '计息天数',
#   `subject`        TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '科目 从平台角度记，应收 应付',
#   `subject_detail` TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '科目 利息 平台服务费',
#   `status`         TINYINT(4)     NOT NULL           DEFAULT '1'
#   COMMENT '0 不可用，1 可用',
#   `gmt_create`     DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP
#   COMMENT '创建时间',
#   `gmt_modified`   DATETIME       NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
#   COMMENT '修改时间',
#   PRIMARY KEY (`rule_id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COMMENT = '清分规则';
#
# CREATE TABLE `fp_clearing_rule_rate` (
#   `rate_id`      BIGINT(20)    NOT NULL           AUTO_INCREMENT
#   COMMENT '清分id',
#   `rule_id`      BIGINT(20)    NOT NULL           DEFAULT '0'
#   COMMENT '规则id',
#   `min`          INT(11)       NOT NULL           DEFAULT '0'
#   COMMENT '最小天数',
#   `max`          INT(11)       NOT NULL           DEFAULT '0'
#   COMMENT '最大天数',
#   `rate`         DECIMAL(8, 6) NOT NULL           DEFAULT '0.00'
#   COMMENT '利率',
#   `status`       TINYINT(4)    NOT NULL           DEFAULT '1'
#   COMMENT '0 不可用，1 可用',
#   `gmt_create`   DATETIME      NOT NULL           DEFAULT CURRENT_TIMESTAMP
#   COMMENT '创建时间',
#   `gmt_modified` DATETIME      NOT NULL           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
#   COMMENT '修改时间',
#   PRIMARY KEY (`rate_id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COMMENT = '清分记录';