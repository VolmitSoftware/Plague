package com.volmit.plague.api;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.annotation.Nullable;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Optional
{
	String defaultString() default "";

	int defaultInt() default 0;

	long defaultLong() default 0;

	boolean defaultBoolean() default false;
}
