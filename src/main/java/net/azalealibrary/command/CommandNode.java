package net.azalealibrary.command;

import net.azalealibrary.command.message.ChatMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.*;

public class CommandNode extends Command {

    private final List<CommandNode> children;

    public CommandNode(String name, CommandNode... children) {
        super(name);
        this.children = List.of(children);
    }

    public List<CommandNode> getChildren(CommandSender sender, Arguments arguments) {
        return children;
    }

    @Override
    public final boolean execute(@Nonnull CommandSender sender, @Nonnull String label, String[] args) {
        try {
            Arguments arguments = new Arguments(this, List.of(args));
            Map.Entry<CommandNode, Arguments> pair = getClosestMatch(sender, getChildren(sender, arguments), arguments, 0, this);
            Optional.ofNullable(pair.getKey()).ifPresent(n -> n.execute(sender, pair.getValue()));
        } catch (AzaleaException exception) {
            List<String> cropped = new ArrayList<>(TextUtil.split(exception.getMessage(), 46));

            for (String message : exception.getMessages()) {
                cropped.addAll(TextUtil.split(message, 62));
            }
            cropped.forEach(m -> ChatMessage.error(m).post("AZA", sender));
        } catch (Exception exception) {
            handleException(sender, exception);
        }
        return true;
    }

    @Override
    public final @Nonnull List<String> tabComplete(@Nonnull CommandSender sender, @Nonnull String label, String[] args) {
        Arguments arguments = new Arguments(this, List.of(args));

        if (arguments.size() > 1) {
            try {
                Map.Entry<CommandNode, Arguments> pair = getClosestMatch(sender, getChildren(sender, arguments), arguments, 0, this);
                List<String> options = Optional.ofNullable(pair.getKey()).map(n -> n.complete(sender, pair.getValue())).orElse(new ArrayList<>());
                return TextUtil.matching(arguments.getLast(), options);
            } catch (AzaleaException ignored) {
                return List.of(); // ignore exception
            } catch (Exception exception) {
                handleException(sender, exception);
            }
        }
        return complete(sender, arguments);
    }

    protected Map.Entry<CommandNode, Arguments> getClosestMatch(CommandSender sender, List<CommandNode> children, Arguments arguments, int depth, CommandNode node) {
        if (arguments.size() == depth) {
            return new AbstractMap.SimpleEntry<>(node, arguments.subArguments(depth));
        }
        CommandNode child = children.stream().filter(n -> n.getName().equals(arguments.get(depth)) && n.testPermissionSilent(sender)).findFirst().orElse(null);

        if (child == null) {
            return new AbstractMap.SimpleEntry<>(node, arguments.subArguments(depth));
        }
        return getClosestMatch(sender, child.getChildren(sender, arguments.subArguments(depth)), arguments, depth + 1, child);
    }

    public void execute(CommandSender sender, Arguments arguments) {
        throw new AzaleaException("Invalid " + getName() + " command issued", "Usage: " + getUsage());
    }

    public List<String> complete(CommandSender sender, Arguments arguments) {
        return getChildren(sender, arguments).stream().filter(n -> n.testPermissionSilent(sender)).map(Command::getName).toList();
    }

    @Override
    public boolean testPermissionSilent(@Nonnull CommandSender target) {
        String permission = getPermission();

        if (permission == null || permission.length() == 0) {
            return true;
        }

        for (String sub : permission.split(";")) {
            if (target.hasPermission(sub)) {
                return true;
            }
        }
        return false;
    }

    private static void handleException(CommandSender sender, Exception exception) {
        String message = exception.getMessage() != null ? exception.getMessage() : exception.toString();
        System.err.println(message);
        exception.printStackTrace();

        for (String line : TextUtil.split(message, 62)) {
            ChatMessage.error(line).post("AZA", sender);
        }
    }
}
