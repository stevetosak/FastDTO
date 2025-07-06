package com.vitosak.annotations;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// container annotacija za da mozis pojke simple dto anotacii da redis.
public @interface SimpleDTOContainer {
    SimpleDTO[] value();
}
