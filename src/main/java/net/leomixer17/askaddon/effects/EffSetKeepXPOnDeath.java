package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.annotation.Nullable;

public class EffSetKeepXPOnDeath extends Effect {

    private Expression<Boolean> keepxp;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3)
    {
        keepxp = (Expression<Boolean>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1)
    {
        return null;
    }

    @Override
    protected void execute(Event event)
    {
        if (event instanceof PlayerDeathEvent)
            ((PlayerDeathEvent) event).setKeepLevel(this.keepxp.getSingle(event));
    }

}
