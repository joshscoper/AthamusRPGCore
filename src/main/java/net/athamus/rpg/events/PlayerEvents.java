package net.athamus.rpg.events;

import net.athamus.rpg.Main;
import net.athamus.rpg.menu.menus.CharacterSelectionMenu;
import net.athamus.rpg.player.CharacterManager;
import net.athamus.rpg.player.InventoryHandler;
import net.athamus.rpg.player.file.PlayerFileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class PlayerEvents implements Listener {

    private final Main main;

    public PlayerEvents (Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //Join Message
        String joinMessage = main.getLangManager().getFile().getString("en.join-message");
        joinMessage = joinMessage.replaceAll("%player%", player.getDisplayName());
        joinMessage = main.formatString(joinMessage);
        event.setJoinMessage(joinMessage);
        //Create PlayerData File
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        if (!playerFileManager.fileExists()){
                playerFileManager.setupFile();
                player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &aYour PlayerData file has been created!"));
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();

        //Character Select & Creation
        if (clicked.getName().equals(main.formatString(main.getConfig().getString("config.module.character-creation.npc-name")))){
            main.getMenuManager().getPlayerSession(player).newMenu(new CharacterSelectionMenu(main, 9, main.formatString(main.getLangManager().getFile().getString("en.menu-title.character-selection")), player));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if (main.getActiveCharacterMap().containsKey(event.getPlayer())){
           // CharacterManager manager = new CharacterManager(main);
           // manager.unloadCharacter(event.getPlayer(), main.getActiveCharacterMap().get(event.getPlayer()).getCharacterSlot());
            InventoryHandler inventoryHandler = new InventoryHandler(main);
            inventoryHandler.saveInventory(event.getPlayer().getInventory(),event.getPlayer(),main.getActiveCharacterMap().get(event.getPlayer()).getCharacterSlot());
        }
    }

}
