package me.clarkquente.Commands;

import me.clarkquente.Entity.Cheque;
import me.clarkquente.Main;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChequeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if(!(s instanceof Player)) return false;
        Player p = (Player) s;

        if(c.getName().equalsIgnoreCase("cheque")) {

            if(!p.hasPermission("cqcheque.criar")) {
                p.sendMessage("§cVocê não possui permissão para isso.");
                return false;
            }

            if(args.length != 1) p.sendMessage("§cComando incorreto. Use: /cheque <valor>.");

            if(args.length == 1) {

                if(!NumberUtils.isNumber(args[0])) {
                    p.sendMessage("§cInsira um valor válido.");
                    return false;
                }

                double valueMinimo = Main.getInstance().getConfig().getDouble("Cheque.ValorMinimo");
                double value = Double.parseDouble(args[0]);

                if(value < valueMinimo) {
                    p.sendMessage("§cO valor deste cheque está baixo demais! O valor minimo para criação de um cheque é " + valueMinimo);
                    return false;
                }

                double valueMaximo = Main.getInstance().getConfig().getDouble("Cheque.ValorMaximo");

                if(value > valueMaximo) {
                    p.sendMessage("§cO valor deste cheque está alto demais! O valor máximo para criação de um cheque é " + valueMaximo);
                    return false;
                }

                if(!Main.getEconomy().has(p, value)) {
                    p.sendMessage("§cVocê não possui o dinheiro para a criação do cheque.");
                    return false;
                }

                Cheque cheque = new Cheque(p.getName(), value);
                p.sendMessage("§eVocê criou um cheque no valor de §a§l$§r§a" + cheque.getValor() + "§e.");
                Main.getEconomy().withdrawPlayer(p, value);

                if (p.getInventory().firstEmpty() == -1) {
                    p.getWorld().dropItemNaturally(p.getLocation(), cheque.createCheque());
                    p.sendMessage("§cSeu inventário estava cheio e o seu cheque foi dropado no chão.");
                } else p.getInventory().addItem(cheque.createCheque());


            }
        }
        return false;
    }
}
