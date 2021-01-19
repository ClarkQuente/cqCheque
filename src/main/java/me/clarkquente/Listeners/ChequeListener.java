package me.clarkquente.Listeners;

import me.clarkquente.Entity.Cheque;
import me.clarkquente.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ChequeListener implements Listener {

    private static Cheque cheque;

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();

        if(item == null || item.getType() == Material.AIR || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;

        if(item.getType() == Material.PAPER) {
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                double valorCheque = Double.parseDouble(ChatColor.stripColor(item.getItemMeta().getDisplayName()));

                if(item.getAmount() > 1) { item.setAmount(item.getAmount() - 1);
                } p.setItemInHand(new ItemStack(Material.AIR));

                Main.getEconomy().depositPlayer(p, valorCheque);
                p.sendMessage("§aFoi adicionado " + valorCheque + " em sua conta.");
            }
        }
    }
}
