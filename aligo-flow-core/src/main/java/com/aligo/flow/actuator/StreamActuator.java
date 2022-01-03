package com.aligo.flow.actuator;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.NodeDefinition;
import com.aligo.flow.definition.StreamDefinition;
import com.aligo.flow.exception.FlowExecuteException;
import com.aligo.flow.util.AsyncExecutor;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

/**
 * 流程执行器
 *
 * @author minghui.y
 * @create 2022-01-03 5:13 下午
 **/
@Component
public class StreamActuator extends AbstractActuator<StreamDefinition, FlowContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger( StreamActuator.class );

    @Resource
    private NodeActuator nodeActuator;

    @Override
    public void exec( StreamDefinition definition, FlowContext context ) {
        try {
            if (CollectionUtils.isEmpty( definition.getNodeDefinitions() )) {
                throw new FlowExecuteException("StreamActuator exec : " + definition.getName() + " with no node");
            }
            //判断是否并行执行
            if (definition.isOpenParallel()) {
                //并行执行
                runWithParallel( definition.getNodeDefinitions(), context );
            } else {
                //串行执行
                definition.getNodeDefinitions().forEach( nodeDefinition -> runNode( nodeDefinition, context ) );
            }
        } catch (Exception e) {
            execException( definition, context, e );
            throw e;
        }
    }

    /**
     * 并行执行node
     * 1、线程池中一个node执行一次，会中断流程
     * 2、主线程会等待所有异步并行线程执行完毕之后，继续执行
     * @param nodeDefinitions
     * @param context
     */
    private void runWithParallel( List<NodeDefinition> nodeDefinitions, FlowContext context ) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier( nodeDefinitions.size() );
        List<FutureTask> futureTasks = new ArrayList<>();

        nodeDefinitions.forEach( nodeDefinition -> {
            //定义一个task
            FutureTask task = new FutureTask( () -> {
                try {
                    runNode( nodeDefinition, context );
                    return null;
                } finally {
                    cyclicBarrier.await();
                }
            } );
            futureTasks.add( task );
            //丢给线程池执行
            AsyncExecutor.run( task );
        } );

        try {
            LOGGER.info( "stream start running with parallel." );
            for (FutureTask task : futureTasks) {
                task.get();
            }
            LOGGER.info( "stream finish running with parallel." );

        } catch (Exception e) {
            LOGGER.error( "StreamActuator run with parallel error.", e);
            throw new FlowExecuteException(e);
        }


    }

    private void runNode( NodeDefinition nodeDefinition, FlowContext context ) {
        nodeActuator.preExec( nodeDefinition, context );
        nodeActuator.exec( nodeDefinition, context );
        nodeActuator.postExec( nodeDefinition, context );
    }

    @Override
    public void preExec( StreamDefinition definition, FlowContext context ) {
        super.preExec( definition, context );
    }

    @Override
    public void postExec( StreamDefinition definition, FlowContext context ) {
        super.postExec( definition, context );
    }
}
