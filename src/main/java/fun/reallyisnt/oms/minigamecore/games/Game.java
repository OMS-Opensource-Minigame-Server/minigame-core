package fun.reallyisnt.oms.minigamecore.games;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Set;

public interface Game {

    void onEnable();
    void onDisable();

    void startGame();
    void stopGame();
    void winGame(Team team);

    void setTeamSize(int size);
    int getTeamSize();
    void setTeamLimit(int limit);
    int getTeamLimit();

    World getWorld();

    Set<Team> getTeams();
    Team getTeamForPlayer(Player p);

    void matchmake(Set<Player> players);
}
