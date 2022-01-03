package com.aligo.flow.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行器
 *
 * @author minghui.y
 * @create 2022-01-03 6:06 下午
 **/
public class AsyncExecutor {

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor( 15, 15,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10240) );


    private static final ExecutorService ASYNC_EXECUTOR = new MonitoringThreadPoolExecutor( 15, 15,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10240), "AligoFlowAsync" );


    public static void run(Runnable task) {
        EXECUTOR.submit( task );
    }

    public static void asyncRun(Runnable task) {
        ASYNC_EXECUTOR.execute( task );
    }


}
