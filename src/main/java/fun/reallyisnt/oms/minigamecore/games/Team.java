package fun.reallyisnt.oms.minigamecore.games;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Team {

    private final Set<UUID> players;
    private String color;
    private String name;
    private int score = 0;

    public Team(Collection<Player> players, String color, String name) {
        this.players = players.stream().map(Player::getUniqueId).collect(Collectors.toSet());
        this.color = color;
        this.name = name;
    }

    public Set<UUID> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        this.players.add(p.getUniqueId());
    }

    public void removePlayer(Player p) {
        this.players.remove(p.getUniqueId());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.broadcastMessage("Your score is now"+this.score);
    }

    public void broadcastMessage(String msg) {
        for (UUID uuid : this.players) {
            Player p = Bukkit.getPlayer(uuid);
            if (p==null) {
                return;
            }
            p.sendMessage(msg);
        }
    }

    public void addScore(int points) {
        this.score+=points;
        this.broadcastMessage("Your score is now"+this.score);
    }
}
