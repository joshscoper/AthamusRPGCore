package net.athamus.rpg.menu.menus;

import com.comphenix.protocol.PacketType;
import net.athamus.rpg.Main;
import net.athamus.rpg.menu.Menu;
import net.athamus.rpg.player.CharacterManager;
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

    public CharacterSelectionMenu(Main main, int size, String name,Player player) {
        super(main, size, name);
        for (int i = 0; i <= inventory.getSize() - 1; i++) {
            PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
            String characterName = playerFileManager.getFile().getString("Characters." + String.valueOf(i) + ".name");
            ItemStack characterIcon;
            if (characterName == null) {
                characterIcon = new ItemStack(Material.PAPER);
                ItemMeta meta = characterIcon.getItemMeta();
                meta.setDisplayName("&c&lError gathering info.");
                characterIcon.setItemMeta(meta);
                Bukkit.getLogger().log(Level.SEVERE, playerFileManager.getFile().getString("Characters." + i + ".name"));
            }
            if (characterName.equalsIgnoreCase("Create Character")) {
                characterIcon = main.getSkullUtil().getSkull("http://textures.minecraft.net/texture/171d8979c1878a05987a7faf21b56d1b744f9d068c74cffcde1ea1edad5852");
                SkullMeta meta = (SkullMeta) characterIcon.getItemMeta();
                meta.setDisplayName(main.formatString(main.getLangManager().getFile().getString("en.menu-items.create-character")));
                characterIcon.setItemMeta(meta);
            } else {
                characterIcon = new ItemStack(Material.PAPER);
                ItemMeta meta = characterIcon.getItemMeta();
                meta.setDisplayName(main.formatString("&eCharacter: &6&l" + i));
                meta.setCustomModelData(1234);
                ArrayList<String> lore = new ArrayList<>();
                String className = playerFileManager.getFile().getString("Characters." + i + ".class");
                String combatLevel = String.valueOf(playerFileManager.getFile().getInt("Characters." + i + "combatLevel"));
                String balance = String.valueOf(playerFileManager.getFile().getInt("Characters." + i + ".balance"));
                lore.add(main.formatString("&e}----[&6&lInfo&e]----{"));
                lore.add(main.formatString("&f&lClass: &e" + className));
                lore.add(main.formatString("&f&lLevel: &e" + combatLevel));
                lore.add(main.formatString("&f&lBalance: &e" + balance + " &f" + main.getConfig().get("config.module.economy.currency-symbol")));
                lore.add(main.formatString("&aLeft Click to select"));
                lore.add(main.formatString("&cRight Click to delete"));
                meta.setLore(lore);
                characterIcon.setItemMeta(meta);
            }
            inventory.setItem(i,characterIcon);
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
        CharacterManager characterManager = main.getCharacterManager();
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        if (e.getClick().equals(ClickType.LEFT)){
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(main.formatString(main.getLangManager().getFile().getString("en.menu-items.create-character")))) {
            //Create Character
            RPGPlayer rpgPlayer = new RPGPlayer(main, player, slot);
            main.getActiveCharacterMap().put(player, rpgPlayer);
            main.getCharacterManager().loadCharacter(player, slot);
            player.closeInventory();
            main.getCharacterCreatorManager().newCharacterCreator(player);
        }
            } else {
                    if (e.getClick().equals(ClickType.LEFT)) {
                    RPGPlayer rpgPlayer = main.getActiveCharacterMap().put(player, new RPGPlayer(main, player, slot));
                    rpgPlayer.setCharacterSlot(slot);
                    main.getActiveCharacterMap().put(player, rpgPlayer);
                    characterManager.loadCharacter(player, slot);
                    player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &aLoading character " + slot));
                 }
                    if (e.getClick().equals(ClickType.RIGHT)){
                    characterManager.resetCharacter(player,slot);
                    player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &eCharacter " + (slot + 1) + " has been reset."));
                    player.closeInventory();
                    main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.formatString(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
            }
        }

    }
}
