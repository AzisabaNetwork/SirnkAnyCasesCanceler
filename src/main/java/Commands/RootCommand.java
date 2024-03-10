package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RootCommand implements TabExecutor {
    private static final List<SubCommand> commands = Arrays.asList(
            new ReloadConfig(),
            new AddIgnore(),
            new AddIgnoreMultiStyle(),
            new RemoveIgnore(),
            new RemoveIgnoreMultiStyle(),
            new LookList(),
            new Toggle()
    );

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "プレイヤーのみが実行できます");
            return true;
        }
        SubCommand subCommand = commands.stream()
                .filter(cmd -> cmd.getName().equalsIgnoreCase(args[0]))
                .findFirst()
                .orElse(null);
        if (subCommand == null) {
            sendHelp(sender);
            return true;
        }
        subCommand.execute((Player) sender, Arrays.copyOfRange(args, 1, args.length));
        return true;
    }

    private void sendHelp(CommandSender sender) {
        for (SubCommand command : commands) {
            sender.sendMessage(ChatColor.AQUA + "/sacc " + command.getName());
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return commands.stream().map(SubCommand::getName).collect(Collectors.toList());
        }
        if (args.length == 2 && sender instanceof Player) {
            return commands.stream()
                    .filter(subCommand -> subCommand.getName().equalsIgnoreCase(args[0]))
                    .findFirst()
                    .map(subCommand -> subCommand.suggest((Player) sender, args))
                    .orElse(Collections.emptyList());
        }
        return Collections.emptyList();
    }
}
