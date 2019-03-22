package net.top.framework.annocations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.annocations
 * @Description: 权限标注
 * @author: xsy
 * @date： 2016/6/30
 * @version： V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolePopedom {
    /**
     * 资源编码
     * @return
     */
    String number() default "";
}
