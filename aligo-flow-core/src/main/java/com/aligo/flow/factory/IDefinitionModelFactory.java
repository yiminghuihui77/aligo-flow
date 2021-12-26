package com.aligo.flow.factory;

/**
 * Definition Factory
 *
 * @author minghui.y
 * @create 2021-12-26 11:03 下午
 **/
public interface IDefinitionModelFactory<M, T> {

    M createDefinition(T definitionType);
}
