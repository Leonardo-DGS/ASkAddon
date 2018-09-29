package net.leomixer17.askaddon.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.leomixer17.askaddon.ASkAddon;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;


public class CondExistsObjectInRam extends Condition {
	
	private Expression<String> name;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean k, ParseResult result)
	{
		this.name = (Expression<String>) exprs[0];
		return true;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean b)
	{
		return "exists object in ram";
	}
	
	@Override
	public boolean check(Event e)
	{
		return ASkAddon.objects.containsKey(this.name.getSingle(e));
	}
	
}
