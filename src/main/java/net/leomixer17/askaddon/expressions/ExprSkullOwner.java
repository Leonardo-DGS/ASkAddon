package net.leomixer17.askaddon.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.eclipse.jdt.annotation.Nullable;

public class ExprSkullOwner extends SimpleExpression<String> {

    private Expression<ItemStack> skull;

    @Override
    public Class<String> getReturnType()
    {
        return String.class;
    }

    @Override
    public boolean isSingle()
    {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3)
    {
        this.skull = (Expression<ItemStack>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean arg1)
    {
        return "skull owner";
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nullable
    protected String[] get(Event e)
    {
        if (this.skull.getSingle(e).getType().equals(Material.PLAYER_HEAD))
            return new String[]{((SkullMeta) this.skull.getSingle(e).getItemMeta()).getOwner()};
        return null;
    }

    @SuppressWarnings("deprecation")
    public void change(Event e, Object[] delta, ChangeMode mode)
    {
        if (mode == ChangeMode.SET)
        {
            SkullMeta meta = (SkullMeta) this.skull.getSingle(e).getItemMeta();
            meta.setOwner((String) delta[0]);
            this.skull.getSingle(e).setItemMeta(meta);
        }
    }

    public Class<?>[] acceptChange(ChangeMode mode)
    {
        return (Class[]) CollectionUtils.array(new Class[]{String.class});
    }

}
