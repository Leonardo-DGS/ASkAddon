package net.leomixer17.askaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.leomixer17.askaddon.utils.Collect;
import net.leomixer17.askaddon.utils.JSONMessage;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class ExprJsonMessageItem extends SimpleExpression<JSONMessage> {
	
	private Expression<JSONMessage> json;
	private Expression<ItemStack> item;
	
	protected JSONMessage[] get(Event event)
	{
		JSONMessage j = (JSONMessage) this.json.getSingle(event);
		ItemStack a = (ItemStack) this.item.getSingle(event);
		if ((j == null) || (a == null)) return null;
		return (JSONMessage[]) Collect.asArray(new JSONMessage[]{
				j.itemTooltip(a)
		});
	}
	
	public boolean isSingle()
	{
		return true;
	}
	
	public Class<? extends JSONMessage> getReturnType()
	{
		return JSONMessage.class;
	}
	
	public String toString(Event event, boolean b)
	{
		return ((JSONMessage) this.json.getSingle(event)).toOldMessageFormat();
	}
	
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parseResult)
	{
		this.json = (Expression<JSONMessage>) exprs[0];
		this.item = (Expression<ItemStack>) exprs[1];
		return true;
	}
	
}