package com.aligo.flow.config;

import com.aligo.flow.AligoFlowBootStrap;
import com.aligo.flow.config.beans.FlowDriverConfig;
import com.aligo.flow.config.beans.FlowTransactionConfig;
import com.aligo.flow.constant.SupportSchemaEnum;
import com.aligo.flow.driver.IFlowDriver;
import com.aligo.flow.exception.AligoFlowInitException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * Aligo-flow 全局自动装配类
 *
 * @author minghui.y
 * @create 2021-12-29 11:27 下午
 **/
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AligoFlowMarkerConfiguration.Marker.class)
@Import( AligoFlowInitializerConfiguration.class )
public class AligoFlowAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger( AligoFlowAutoConfiguration.class );

    @Resource
    private AligoFlowProperties aligoFlowProperties;



    /**
     * 数据源驱动
     * @return
     */
    @Bean
    public FlowDriverConfig flowDriverConfig() {
        FlowDriverConfig driverConfig = new FlowDriverConfig();
        driverConfig.setSchema( aligoFlowProperties.getFlowCommonConfig().getSchema() );
        LOGGER.info( "AligoFlowAutoConfiguration config with driver: {}", aligoFlowProperties.getFlowCommonConfig().getSchema() );
        try {
            SupportSchemaEnum schema = SupportSchemaEnum.getByCode( aligoFlowProperties.getFlowCommonConfig().getSchema() );
            driverConfig.setDriver( (IFlowDriver) schema.getDriverClass().newInstance() );

        } catch (Exception e) {
            throw new AligoFlowInitException("AligoFlowAutoConfiguration config with error:" + e.getMessage());
        }

        return driverConfig;
    }

    /**
     * 事务模板
     * @return
     */
    @Bean
    public FlowTransactionConfig flowTransactionConfig( ApplicationContext applicationContext) {
        FlowTransactionConfig transactionConfig = new FlowTransactionConfig();
        transactionConfig.setTransactionTemplateBeanName( aligoFlowProperties.getGlobalConfig().getTransactionTemplateBeanName() );
        //如果指定了事务模板，则必须赋值
        if (StringUtils.isNotEmpty(aligoFlowProperties.getGlobalConfig().getTransactionTemplateBeanName())) {
            //指定事务模板
            Object transactionBean = applicationContext.getBean( aligoFlowProperties.getGlobalConfig().getTransactionTemplateBeanName() );
            if (transactionBean == null) {
                throw new AligoFlowInitException("AligoFlowAutoConfiguration config with error: can not find transactionTemplate for name: "
                        + aligoFlowProperties.getGlobalConfig().getTransactionTemplateBeanName());
            }
            transactionConfig.setTransactionTemplate( (TransactionTemplate) transactionBean );
        }
        return transactionConfig;
    }

    /**
     * aligo-flow核心启动类，用于初始化上下文
     * @param context
     * @return
     */
    @Bean
    public AligoFlowBootStrap aligoFlowBootStrap( ApplicationContext context) {
        return new AligoFlowBootStrap(context);
    }


}
