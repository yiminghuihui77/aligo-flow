package com.aligo.flow.holder;

import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.identity.FlowIdentity;
import com.aligo.flow.util.WildcardUtils;
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

    private final Map<DefaultFlowIdentity, T> flowContainer = Maps.newConcurrentMap();

    /**
     * 根据流程定义，匹配执行流
     * @param flowIdentity
     * @return
     */
    public T matchFlow4Pattern(FlowIdentity flowIdentity) {
        T flowDefinition = simpleMatch( flowIdentity );
        if (flowDefinition != null) {
            return flowDefinition;
        }

        for (Map.Entry<DefaultFlowIdentity, T> entry : flowContainer.entrySet()) {
            if (WildcardUtils.isMatch( flowDefinition.getIdentity(), entry.getKey().getIdentity() )) {
                return entry.getValue();
            }
        }

        return null;
    }

    /**
     * 根据flowIdentity简单匹配
     * DefaultFlowIdentity.equals
     * @param flowIdentity
     * @return
     */
    public T simpleMatch(FlowIdentity flowIdentity) {
        return flowContainer.get( new DefaultFlowIdentity( flowIdentity ) );
    }

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

        public String getIdentity() {
            return identity;
        }

        public String getReuseIdentity() {
            return reuseIdentity;
        }
    }
}
