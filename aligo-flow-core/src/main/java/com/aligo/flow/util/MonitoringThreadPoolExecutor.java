package com.aligo.flow.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 可监控的线程池
 *
 * @author minghui.y
 * @create 2022-01-03 6:10 下午
 **/
@Slf4j
public class MonitoringThreadPoolExecutor extends ThreadPoolExecutor {

    private String poolName;

    /**
     * 构造方法
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param poolName
     */
    public MonitoringThreadPoolExecutor( int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, String poolName ) {
        super( corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue );
        this.poolName = poolName;
    }


    /**
     * 执行完任务之后，记录线程池状态
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute( Runnable r, Throwable t ) {
        log.info( "{}-pool-monitor: " +
                        "poolSize: {}, coreSize: {}, active: {}, " +
                        "completed: {}, task: {}, queue: {}, largestPoolSize: {}," +
                "maxPoolSize: {}, keepAliveTime: {}, isShutdown: {}, isTerminated: {}",
                this.poolName, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(),
                this.getCompletedTaskCount(), this.getTaskCount(), this.getQueue().size(), this.getLargestPoolSize(),
                this.getMaximumPoolSize(), this.getKeepAliveTime( TimeUnit.MILLISECONDS ), this.isShutdown(), this.isTerminated());

        //异常处理
        if (t != null) {
            log.error( "{}-pool_monitor: run with exception: {}", this.poolName, t );
        }

    }
}
