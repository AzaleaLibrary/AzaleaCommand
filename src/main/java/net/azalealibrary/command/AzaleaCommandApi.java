package net.azalealibrary.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public final class AzaleaCommandApi {

    public static void register(Plugin plugin, Class<? extends CommandNode> command) {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(plugin.getName(), command.getConstructor().newInstance());
        } catch (Exception exception) {
            throw new AzaleaException("An error occurred while registering command.", exception);
        }
    }
}
