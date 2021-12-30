package com.aligo.flow.constant;

import com.aligo.flow.definition.*;

/**
 * Element元素枚举
 *
 * @author minghui.y
 * @create 2021-12-30 5:25 下午
 **/
public enum ElemDefinitionBeanEnum {

    FLOW("FLOW", "流程") {
        @Override
        public IDefinition build() {
            return new Flow();
        }
    },
    STEP("STEP", "步骤") {
        @Override
        public IDefinition build() {
            return new Step();
        }
    },
    STREAM("STREAM", "执行流") {
        @Override
        public IDefinition build() {
            return new Stream();
        }
    },
    NODE("NODE", "节点") {
        @Override
        public IDefinition build() {
            return new Node();
        }
    }

    ;

    private String element;
    private String desc;

    ElemDefinitionBeanEnum(String element, String desc) {
        this.element = element;
        this.desc = desc;
    }

    public abstract IDefinition build();


}
