package com.aligo.flow.annotation;

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
public @interface EnableAligoFlow {
}
