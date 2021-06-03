package fun.reallyisnt.oms.minigamecore.commands;

import fun.reallyisnt.oms.minigamecore.Minigamecore;
import fun.reallyisnt.oms.minigamecore.games.GameController;
import fun.reallyisnt.oms.minigamecore.games.OITQ.OITQ;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Start implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameController GC = Minigamecore.getInstance().getController();

        if (GC.getGame() == null) {
            GC.setGame(new OITQ());
        }

        GC.startGame();

        return false;
    }
}
