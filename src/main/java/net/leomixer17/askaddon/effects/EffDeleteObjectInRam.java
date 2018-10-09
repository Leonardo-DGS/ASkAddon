package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.leomixer17.askaddon.ASkAddon;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffDeleteObjectInRam extends Effect {
	
	private Expression<String> s;
	private int a;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean arg2, ParseResult arg3)
	{
		if (i == 0)
			this.s = (Expression<String>) exprs[0];
		this.a = i;
		return true;
	}
	
	@Override
	public String toString(@Nullable Event arg0, boolean arg1)
	{
		return "delete object in ram";
	}
	
	@Override
	protected void execute(Event e)
	{
		if (this.a == 0)
			ASkAddon.objects.remove(this.s.getSingle(e));
		else
			ASkAddon.objects.clear();
	}
	
}
