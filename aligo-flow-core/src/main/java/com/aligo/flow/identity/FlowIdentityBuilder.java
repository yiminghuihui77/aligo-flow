package com.aligo.flow.identity;

import com.aligo.flow.exception.FlowIdentityException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程身份构建器
 *
 * @author minghui.y
 * @create 2022-01-14 2:07 下午
 **/
public class FlowIdentityBuilder {
    /**
     * 流程标识描述器
     */
    private FlowIdentityDescriptor identityDescriptor;


    public FlowIdentityBuilder() {
        identityDescriptor = new FlowIdentityDescriptor();
    }

    public FlowIdentityBuilder idSlice(String idSlice) {
        this.identityDescriptor.idSlices.add( idSlice );
        return this;
    }

    /**
     * 构建流程标识
     * @return
     */
    public FlowIdentity build() {
        if (CollectionUtils.isEmpty(this.identityDescriptor.idSlices)) {
            throw new FlowIdentityException("building flow identity error: without idSlice");
        }
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < this.identityDescriptor.idSlices.size()) {
            builder.append( this.identityDescriptor.idSlices.get( index ) );
            if (index != this.identityDescriptor.idSlices.size() - 1) {
                builder.append( FlowIdentityDescriptor.IDENTITY_SEPARATOR );
            }
            index++;
        }
        return builder::toString;
    }



    /**
     * 流程标识描述符
     */
    private static class FlowIdentityDescriptor {
        private List<String> idSlices = new ArrayList<>();
        private static final String IDENTITY_SEPARATOR = ":";
    }
}
