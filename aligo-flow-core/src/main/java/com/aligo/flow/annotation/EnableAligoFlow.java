package com.aligo.flow.annotation;

import com.aligo.flow.config.AligoFlowMarkerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动aligo-flow
 *
 * @author minghui.y
 * @create 2021-12-26 10:07 下午
 **/
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Import(AligoFlowMarkerConfiguration.class)
public @interface EnableAligoFlow {
}
