package net.leomixer17.askaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprMaxPlayers extends SimpleExpression<Integer> {
    
    public Class<? extends Integer> getReturnType()
    {
        return Integer.class;
    }
    
    public boolean isSingle()
    {
        return true;
    }
    
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3)
    {
        return true;
    }
    
    public String toString(@Nullable Event event, boolean arg1)
    {
        return "max players count";
    }
    
    @Nullable
    protected Integer[] get(Event event)
    {
        return new Integer[]{
                Bukkit.getMaxPlayers()
        };
    }
    
}