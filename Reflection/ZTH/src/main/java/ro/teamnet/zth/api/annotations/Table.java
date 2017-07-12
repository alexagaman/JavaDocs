package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Alexandra.Gaman on 7/12/2017.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
    String name() default "";
}
