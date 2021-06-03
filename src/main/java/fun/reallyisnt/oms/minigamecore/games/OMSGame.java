package fun.reallyisnt.oms.minigamecore.games;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class OMSGame implements Game {

    private int teamSize = 1;
    private int teamLimit = 8;
    private Set<Team> teams;

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void winGame(Team team) {
        this.stopGame();
        Bukkit.broadcastMessage("Team "+team.getName()+" has won the game!");
    }

    @Override
    public World getWorld() {
        return Bukkit.getServer().getWorld("world");
    }

    @Override
    public void setTeamSize(int size) {
        this.teamSize = size;
    }

    @Override
    public void setTeamLimit(int limit) {
        this.teamLimit = limit;
    }

    //Currently this is useless, but soon™
    @Override
    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public int getTeamLimit() {
        return teamLimit;
    }

    public Set<Team> getTeams() {
        return Collections.unmodifiableSet(teams);
    }

    //TODO: Improve it so players can try to get a certain team and matchmake parties
    @Override
    public void matchmake(Set<Player> players) {
        Team[] teams = new Team[this.teamLimit];

        for (int i=0; i<this.teamLimit; i++){
            teams[i] = new Team(Collections.EMPTY_SET,""+i,""+i);
        }

        int currentTeam = 0;
        for (Player player : players) {
            teams[currentTeam].addPlayer(player);
            player.sendMessage("You have been added to the "+currentTeam+" team");
            currentTeam = (currentTeam + 1)%this.teamLimit;
        }

        this.teams = new HashSet<>(Arrays.asList(teams));
    }

    public Team getTeamForPlayer(Player p) {
        for (Team team : this.teams) {
            if (team.getPlayers().contains(p.getUniqueId())) return team;
        }
        return null;
    }
}
