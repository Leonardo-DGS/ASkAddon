package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import net.leomixer17.pluginlib.util.Players;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffSendTitle extends Effect {

    private Expression<String> Title;
    private Expression<String> subTitle;
    private Expression<Player> Player;
    private Expression<Timespan> time;
    private Expression<Timespan> fadeIn;
    private Expression<Timespan> fadeOut;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, ParseResult parse)
    {
        this.Title = (Expression<String>) exprs[0];
        this.subTitle = (Expression<String>) exprs[1];
        this.time = (Expression<Timespan>) exprs[2];
        this.fadeIn = (Expression<Timespan>) exprs[3];
        this.fadeOut = (Expression<Timespan>) exprs[4];
        this.Player = (Expression<Player>) exprs[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1)
    {
        return "send title";
    }

    @Override
    protected void execute(Event event)
    {
        int timeTicks = 60;
        int fadeInTicks = 5;
        int fadeOutTicks = 5;
        if (this.time != null)
            timeTicks = (int) this.time.getSingle(event).getTicks_i();
        if (this.fadeIn != null)
            fadeInTicks = (int) this.fadeIn.getSingle(event).getTicks_i();
        if (this.fadeOut != null)
            fadeOutTicks = (int) this.fadeOut.getSingle(event).getTicks_i();

        for (final Player p : this.Player.getAll(event))
        {
            if (this.Title != null && this.subTitle != null)
                Players.sendTitle(fadeInTicks, timeTicks, fadeOutTicks, this.Title.getSingle(event), this.subTitle.getSingle(event), p);
            else if (this.Title != null)
                Players.sendTitle(fadeInTicks, timeTicks, fadeOutTicks, this.Title.getSingle(event), null, p);
            else if (this.subTitle != null)
                Players.sendTitle(fadeInTicks, timeTicks, fadeOutTicks, null, this.subTitle.getSingle(event), p);
        }
    }

}
