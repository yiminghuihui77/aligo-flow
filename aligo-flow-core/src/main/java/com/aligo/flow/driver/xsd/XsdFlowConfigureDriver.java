package com.aligo.flow.driver.xsd;

import com.aligo.flow.constant.ElemDefinitionBeanEnum;
import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.definition.StepDefinition;
import com.aligo.flow.driver.AligoFlowBean;
import com.aligo.flow.driver.IFlowDriver;
import com.aligo.flow.driver.xsd.config.FlowConfig;
import com.aligo.flow.driver.xsd.config.FlowTemplateConfig;
import com.aligo.flow.driver.xsd.config.StepConfig;
import com.aligo.flow.exception.AligoFlowLoadException;
import com.aligo.flow.factory.IDefinitionModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.InputStream;
import java.util.List;

/**
 * xml schema 执行流加载驱动
 * @author minghui.y
 * @create 2021-12-26 10:54 下午
 **/
public class XsdFlowConfigureDriver implements IFlowDriver<FlowTemplateConfig, AligoFlowBean> {

    private static final Logger LOGGER = LoggerFactory.getLogger( XsdFlowConfigureDriver.class );

    /**
     * xml执行流文件默认的装配路径
     */
    private static final String DEFAULT_CONFIG_PATH = "ALIGO-FLOW";

    private static final String SUFFIX = ".xml";


    /**
     * 从xml文件中读取执行流，封装成FlowTemplateConfig
     * @return
     * @throws Exception
     */
    @Override
    public FlowTemplateConfig load() throws Exception {
        LOGGER.info( "XsdFlowConfigureDriver start to load..." );
        try {
            String locationPattern = DEFAULT_CONFIG_PATH + "/**/*" + SUFFIX;
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources( locationPattern );
            if (resources.length <= 0) {
                LOGGER.error( "XsdFlowConfigureDriver loading with error: no flow file was found at {}", DEFAULT_CONFIG_PATH );
                throw new AligoFlowLoadException("no flow file was found at: " + DEFAULT_CONFIG_PATH);
            }
            //读取
            InputStream[] inputStreams = new InputStream[resources.length];
            for (int i = 0;i < resources.length;i++) {
                LOGGER.debug( "XsdFlowConfigureDriver loading flow file: {}", resources[i].getFilename() );
                inputStreams[i] = resources[i].getInputStream();
            }
            //委托loader完成加载
            return AligoFlowXsdConfigLoader.parseFlowFromXsdConfig( inputStreams );

        } catch (Exception e) {
            //加载xml异常
            throw new AligoFlowLoadException("XsdFlowConfigureDriver loading with error: {}", e);
        }
    }


    /**
     * 将xml的数据源FlowTemplateConfig，封装成同一的AligoFlowBean
     * @param modelFactory
     * @param sourceData 数据源
     * @return
     * @throws Exception
     */
    @Override
    public AligoFlowBean modeling( IDefinitionModelFactory modelFactory, FlowTemplateConfig sourceData ) throws Exception {
        //封装数据
        AligoFlowBean flowBean = new AligoFlowBean();
        for (FlowConfig flowConfig : sourceData.getFlowConfigs()) {
            FlowDefinition flowDefinition = (FlowDefinition) ElemDefinitionBeanEnum.FLOW.build();
            flowDefinition.setName( flowConfig.getName() );
            flowDefinition.setPriority( -1 );
            flowDefinition.setIdentity( flowConfig.getIdentifier() );
            for (StepConfig stepConfig : flowConfig.getStepConfigs()) {
                StepDefinition stepDefinition = (StepDefinition) ElemDefinitionBeanEnum.STEP.build();

            }

        }





        return null;
    }
}
