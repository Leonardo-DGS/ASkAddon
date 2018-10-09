package net.leomixer17.askaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ExprFreeMemory extends SimpleExpression<Integer> {
	
	protected Integer[] get(Event event)
	{
		long l = Runtime.getRuntime().freeMemory();
		int i = 0;
		if (!(l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)) i = (int) l;
		return new Integer[]{
				i
		};
	}
	
	public boolean isSingle()
	{
		return true;
	}
	
	public Class<? extends Integer> getReturnType()
	{
		return Integer.class;
	}
	
	public String toString(Event event, boolean b)
	{
		return this.getClass().getName();
	}
	
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult)
	{
		return true;
	}
}