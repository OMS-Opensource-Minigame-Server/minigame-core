package fun.reallyisnt.oms.minigamecore.games;

import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.logging.Level;

public class GameController {

    private Game game;
    private Plugin plugin;

    public GameController(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startGame() {
        if (this.game == null) {
            this.plugin.getLogger().log(Level.SEVERE,"Cannot start game, as there is no current game.");
            return;
        }

        this.game.matchmake(new HashSet<>(this.plugin.getServer().getOnlinePlayers()));
        this.game.startGame();
    }

    public void setGame(Game game) {
        if (this.game != null) {
            this.game.onDisable();
        }
        this.game = game;
        this.game.onEnable();
    }

    public Game getGame() {
        return game;
    }
}
