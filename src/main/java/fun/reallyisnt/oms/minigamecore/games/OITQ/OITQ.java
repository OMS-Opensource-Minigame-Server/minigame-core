package fun.reallyisnt.oms.minigamecore.games.OITQ;

import fun.reallyisnt.oms.minigamecore.Minigamecore;
import fun.reallyisnt.oms.minigamecore.games.OMSGame;
import fun.reallyisnt.oms.minigamecore.games.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

public class OITQ extends OMSGame {

    private OITQListener listener;

    @Override
    public void startGame() {
        this.listener = new OITQListener(this);
        Minigamecore.getInstance().getServer().getPluginManager().registerEvents(this.listener,Minigamecore.getInstance());


        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);

        for (Team team : this.getTeams()){
            for (UUID uuid : team.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                if (p==null) continue;

                PlayerInventory inv = p.getInventory();
                inv.clear();
                inv.addItem(bow, sword, arrow);

                p.setGameMode(GameMode.ADVENTURE);

                p.setHealth(20);
            }
        }

        this.getWorld().setGameRule(GameRule.KEEP_INVENTORY, true);
        this.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        this.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.getWorld().setTime(6000L);
    }

    @Override
    public void stopGame() {
        HandlerList.unregisterAll(this.listener);

        for (Team team : this.getTeams()){
            for (UUID uuid : team.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                if (p==null) continue;

                PlayerInventory inv = p.getInventory();
                inv.clear();

                p.setGameMode(GameMode.ADVENTURE);

                p.setHealth(20);
            }
        }
    }
}
