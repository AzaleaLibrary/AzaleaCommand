package net.azalealibrary.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public final class AzaleaCommandApi {

    public static void register(Plugin plugin, CommandNode command) {
        try {
            setDefaultPermissions(command, command.getName());

            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(plugin.getName(), command);
        } catch (Exception exception) {
            throw new AzaleaException("An error occurred while registering command.", exception);
        }
    }

    private static void setDefaultPermissions(CommandNode command, String permission) {
        for (CommandNode child : command.getChildren()) {
            String sub = permission + "." + child.getName();

            if (child.getPermission() == null) {
                child.setPermission(sub);
            }
            setDefaultPermissions(child, sub);
        }
    }
}
