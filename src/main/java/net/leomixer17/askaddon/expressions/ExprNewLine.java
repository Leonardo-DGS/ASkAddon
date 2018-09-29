package net.leomixer17.askaddon.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprNewLine extends SimpleExpression<String> {
	
	@Override
	public Class<String> getReturnType()
	{
		return String.class;
	}
	
	@Override
	public boolean isSingle()
	{
		return true;
	}
	
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult parseresult)
	{
		return true;
	}
	
	@Override
	public String toString(@Nullable Event event, boolean arg1)
	{
		return "new line";
	}
	
	@Override
	@Nullable
	protected String[] get(Event event)
	{
		return new String[]{ "\n" };
	}
	
}
