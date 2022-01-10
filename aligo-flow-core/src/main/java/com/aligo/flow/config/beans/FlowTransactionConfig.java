package com.aligo.flow.config.beans;

import lombok.Data;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author minghui.y
 * @create 2022-01-10 5:06 下午
 **/
@Data
public class FlowTransactionConfig {

    /**
     * 事务模板 bean name
     */
    private String transactionTemplateBeanName;

    /**
     * 事务模板
     */
    private TransactionTemplate transactionTemplate;

}
