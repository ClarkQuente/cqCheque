package me.clarkquente.Entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Cheque {

    private String autor;
    private double valor;

    public Cheque(String autor, double valor) {
        this.autor = autor;
        this.valor = valor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ItemStack createCheque() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a" + getValor());
        List<String> lore = new ArrayList<>();
        lore.add("§7Este cheque foi assinado por: §e" + getAutor() + ".");
        lore.add("§7E possui um valor de §a$" + getValor());
        lore.add("");
        lore.add("§7Clique com o botão direito para ativar este valor.");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
