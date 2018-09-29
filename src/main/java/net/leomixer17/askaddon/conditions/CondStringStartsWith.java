package net.leomixer17.askaddon.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class CondStringStartsWith extends Condition {
	
	private Expression<String> stringToCheck;
	private Expression<String> stringstartswith;
	private boolean b;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean arg2, ParseResult arg3)
	{
		this.stringToCheck = (Expression<String>) exprs[0];
		this.stringstartswith = (Expression<String>) exprs[1];
		this.b = i == 0;
		return true;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean arg1)
	{
		return "string starts with another string";
	}
	
	@Override
	public boolean check(Event e)
	{
		if (b)
			return stringToCheck.getSingle(e).startsWith(stringstartswith.getSingle(e));
		return !stringToCheck.getSingle(e).startsWith(stringstartswith.getSingle(e));
	}
	
}
