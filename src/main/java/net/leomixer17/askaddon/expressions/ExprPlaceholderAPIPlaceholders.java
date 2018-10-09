package net.leomixer17.askaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprPlaceholderAPIPlaceholders extends SimpleExpression<String> {
	
	private Expression<String> text;
	private Expression<Player> player;
	
	@Override
	public Class<? extends String> getReturnType()
	{
		return String.class;
	}
	
	@Override
	public boolean isSingle()
	{
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int i, Kleenean k, ParseResult result)
	{
		this.text = (Expression<String>) exprs[0];
		this.player = (Expression<Player>) exprs[1];
		return true;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean b)
	{
		return "placeholderapi placeholders";
	}
	
	@Override
	@Nullable
	protected String[] get(Event e)
	{
		if (this.player == null)
			return new String[]{PlaceholderAPI.setPlaceholders(null, this.text.getSingle(e))};
		else
			return new String[]{PlaceholderAPI.setPlaceholders((Player) this.player.getSingle(e), this.text.getSingle(e))};
	}
	
}
