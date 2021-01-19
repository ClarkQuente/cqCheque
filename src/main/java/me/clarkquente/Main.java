package me.clarkquente;

import me.clarkquente.Commands.ChequeCommand;
import me.clarkquente.Listeners.ChequeListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Economy econ = null;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!setupEconomy()) {
            sendMessage("§4Vault não foi encontrado, desabilitando plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        sendMessage("Vault encontrado! Inicializando plugin.");

        loadConfiguration();

        getCommand("cheque").setExecutor(new ChequeCommand());
        Bukkit.getPluginManager().registerEvents(new ChequeListener(), this);
        sendMessage("Plugin inicializado com sucesso.");

    }

    @Override
    public void onDisable() {
    }

    public static void sendMessage(String msg) {
        Bukkit.getConsoleSender().sendMessage("§a[cqCheque] §e" + msg);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Main getInstance() {
        return instance;
    }

    public void loadConfiguration() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }
}
