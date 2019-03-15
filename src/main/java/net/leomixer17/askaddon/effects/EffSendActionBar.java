package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.leomixer17.askaddon.utils.TitleAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffSendActionBar extends Effect {

    private Expression<Player> player;
    private Expression<String> text;

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int i, Kleenean arg2, SkriptParser.ParseResult arg3)
    {
        if (i == 1)
        {
            this.player = (Expression<Player>) exprs[0];
            this.text = (Expression<String>) exprs[1];
        }
        else
        {
            this.text = (Expression<String>) exprs[0];
            this.player = (Expression<Player>) exprs[1];
        }
        return true;
    }

    public String toString(@Nullable Event e, boolean arg1)
    {
        return null;
    }

    protected void execute(Event e)
    {
        for (Player player : this.player.getAll(e))
            TitleAPI.sendActionBar(player, this.text.getSingle(e));
    }

}