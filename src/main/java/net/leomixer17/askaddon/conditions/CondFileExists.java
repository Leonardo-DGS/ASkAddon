package net.leomixer17.askaddon.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.io.File;

public class CondFileExists extends Condition {

    private Expression<String> file;
    private boolean b;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kl, ParseResult pr)
    {
        this.file = (Expression<String>) exprs[0];
        this.b = i == 0;
        return true;
    }

    public String toString(@Nullable Event e, boolean b)
    {
        return "file exists";
    }

    public boolean check(Event e)
    {
        return this.b ? new File(this.file.getSingle(e)).exists() : !(new File(this.file.getSingle(e)).exists());
    }

}