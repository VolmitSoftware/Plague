package com.volmit.plague.api.command;

import java.lang.reflect.Parameter;

import com.volmit.plague.api.Description;
import com.volmit.plague.api.Optional;

import lombok.Data;

@Data
public class PlagueCommandParameter
{
	private final String name;
	private final String description;
	private final boolean required;
	private final Class<?> type;
	
	public PlagueCommandParameter(Parameter p)
	{
		this.name = p.getName();
		this.description = p.getDeclaredAnnotation(Description.class).value();
		this.required = p.isAnnotationPresent(Optional.class) ? false : true;
		this.type = p.getType();
	}
}
