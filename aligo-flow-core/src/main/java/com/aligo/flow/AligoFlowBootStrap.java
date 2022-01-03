package com.aligo.flow;

import com.aligo.flow.exception.AligoFlowInitException;
import com.aligo.flow.initializer.Initializer;
import com.aligo.flow.util.SpringContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * aligo-flow启动类
 *
 * @author minghui.y
 * @create 2021-12-26 9:28 下午
 **/
@Component
public class AligoFlowBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger( AligoFlowBootStrap.class );

    private final AtomicBoolean isInit = new AtomicBoolean( false );


    @Override
    public void onApplicationEvent( ContextRefreshedEvent event ) {
        if (isInit.compareAndSet( false, true )) {
            start( event );
        }
    }

    /**
     * alig-flow核心启动类
     * @param event
     */
    private void start(ContextRefreshedEvent event) {
        LOGGER.info( "aligo-flow bootstrap start..." );

        //获取所有的Initializer bean
        List<Initializer> initializers = SpringContextProvider.getImplBeanList( Initializer.class );
        //额外通过ServiceLoader方式加载
        ServiceLoader<Initializer> customInitializers = ServiceLoader.load( Initializer.class );
        for (Initializer o : customInitializers) {
            initializers.add( o );
        }
        //按优先级顺序排序
        initializers.sort( Comparator.comparingInt(Initializer::getPriority) );

        //Initializer逐个初始化
        initializers.forEach( initializer -> {
            String initializerName = initializer.getClass().getSimpleName();
            LOGGER.info( "initializer: {} start init...", initializerName );
            try {
                initializer.initialize( event );
            } catch (Exception e) {
                LOGGER.error( "initializer: {} fail to init, because of :{}", initializerName, e );
                throw new AligoFlowInitException("Initializer: " + initializerName + "fail to init", e);
            }
        } );

        LOGGER.info( "aligo-flow bootstrap finished..." );
    }
}
