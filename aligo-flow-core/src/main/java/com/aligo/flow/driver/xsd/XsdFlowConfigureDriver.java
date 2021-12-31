package com.aligo.flow.driver.xsd;

import com.aligo.flow.constant.ElemDefinitionBeanEnum;
import com.aligo.flow.constant.StreamDispatchTypeEnum;
import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.definition.NodeDefinition;
import com.aligo.flow.definition.StepDefinition;
import com.aligo.flow.definition.StreamDefinition;
import com.aligo.flow.driver.AligoFlowBean;
import com.aligo.flow.driver.IFlowDriver;
import com.aligo.flow.driver.xsd.config.*;
import com.aligo.flow.exception.AligoFlowLoadException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.InputStream;

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
     * @param sourceData 数据源
     * @return
     * @throws Exception
     */
    @Override
    public AligoFlowBean modeling( FlowTemplateConfig sourceData ) throws Exception {
        //封装数据
        AligoFlowBean flowBean = new AligoFlowBean();
        for (FlowConfig flowConfig : sourceData.getFlowConfigs()) {
            FlowDefinition flowDefinition = (FlowDefinition) ElemDefinitionBeanEnum.FLOW.build();
            //注册flow
            flowBean.getFlowDefinitions().add( flowDefinition );
            flowDefinition.setName( flowConfig.getName() );
            flowDefinition.setPriority( -1 );
            flowDefinition.setIdentity( flowConfig.getIdentifier() );
            for (StepConfig stepConfig : flowConfig.getStepConfigs()) {
                StepDefinition stepDefinition = (StepDefinition) ElemDefinitionBeanEnum.STEP.build();
                //step注册到flow
                flowDefinition.addStepDefinition( stepDefinition );
                stepDefinition.setName( stepConfig.getName() );
                stepDefinition.setPriority( stepConfig.getPriority() );
                if (stepConfig.getTransaction() != null && stepConfig.getTransaction()) {
                    stepDefinition.openTransaction();
                }
                for (StreamConfig streamConfig : stepConfig.getStreamConfigs()) {
                    StreamDefinition streamDefinition = (StreamDefinition) ElemDefinitionBeanEnum.STREAM.build();
                    //stream注册到step
                    stepDefinition.addStreamDefinition( streamDefinition );
                    streamDefinition.setPriority( streamConfig.getPriority() );
                    if (StringUtils.isNotEmpty( streamConfig.getType() )
                            && StreamDispatchTypeEnum.PARALLEL.equals( StreamDispatchTypeEnum.getByCode(streamConfig.getType()) )) {
                        streamDefinition.openParallel();
                    }
                    for (NodeConfig nodeConfig : streamConfig.getNodeConfigs()) {
                        NodeDefinition nodeDefinition = (NodeDefinition) ElemDefinitionBeanEnum.NODE.build();
                        //node注册到stream
                        streamDefinition.addNodeDefinition( nodeDefinition );
                        nodeDefinition.setName( nodeConfig.getName() );
                        if (nodeConfig.getIsAsync() != null && nodeConfig.getIsAsync()) {
                            nodeDefinition.openAsync();
                        }
                        nodeDefinition.binding( nodeConfig.getExecutableComponent() );
                    }

                }
            }

        }
        return flowBean;
    }
}
