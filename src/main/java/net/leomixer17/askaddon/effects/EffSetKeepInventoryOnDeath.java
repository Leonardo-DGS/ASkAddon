package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.eclipse.jdt.annotation.Nullable;

public class EffSetKeepInventoryOnDeath extends Effect {

    private Expression<Boolean> keepinv;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean arg2, ParseResult arg3)
    {
        this.keepinv = (Expression<Boolean>) exprs[0];
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
            ((PlayerDeathEvent) event).setKeepInventory(this.keepinv.getSingle(event));
    }

}
