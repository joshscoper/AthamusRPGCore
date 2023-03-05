package net.athamus.rpg.menu.menus;

import com.comphenix.protocol.PacketType;
import net.athamus.rpg.Main;
import net.athamus.rpg.menu.Menu;
import net.athamus.rpg.player.CharacterManager;
import net.athamus.rpg.player.InventoryHandler;
import net.athamus.rpg.player.RPGPlayer;
import net.athamus.rpg.player.file.PlayerFileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.logging.Level;

public class CharacterSelectionMenu extends Menu {

    private ItemStack createCharacter;

    public CharacterSelectionMenu(Main main, int size, String name, Player player) {
        super(main, size, name);
        for (int i = 0; i <= inventory.getSize() - 1; i++) {
            int slot = i + 1;
            PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
            String characterName = playerFileManager.getFile().getString("Characters." + String.valueOf(slot) + ".name");
            ItemStack characterIcon;
            if (characterName == null) {
                characterIcon = new ItemStack(Material.PAPER);
                ItemMeta meta = characterIcon.getItemMeta();
                meta.setDisplayName("&c&lError gathering info.");
                characterIcon.setItemMeta(meta);
                Bukkit.getLogger().log(Level.SEVERE, playerFileManager.getFile().getString("Characters." + slot + ".name"));
            }
            if (characterName.equalsIgnoreCase("Create Character")) {
                characterIcon = new ItemStack(Material.PAPER);
                ItemMeta meta = characterIcon.getItemMeta();
                meta.setDisplayName(main.formatString(main.getLangManager().getFile().getString("en.menu-items.create-character")));
                meta.setCustomModelData(1235);
                characterIcon.setItemMeta(meta);
            } else {
                characterIcon = new ItemStack(Material.PAPER);
                ItemMeta meta = characterIcon.getItemMeta();
                meta.setDisplayName(main.formatString("&eCharacter: &6&l" + slot));
                meta.setCustomModelData(1234);
                ArrayList<String> lore = new ArrayList<>();
                String className = playerFileManager.getFile().getString("Characters." + slot + ".class");
                String combatLevel = String.valueOf(playerFileManager.getFile().getInt("Characters." + slot + "combatLevel"));
                String balance = String.valueOf(playerFileManager.getFile().getInt("Characters." + slot + ".balance"));
                lore.add(main.formatString("&e}----[&6&lInfo&e]----{"));
                lore.add(main.formatString("&f&lClass: &e" + className));
                lore.add(main.formatString("&f&lLevel: &e" + combatLevel));
                lore.add(main.formatString("&f&lBalance: &e" + balance + " &f" + main.getConfig().get("config.module.economy.currency-symbol")));
                lore.add(main.formatString("&aLeft Click to select"));
                lore.add(main.formatString("&cRight Click to delete"));
                meta.setLore(lore);
                characterIcon.setItemMeta(meta);
            }
            inventory.setItem(i, characterIcon);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent e) {
        e.setCancelled(true);
        e.setCursor(null);
        CharacterManager characterManager = new CharacterManager(main);
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot() + 1;
        if (e.getClick().equals(ClickType.LEFT)) {
            if (characterManager.hasActivePlayer(player)) {
                InventoryHandler inventoryHandler = new InventoryHandler(main);
                inventoryHandler.saveInventory(player.getInventory(),player,slot);
                //characterManager.unloadCharacter(player, slot);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(main.formatString(main.getLangManager().getFile().getString("en.menu-items.create-character")))) {
                //Create Character
                RPGPlayer rpgPlayer = new RPGPlayer(main, player, slot);
                main.getActiveCharacterMap().put(player, rpgPlayer);
                rpgPlayer.setCharacterSlot(slot);
                characterManager.loadCharacter(player, slot);
                player.closeInventory();
                main.getCharacterCreatorManager().newCharacterCreator(player);
            } else {
                characterManager.loadCharacter(player, slot);
                main.getActiveCharacterMap().get(player).setCharacterSlot(slot);
                player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &aLoading character " + slot));
                player.teleport(main.getActiveCharacterMap().get(player).getLastLocation());
            }
        }
        if (e.getClick().equals(ClickType.RIGHT)) {
            if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(main.formatString(main.getLangManager().getFile().getString("en.menu-items.create-character")))) {
                characterManager.resetCharacter(player, slot);
                player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &eCharacter " + (slot) + " has been reset."));
                player.closeInventory();
                main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.formatString(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
            }
        }
    }
}
