package com.volmit.plague.api.command;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.volmit.plague.api.PlagueSender;

import lombok.Data;
import ninja.bytecode.shuriken.collections.KList;

@Data
public class PlagueCommandFunction
{
	private final Method method;
	private final KList<PlagueCommandParameter> parameters;
	
	public PlagueCommandFunction(Method method)
	{
		this.method = method;
		this.parameters = new KList<>();
		
		for(Parameter i : method.getParameters())
		{
			if(i.getType().equals(PlagueSender.class))
			{
				continue;
			}
			
			parameters.add(new PlagueCommandParameter(i));
		}
	}
}
