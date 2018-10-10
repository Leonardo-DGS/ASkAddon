package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.File;

public class EffDeleteWorld extends Effect {

    private Expression<String> worldName;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3)
    {
        this.worldName = (Expression<String>) args[0];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1)
    {
        return "delete a world";
    }

    protected void execute(Event e)
    {
        if (worldName == null)
            return;
        if (Bukkit.getWorlds().contains(Bukkit.getWorld(this.worldName.getSingle(e))))
            Bukkit.getServer().unloadWorld(this.worldName.getSingle(e), false);
        final File f = new File(Bukkit.getWorldContainer() + File.separator + this.worldName.getSingle(e));
        if (f.exists())
            f.delete();
    }

}