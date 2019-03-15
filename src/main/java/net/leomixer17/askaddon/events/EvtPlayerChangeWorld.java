package net.leomixer17.askaddon.events;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.eclipse.jdt.annotation.Nullable;

public class EvtPlayerChangeWorld extends SkriptEvent {

    @Override
    public String toString(@Nullable Event event, boolean arg1)
    {
        return "PlayerChangedWorldEvent";
    }

    @Override
    public boolean check(Event event)
    {
        return event instanceof PlayerChangedWorldEvent;
    }

    @Override
    public boolean init(Literal<?>[] lit, int i, ParseResult parse)
    {
        return true;
    }

}