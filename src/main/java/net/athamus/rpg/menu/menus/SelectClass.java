package net.athamus.rpg.menu.menus;

import net.athamus.rpg.Main;
import net.athamus.rpg.classes.CharacterClass;
import net.athamus.rpg.menu.Menu;
import net.athamus.rpg.menu.ScrollMenu;
import net.athamus.rpg.player.RPGPlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectClass extends ScrollMenu {
    public SelectClass(Main main, String name) {
        super(main, name);
        populateClassMap();
    }

    private Map<Integer, CharacterClass> classMap = new HashMap<>();
    private int current;
    private String className;

    private List<Integer> confirmButton = Arrays.asList(48, 49, 50, 39, 40, 41);

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void execute(InventoryClickEvent e) {
        super.execute(e);
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        if (confirmButton.contains(e.getSlot())){
            if (main.getCharacterCreatorManager().hasCreator(p)) {
                RPGPlayer rpgPlayer = main.getActiveCharacterMap().get(p);
                rpgPlayer.setCharacterClass(className);
                main.getCharacterManager().saveCharacter(p);
                String worldName = main.getConfig().getString("config.module.character-creation.first-zone");
                World world = Bukkit.getWorld(worldName);
                p.teleport(world.getSpawnLocation());
                p.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix" + "en.loaded-character-success")));
                main.getCharacterCreatorManager().getCharacterCreator(p).finishCreator();
            }
        }


    }

    @Override
    public void populateScrollMap() {
        int x= 0;
        for (CharacterClass type : CharacterClass.values()){
            x++;
            int typeInt = x;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getModelData());
            im.setDisplayName(ChatColor.GREEN + type.toString());
            className = type.toString();
            item.setItemMeta(im);
            scrollMap.put(x, item);

        }
    }

    public void populateClassMap() {
        int x= 0;
        for (CharacterClass type : CharacterClass.values()){
            x++;
            current = x;

        }
    }

    @Override
    public void populateGreyScrollMap() {
        int x= 0;
        for (CharacterClass type : CharacterClass.values()){
            x++;
            ItemStack item = new ItemStack(Material.BLAZE_ROD);
            ItemMeta im = item.getItemMeta();
            im.setCustomModelData(type.getGreyData());
            im.setDisplayName(ChatColor.GRAY + type.toString());
            item.setItemMeta(im);
            greyMap.put(x, item);

        }
    }
}
