package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffRequestGarbageCollector extends Effect {
    
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean arg2, ParseResult arg3)
    {
        return true;
    }
    
    @Override
    public String toString(@Nullable Event arg0, boolean arg1)
    {
        return "request a garbage collector";
    }
    
    @Override
    protected void execute(Event e)
    {
        System.gc();
    }
    
}
