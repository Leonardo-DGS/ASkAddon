package net.leomixer17.askaddon;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.Classes;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import net.leomixer17.askaddon.conditions.*;
import net.leomixer17.askaddon.effects.*;
import net.leomixer17.askaddon.events.*;
import net.leomixer17.askaddon.expressions.*;
import net.leomixer17.askaddon.utils.EnumRegister;
import net.leomixer17.askaddon.utils.JSONMessage;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static ch.njol.skript.Skript.*;

public final class ASkAddon extends JavaPlugin {
    
    public static final HashMap<String, Object> objects = new HashMap<>();
    
    @Override
    public void onEnable()
    {
        Skript.registerAddon(this);
        
        // Events
        registerEvent("player changed world", EvtPlayerChangeWorld.class, PlayerChangedWorldEvent.class, "player change[d] world");
        if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].startsWith("v1_8_R"))
        {
            registerEvent("player change main hand", EvtPlayerChangeMainHand.class, PlayerChangedMainHandEvent.class, "player change[d] main hand");
            registerEvent("player swap hand items", EvtPlayerSwapHandItem.class, PlayerSwapHandItemsEvent.class, "player swap hand[ items]");
        }
        registerEvent("player chat tab complete", EvtPlayerChatTabComplete.class, PlayerChatTabCompleteEvent.class, "player[ chat] tab complete");
        registerEvent("player edit book", EvtPlayerEditBook.class, PlayerEditBookEvent.class, "[player] (edit book|book edit)");
        registerEvent("player shear entity", EvtPlayerShearEntity.class, PlayerShearEntityEvent.class, "player shear entity");
        registerEvent("player velocity", EvtPlayerVelocity.class, PlayerVelocityEvent.class, "player [change] velocity");
        registerEvent("player armor stand manipulate", EvtPlayerArmorStandManipulate.class, PlayerArmorStandManipulateEvent.class, "player armor stand manipulate");
        
        // Effects
        registerEffect(EffSendTitle.class, "send title %string% [with subtitle %-string%] [for %-timespan%] [with %-timespan% fade in] [with %-timespan% fade out] to %player%");
        registerEffect(EffSendActionBar.class, new String[]{"(send|show) [a[n]] action[ ]bar [(with|from)] [string] %string% to %players%", "set action[ ]bar of %players% to %string%"});
        registerEffect(EffLoadWorld.class, "load world %string%");
        registerEffect(EffUnloadWorld.class, "unload world %string%");
        registerEffect(EffSetKeepInventoryOnDeath.class, "set keep inventory to %boolean%");
        registerEffect(EffSetKeepXPOnDeath.class, "set keep (xp|level) to %boolean%");
        registerEffect(EffSendJson.class, "send %jsonmessage% to %player%");
        
        if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].startsWith("v1_8_R"))
        {
            registerEffect(EffShowBossBar.class, "show[ boss[ ]bar] %bossbar%");
            registerEffect(EffHideBossBar.class, "hide[ boss[ ]bar] %bossbar%");
            registerEffect(EffAddFlagToBar.class, "add flag %flag% to[ boss[ ]bar] %bossbar%");
            registerEffect(EffAddPlayerToBossBar.class, "add player %player% to[ boss][ ][bar] %bossbar%");
            registerEffect(EffRemoveAllPlayersFromBossBar.class, "remove all players from[ boss][ ][bar] %bossbar%");
            registerEffect(EffRemoveFlagFromBossBar.class, "remove flag %flag% from[ boss][ ][bar] %bossbar%");
            registerEffect(EffRemovePlayerFromBossBar.class, "remove player %player% from[ boss][ ][bar] %bossbar%");
        }
        
        registerEffect(EffCreateFile.class, "create[ (a|the)] file %string%");
        registerEffect(EffDeleteFile.class, "delete[ the] file %string%");
        registerEffect(EffRequestGarbageCollector.class, "[(request|do|make)[ a] ]garbage collect[or]");
        registerEffect(EffCreateWorld.class, "create[ a][ new] world[ (with name|called|named)] %string%");
        registerEffect(EffDeleteWorld.class, "delete[ the] world[ (with name|called|named)] %string%");
        registerEffect(EffLoadChunk.class, "load chunk[ at] %chunk%");
        registerEffect(EffUnloadChunk.class, "unload chunk[ at] %chunk%");
        registerEffect(EffSaveObjectInRam.class, new String[]{"save object %object% in ram[ with name] %string%", "save object %object%[ with name] %string% in ram"});
        registerEffect(EffDeleteObjectInRam.class, new String[]{"delete object[ with name] %string%[ saved] in ram", "delete all object[s][ saved] in ram"});
        registerEffect(EffSetCursorItem.class, "set cursor item to %itemstack%");
        registerEffect(EffSetClickedItem.class, "set clicked item to %itemstack%");
        
        // Expressions
        registerExpression(ExprInventoryName.class, String.class, ExpressionType.SIMPLE, new String[]{"(name of inventory|inventory's name)", "name of[ inventory] %inventory%"});
        registerExpression(ExprJsonMessage.class, JSONMessage.class, ExpressionType.SIMPLE, "json[ message][ (of|from)] %string%");
        registerExpression(ExprJsonMessageCommand.class, JSONMessage.class, ExpressionType.SIMPLE, new String[]{"%jsonmessage% suggest %string%", "%jsonmessage% run %string%"});
        registerExpression(ExprJsonMessageURL.class, JSONMessage.class, ExpressionType.SIMPLE, "%jsonmessage% open[ url] %string%");
        registerExpression(ExprJsonMessageTooltip.class, JSONMessage.class, ExpressionType.SIMPLE, "%jsonmessage% tooltip %string%");
        registerExpression(ExprJsonMessageItem.class, JSONMessage.class, ExpressionType.SIMPLE, "%jsonmessage% (item[ ]tooltip|tooltip[ ]item) %itemstack%");
        registerExpression(ExprJsonAppend.class, JSONMessage.class, ExpressionType.SIMPLE, "%jsonmessage% (then|append) %string%");
        
        if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].startsWith("v1_8_R"))
        {
            registerExpression(ExprNewBossBar.class, BossBar.class, ExpressionType.SIMPLE, "new boss[ ]bar");
            registerExpression(ExprBossBarColor.class, BarColor.class, ExpressionType.SIMPLE, "[[boss][ ][bar] ]bar colo[u]r of %bossbar%");
            registerExpression(ExprFlagsOfBossBar.class, BarFlag.class, ExpressionType.SIMPLE, "list of flags of[ [boss][ ][bar]] %bossbar%");
            registerExpression(ExprPlayersOfBossBar.class, Player.class, ExpressionType.SIMPLE, "list of players of[ [boss][ ][bar]] %bossbar%");
            registerExpression(ExprBossBarProgress.class, Number.class, ExpressionType.SIMPLE, "[[boss][ ][bar] ]progress of %bossbar%");
            registerExpression(ExprBossBarStyle.class, BarStyle.class, ExpressionType.SIMPLE, "[boss][ ][bar] bar style of %bossbar%");
            registerExpression(ExprBossBarTitle.class, String.class, ExpressionType.SIMPLE, "[boss][ ][bar] title of %bossbar%");
            registerExpression(ExprSerialiseBossBar.class, String.class, ExpressionType.SIMPLE, "seriali(z|s)ed [(contents|data)][ of][ boss][ ][bar] %bossbar%");
            registerExpression(ExprBossBarFromSerialised.class, BossBar.class, ExpressionType.SIMPLE, "boss[ ]bar from data %string%");
        }
        
        registerExpression(ExprDropsOfBlock.class, ItemStack.class, ExpressionType.SIMPLE, new String[]{"drops of[ block] %block%", "drops of[ block] %block% with[ item] %itemstack%"});
        registerExpression(ExprSkullOwner.class, String.class, ExpressionType.SIMPLE, "[the ]skull owner of %itemstack%");
        registerExpression(ExprGetObjectInRam.class, Object.class, ExpressionType.SIMPLE, "object[ with name] %string%[ saved] in ram");
        registerExpression(ExprNewLine.class, String.class, ExpressionType.SIMPLE, "(new[ ]line|nl)");
        registerExpression(ExprFreeMemory.class, Integer.class, ExpressionType.SIMPLE, "free memory");
        registerExpression(ExprMaxMemory.class, Integer.class, ExpressionType.SIMPLE, "max[imum] memory");
        registerExpression(ExprTotalMemory.class, Integer.class, ExpressionType.SIMPLE, "total memory");
        registerExpression(ExprJavaVersion.class, String.class, ExpressionType.SIMPLE, "java version");
        registerExpression(ExprPingOfPlayer.class, Integer.class, ExpressionType.SIMPLE, "ping of[ player] %player%");
        registerExpression(ExprMaxPlayers.class, Integer.class, ExpressionType.SIMPLE, "max players count");
        registerExpression(ExprLoadedChunks.class, Chunk.class, ExpressionType.SIMPLE, new String[]{"([all ]loaded chunks|loaded chunks of all worlds)", "loaded chunks of[ world] %world%"});
        // PlaceholderAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") instanceof PlaceholderAPIPlugin)
            registerExpression(ExprPlaceholderAPIPlaceholders.class, String.class, ExpressionType.SIMPLE, "placeholderapi [parse[d]] place[ ]holder[s] [(from|with)] %string% [[(from|for|of)] %-player%]");
        
        // Conditions
        registerCondition(CondStringStartsWith.class, new String[]{"%string% start[s][ with] %string%", "%string% (doesn't|don't|not) start[s][ with] %string%"});
        registerCondition(CondStringEndsWith.class, new String[]{"%string% end[s][ with] %string%", "%string% (doesn't|don't|not) start[s][ with] %string%"});
        registerCondition(CondHasPotionEffect.class, "[player] %player% has [potion] effect [type] [of] %potioneffecttype%");
        registerCondition(CondFileExists.class, "file %string% exists");
        registerCondition(CondExistsObjectInRam.class, "exists object [with name] %string% [saved] in ram");
        
        // Classes
        Classes.registerClass(new ClassInfo<>(JSONMessage.class, "jsonmessage"));
        if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].startsWith("v1_8_R"))
            Classes.registerClass(new ClassInfo<>(BossBar.class, "bossbar"));
        
        // Enums
        if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].startsWith("v1_8_R"))
        {
            EnumRegister.registerEnum(BarColor.class, "barcolor");
            EnumRegister.registerEnum(BarFlag.class, "flag");
            EnumRegister.registerEnum(BarStyle.class, "barstyle");
        }
    }
    
}
