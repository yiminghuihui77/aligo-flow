package com.aligo.flow.holder;

import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.identity.FlowIdentity;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * 执行流holder
 *
 * @author minghui.y
 * @create 2021-12-31 3:34 下午
 **/
public class FlowHolder<T extends FlowDefinition> implements IAligoHolder{

    private Map<DefaultFlowIdentity, T> flowContainer = Maps.newConcurrentMap();

    /**
     * Getter method for property flowContainer.
     *
     * @return property value of flowContainer
     */
    public Map<DefaultFlowIdentity, T> getFlowContainer() {
        return flowContainer;
    }

    public static class DefaultFlowIdentity {

        /**
         * 流程的唯一标识
         */
        private String identity;
        /**
         * 复用流程的唯一标识
         */
        private String reuseIdentity;

        public DefaultFlowIdentity(String identity) {
            this.identity = identity;
        }

        public DefaultFlowIdentity( FlowIdentity flowIdentity ) {
            this.identity = flowIdentity.getIdentity();
        }

        @Override
        public boolean equals( Object o ) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DefaultFlowIdentity that = (DefaultFlowIdentity) o;
            return Objects.equals( identity, that.identity );
        }

        @Override
        public int hashCode() {
            return Objects.hash( identity, reuseIdentity );
        }
    }
}
