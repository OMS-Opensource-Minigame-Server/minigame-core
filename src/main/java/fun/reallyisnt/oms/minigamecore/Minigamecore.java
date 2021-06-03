package fun.reallyisnt.oms.minigamecore;

import fun.reallyisnt.oms.minigamecore.commands.Start;
import fun.reallyisnt.oms.minigamecore.games.GameController;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigamecore extends JavaPlugin {

    private static Minigamecore instance;
    private GameController controller;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.controller = new GameController(this);

        this.getCommand("start").setExecutor(new Start());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Minigamecore getInstance() {
        return instance;
    }

    public GameController getController() {
        return controller;
    }
}
