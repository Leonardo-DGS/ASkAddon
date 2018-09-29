package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.boss.BossBar;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class EffRemoveAllPlayersFromBossBar extends Effect implements Listener {
	
	private Expression<BossBar> bar;
	
	protected void execute(Event event)
	{
		(this.bar.getSingle(event)).removeAll();
	}
	
	public String toString(Event event, boolean b)
	{
		return "Remove all Players from Boss Bar";
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult)
	{
		this.bar = (Expression < BossBar > ) exprs[0];
		return true;
	}
	
}