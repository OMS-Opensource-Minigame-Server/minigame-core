package fun.reallyisnt.oms.minigamecore.games.OITQ;

import fun.reallyisnt.oms.minigamecore.games.Game;
import fun.reallyisnt.oms.minigamecore.games.Team;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class OITQListener implements Listener {

    private final Game game;

    public OITQListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }


        Player p = (Player) e.getEntity();

        Team team = this.game.getTeamForPlayer(p);

        if (team==null) {
            return;
        }

        Player killer = null;

        if (e.getDamager() instanceof Arrow) {
            e.setDamage(1000L);
            Arrow arrow = (Arrow) e.getDamager();

            if (arrow.getShooter() instanceof Player) {
                killer = (Player) arrow.getShooter();
            }
        } else if (e.getDamager() instanceof Player) {
            killer = (Player) e.getDamager();
        }

        if (p.getHealth() - e.getDamage() <= 0) {
            //It's a death

            if (p.getInventory().contains(Material.ARROW)) {
                for (ItemStack stack : p.getInventory().getContents()) {
                    if (stack != null && stack.getType() == Material.ARROW) {
                        stack.setAmount(1);
                        break;
                    }
                }
            } else {
                p.getInventory().addItem(new ItemStack(Material.ARROW));
            }

            if (killer == null) {
                return;
            }

            Team killingTeam = this.game.getTeamForPlayer(killer);

            if (killingTeam == null) {
                return;
            }

            killer.getInventory().addItem(new ItemStack(Material.ARROW));
            killingTeam.addScore(1);

            if (killingTeam.getScore() == 5) {
                game.winGame(killingTeam);
            }
        }
    }
}
