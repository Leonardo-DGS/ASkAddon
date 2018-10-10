package net.leomixer17.askaddon.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import java.io.File;
import java.io.IOException;

public class EffCreateFile extends Effect {
    
    private Expression<String> file;
    
    @Override
    protected void execute(Event event)
    {
        final File f = new File(file.getSingle(event));
        if (!f.exists())
        {
            try
            {
                f.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public String toString(Event event, boolean b)
    {
        return "create a file";
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
    {
        this.file = (Expression<String>) expressions[0];
        return true;
    }
    
}