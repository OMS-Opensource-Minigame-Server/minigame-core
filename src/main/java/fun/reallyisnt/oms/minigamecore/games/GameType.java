package fun.reallyisnt.oms.minigamecore.games;

import fun.reallyisnt.oms.minigamecore.games.OITQ.OITQ;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public enum GameType {

    ONE_IN_THE_QUIVER(OITQ.class);

    private final Class<? extends Game> clazz;

    GameType(Class<? extends Game> clazz) {
        this.clazz = clazz;
    }

    public Game newInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to instantiate game.", e);
            throw new RuntimeException(e);
        }
    }
}
