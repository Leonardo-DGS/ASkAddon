package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.leomixer17.askaddon.ASkAddon;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffSaveObjectInRam extends Effect {

    private Expression<Object> object;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3)
    {
        this.object = (Expression<Object>) exprs[0];
        this.name = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean arg1)
    {
        return "save object in ram";
    }

    @Override
    protected void execute(Event e)
    {
        ASkAddon.objects.put(this.name.getSingle(e), this.object.getSingle(e));
    }

}
