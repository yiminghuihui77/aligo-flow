package com.aligo.flow.driver.xsd;

import com.aligo.flow.driver.xsd.config.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责从输入流中读取xml的配置信息
 *
 * @author minghui.y
 * @create 2021-12-28 10:04 下午
 **/
public class AligoFlowXsdConfigLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger( AligoFlowXsdConfigLoader.class );

    /**
     * 从xml输入流中读取流程配置，封装成FlowTemplateConfig
     * @param inputStreams
     * @return
     */
    public static FlowTemplateConfig parseFlowFromXsdConfig( InputStream[] inputStreams ) throws DocumentException {
        FlowTemplateConfig templateConfig = new FlowTemplateConfig();
        if (inputStreams == null || inputStreams.length <= 0) {
            return templateConfig;
        }
        //执行流集合
        List<FlowConfig> flowConfigs = new ArrayList<>();
        templateConfig.setFlowConfigs( flowConfigs );

        for (InputStream inputStream : inputStreams) {
            FlowTemplateConfig flowTemplateConfig = parseConfigFile4Xml(inputStream);
            if (flowTemplateConfig == null || CollectionUtils.isEmpty( flowTemplateConfig.getFlowConfigs() )) {
                continue;
            }
            templateConfig.setName( flowTemplateConfig.getName() );
            templateConfig.setVersion( flowTemplateConfig.getVersion() );
            flowConfigs.addAll( flowTemplateConfig.getFlowConfigs() );
        }

        LOGGER.info( "AligoFlowXsdConfigLoader has load : {} flow files", flowConfigs.size() );
        return templateConfig;
    }


    private static FlowTemplateConfig parseConfigFile4Xml(InputStream inputStream) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read( inputStream );
        Element rootElement = document.getRootElement();
        return buildFlowTemplateConfig(rootElement);
    }

    /**
     * 根据root元素构建FlowTemplateConfig
     * @param rootElement
     * @return
     */
    private static FlowTemplateConfig buildFlowTemplateConfig(Element rootElement) {
        FlowTemplateConfig flowTemplateConfig = new FlowTemplateConfig();
        if (rootElement == null) {
            LOGGER.warn( "AligoFlowXsdConfigLoader loading a valid root node for flow file" );
            return flowTemplateConfig;
        }
        //开始封装数据
        String name = rootElement.attributeValue( "name" );
        flowTemplateConfig.setName( name );
        String version = rootElement.attributeValue( "version" );
        flowTemplateConfig.setVersion( version );
        List<FlowConfig> flowConfigs = new ArrayList<>();
        flowTemplateConfig.setFlowConfigs( flowConfigs );
        List<Element> flowElements = rootElement.elements();
        for (Element element : flowElements) {
            FlowConfig flowConfig = buildFlowConfig(element);
            if (flowConfig == null) {
                continue;
            }
            flowConfigs.add( flowConfig );
        }
        return flowTemplateConfig;
    }

    private static FlowConfig buildFlowConfig(Element flowElement) {
        if (flowElement == null) {
            LOGGER.warn( "AligoFlowXsdConfigLoader loading a valid node for flow" );
            return null;
        }
        FlowConfig flowConfig = new FlowConfig();
        String name = flowElement.attributeValue( "name" );
        flowConfig.setName( name );
        //执行流标识符
        String identifier = flowElement.attributeValue( "identifier" );
        flowConfig.setIdentifier( identifier );
        //执行流复用标识符
        String reuseIdentifier = flowElement.attributeValue( "reuseIdentifier" );
        flowConfig.setReuseIdentifier( reuseIdentifier );
        //step 集合
        List<StepConfig> stepConfigs = new ArrayList<>();
        flowConfig.setStepConfigs( stepConfigs );
        List<Element> stepElements = flowElement.elements();
        for (Element element : stepElements) {
            StepConfig stepConfig = buildStepConfig(element);
            if (stepConfig == null) {
                continue;
            }
            stepConfigs.add( stepConfig );
        }
        return flowConfig;
    }

    private static StepConfig buildStepConfig(Element stepElement) {
        if (stepElement == null) {
            LOGGER.warn( "AligoFlowXsdConfigLoader loading a valid node for step" );
            return null;
        }
        StepConfig stepConfig = new StepConfig();
        String name = stepElement.attributeValue( "name" );
        stepConfig.setName( name );
        String priorityValue = stepElement.attributeValue( "priority" );
        //step优先级
        Integer priority = StringUtils.isEmpty( priorityValue ) ? null : Integer.parseInt( priorityValue );
        stepConfig.setPriority( priority );
        //是否在事务范围内执行，默认否
        String transactionValue = stepElement.attributeValue( "transaction" );
        Boolean transaction = !StringUtils.isEmpty( transactionValue ) && Boolean.parseBoolean( transactionValue );
        stepConfig.setTransaction( transaction );
        //stream 集合
        List<StreamConfig> streamConfigs = new ArrayList<>();
        stepConfig.setStreamConfigs( streamConfigs );
        List<Element> streamElements = stepElement.elements();
        for (Element element : streamElements) {
            StreamConfig streamConfig = buildStreamConfig(element);
            if (streamConfig == null) {
                continue;
            }
            streamConfigs.add( streamConfig );
        }

        return stepConfig;
    }

    private static StreamConfig buildStreamConfig(Element streamElement) {
        if (streamElement == null) {
            LOGGER.warn( "AligoFlowXsdConfigLoader loading a valid node for stream" );
            return null;
        }
        StreamConfig streamConfig = new StreamConfig();
        //调度类型
        String type = streamElement.attributeValue( "type" );
        streamConfig.setType( type );
        //优先级
        String priorityValue = streamElement.attributeValue( "priority" );
        //step优先级
        Integer priority = StringUtils.isEmpty( priorityValue ) ? null : Integer.parseInt( priorityValue );
        streamConfig.setPriority( priority );
        //事务
        String transactionValue = streamElement.attributeValue( "transaction" );
        streamConfig.setTransaction( StringUtils.isEmpty( transactionValue ) ? null : Boolean.parseBoolean( transactionValue ) );
        //Node集合
        List<NodeConfig> nodeConfigs = new ArrayList<>();
        streamConfig.setNodeConfigs( nodeConfigs );
        List<Element> nodeElements = streamElement.elements();
        for (Element element : nodeElements) {
            NodeConfig nodeConfig = buildNodeConfig(element);
            if (nodeConfig == null) {
                continue;
            }
            nodeConfigs.add( nodeConfig );
        }
        return streamConfig;
    }

    private static NodeConfig buildNodeConfig(Element nodeElement) {
        if (nodeElement == null) {
            LOGGER.warn( "AligoFlowXsdConfigLoader loading a valid node for node" );
            return null;
        }
        NodeConfig nodeConfig = new NodeConfig();
        String name = nodeElement.attributeValue( "name" );
        nodeConfig.setName( name );
        //是否异步
        String isAsyncValue = nodeElement.attributeValue( "isAsync" );
        nodeConfig.setIsAsync( StringUtils.isEmpty( isAsyncValue ) ? null : Boolean.parseBoolean( isAsyncValue ) );
        //组件bean
        String executableComponent = nodeElement.attributeValue( "executableComponent" );
        nodeConfig.setExecutableComponent( executableComponent );
        return nodeConfig;
    }
}
