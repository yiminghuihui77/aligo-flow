package com.aligo.flow.launcher;

/**
 * 运行器接口
 *
 * @author minghui.y
 * @create 2022-01-03 3:49 下午
 **/
public interface ILauncher<E, C> {

    void fire(E executable, C paramContext) throws Exception;

}
