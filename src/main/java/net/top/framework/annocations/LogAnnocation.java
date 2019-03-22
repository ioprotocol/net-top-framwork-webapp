package net.top.framework.annocations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.annocations
 * @Description:
 * @author: xsy
 * @date： 2016/6/30
 * @version： V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnocation {
    /**
     * 系统操作资源
     * @return
     */
    String opName() default "";

    /**
     * 日志备注
     * @return
     */
    String opDescription() default "";
}
