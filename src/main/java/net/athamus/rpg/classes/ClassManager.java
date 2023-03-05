package net.athamus.rpg.classes;

import net.athamus.rpg.Main;
import net.athamus.rpg.player.CharacterManager;
import net.athamus.rpg.player.InventoryHandler;
import net.athamus.rpg.player.RPGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassManager implements Listener {

    private Map<CharacterClass, AthamusClass> classMap = new HashMap<>();
    private List<Player> combatMenu = new ArrayList<>();

    private final Main main;


    public ClassManager(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
        loadClasses();
    }

    public AthamusClass getClass(CharacterClass type){
        return classMap.get(type);
    }

    public void loadClasses(){
        for (CharacterClass type : CharacterClass.values()){
            try {
                classMap.put(type, type.getCharacterClass().getConstructor(Main.class).newInstance(main));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){
        Player p = e.getPlayer();
        CharacterManager characterManager = new CharacterManager(main);
        if (characterManager.hasActivePlayer(p)) {
            e.setCancelled(true);
            toggleCombatInventory(p);
        }
    }

    public void toggleCombatInventory(Player p){
        if (combatMenu.contains(p)){
            combatMenu.remove(p);
            InventoryHandler inventoryHandler = new InventoryHandler(main);
            inventoryHandler.loadInventory(p);
        } else {
            combatMenu.add(p);
            RPGPlayer character = main.getActiveCharacterMap().get(p);
            InventoryHandler inventoryHandler = new InventoryHandler(main);
            inventoryHandler.saveInventory(p.getInventory(),p, main.getActiveCharacterMap().get(p).getCharacterSlot());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    ItemStack[] clear = {new ItemStack(Material.AIR), new ItemStack(Material.AIR)};

                    p.getInventory().setContents(clear);

                    p.getInventory().setHelmet(character.getCharacterInventory().getContents()[character.getCharacterInventory().getContents().length - 2]);
                    p.getInventory().setChestplate(character.getCharacterInventory().getContents()[character.getCharacterInventory().getContents().length - 3]);
                    p.getInventory().setLeggings(character.getCharacterInventory().getContents()[character.getCharacterInventory().getContents().length - 4]);
                    p.getInventory().setBoots(character.getCharacterInventory().getContents()[character.getCharacterInventory().getContents().length - 5]);
                }
            }, 1L);
        }
    }

    public boolean isInCombatMenu(Player p){
        if (combatMenu.contains(p)){
            return true;
        }
        return false;
    }



}
