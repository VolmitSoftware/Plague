package com.volmit.plague.api.command;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.volmit.plague.api.Description;
import com.volmit.plague.api.PlagueRegistry;
import com.volmit.plague.api.PlagueSender;
import com.volmit.plague.api.Required;

import lombok.Getter;
import ninja.bytecode.shuriken.collections.KList;

public abstract class PlagueCommand
{
	@Getter
	private final String node;

	@Getter
	private final KList<String> aliases;

	@Getter
	private final KList<PlagueCommand> children;

	public PlagueCommand()
	{
		String[] nodes = getNodes();
		if(nodes.length < 1)
		{
			throw new RuntimeException("A command must have at least one node");
		}

		this.node = nodes[0];
		aliases = new KList<>();
		children = new KList<>();

		if(nodes.length > 1)
		{
			for(int i = 0; i < nodes.length; i++)
			{
				aliases.add(nodes[i]);
			}
		}

		PlagueRegistry.getPlaguedFields(getClass(), PlagueCommand.class).forEach((g) -> children.add((PlagueCommand) PlagueRegistry.init(g.getType())));
	}

	public abstract String[] getNodes();

	public boolean hasNode(String node)
	{
		return node.toLowerCase().equals(this.node) || aliases.contains(node.toLowerCase());
	}

	public void handleCommand(PlagueSender sender, KList<String> args)
	{
		for(Method i : PlagueRegistry.getPlaguedMethods(getClass()))
		{
			Parameter[] pars = i.getParameters();

			if(pars.length == 0)
			{
				continue;
			}

			if(!pars[0].getType().equals(PlagueSender.class))
			{
				continue;
			}

			for(Parameter j : pars)
			{

			}
		}
	}

	public void handle(PlagueSender sender, KList<String> command)
	{
		if(!command.isEmpty())
		{
			String nodePossibility = command.get(0);

			for(PlagueCommand i : children)
			{
				if(i.node.matches(nodePossibility))
				{
					command.remove(0);
					i.handle(sender, command);
					return;
				}
			}
		}

		handleCommand(sender, command);
	}
}
