package com.aligo.flow.initializer;

import com.aligo.flow.config.FlowDriverConfig;
import com.aligo.flow.driver.AligoFlowBean;
import com.aligo.flow.driver.IFlowDriver;
import com.aligo.flow.exception.AligoFlowInitException;
import com.aligo.flow.holder.HolderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ServiceLoader;

/**
 * 执行流初始化器
 *
 * @author minghui.y
 * @create 2021-12-26 10:05 下午
 **/
@Component
public class FlowInitializer implements Initializer {

    private static final Logger LOGGER = LoggerFactory.getLogger( FlowInitializer.class );

    @Resource
    private FlowDriverConfig flowDriverConfig;

    @Resource
    private HolderManager holderManager;

    @Override
    public <E extends ApplicationContextEvent> void initialize( E event ) {
        LOGGER.info( "FlowInitializer start to initialize..." );

        IFlowDriver flowDriver = flowDriverConfig.getDriver();
        ServiceLoader<IFlowDriver> drivers = ServiceLoader.load( IFlowDriver.class );
        for (IFlowDriver driver : drivers) {
            flowDriver = driver;
            break;
        }

        if (flowDriver == null) {
            throw new AligoFlowInitException("FlowInitializer error with no FlowDriver");
        }
        //使用驱动加载并model数据
        try {
            AligoFlowBean flowBean = (AligoFlowBean) flowDriver.modeling( flowDriver.load() );
            //自检
            flowBean.check();

            //流程定义存储到容器中
            flowBean.getFlowDefinitions().forEach( flowDefinition -> {
                holderManager.addFlow( flowDefinition::getIdentity, flowDefinition );
            } );

        } catch (Exception e) {
            LOGGER.error( "FlowInitializer error with: {}", e.getMessage() );
            throw new AligoFlowInitException("FlowInitializer 驱动加载数据源失败：" + e.getMessage());
        }


    }

    @Override
    public int getPriority() {
        return 0;
    }
}
