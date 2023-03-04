package net.athamus.rpg.player.file;

import net.athamus.rpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class PlayerFileManager {

    private final Main main;
    private final Player player;
    private final File directory;
    private final File file;
    private final String fileName;
    private final FileConfiguration config;

    public PlayerFileManager(Main main, Player player){
        this.main = main;
        this.player = player;
        this.fileName = player.getUniqueId().toString() + ".yml";
        this.directory = new File(main.getDataFolder() + File.separator + "PlayerData");
        this.file = new File(directory, fileName);
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void setupFile(){
        if (!directory.exists()){
            directory.mkdirs();
        }
        if (!file.exists()) {
            createFile();
        }

    }

    //Create File
    public void createFile() {
        for (int i = 0; i <= 8; i++) {
            config.set("Characters." + Integer.valueOf(i) + ".name", "Create Character");
            config.set("Characters." + Integer.valueOf(i) + ".class", "None");
            config.set("Characters." + Integer.valueOf(i) + ".combatLevel", 1);
            config.set("Characters." + Integer.valueOf(i) + ".xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".balance", main.getConfig().getInt("config.module.economy.starting-balance"));
            config.set("Characters." + Integer.valueOf(i) + ".health", 10);
            config.set("Characters." + Integer.valueOf(i) + ".max-health", 20);
            config.set("Characters." + Integer.valueOf(i) + ".mana", 10);
            config.set("Characters." + Integer.valueOf(i) + ".max-mana", 10);
            Inventory inventory = player.getInventory();
            inventory.addItem(new ItemStack(Material.AIR));
            config.set("Characters." + Integer.valueOf(i) + ".inventory", main.getInventorySerializer().InventoryToString(inventory));
            config.set("Characters." + Integer.valueOf(i) + ".stats.constitution",1);
            config.set("Characters." + Integer.valueOf(i) + ".stats.defence",1);
            config.set("Characters." + Integer.valueOf(i) + ".stats.dexterity",1);
            config.set("Characters." + Integer.valueOf(i) + ".stats.strength",1);
            config.set("Characters." + Integer.valueOf(i) + ".stats.intellect",1);
            config.set("Characters." + Integer.valueOf(i) + ".stats.wisdom",1);
            config.set("Characters." + Integer.valueOf(i) + ".skills.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".skills.tree", "None");
            config.set("Characters." + Integer.valueOf(i) + ".skills.points", 3);
            config.set("Characters." + Integer.valueOf(i) + ".profession.mining.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.mining.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.smithing.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.smithing.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.fishing.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.fishing.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.farming.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.farming.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.cooking.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.cooking.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.woodcutting.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.woodcutting.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.woodworking.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.woodworking.xp", 0);
            config.set("Characters." + Integer.valueOf(i) + ".profession.thieving.level", 1);
            config.set("Characters." + Integer.valueOf(i) + ".profession.thieving.xp", 0);
            World world = Bukkit.getWorld(main.getConfig().getString("config.module.character-creation.first-zone"));
            Location spawn = world.getSpawnLocation();
            config.set("Characters." + Integer.valueOf(i) + ".last-location", spawn);
        }
        saveFile();
    }


    public void resetCharacter(int character){
        int i = character;
        config.set("Characters." + Integer.valueOf(i) + ".name", "Create Character");
        config.set("Characters." + Integer.valueOf(i) + ".class", "None");
        config.set("Characters." + Integer.valueOf(i) + ".combatLevel", 1);
        config.set("Characters." + Integer.valueOf(i) + ".xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".balance", main.getConfig().getInt("config.module.economy.starting-balance"));
        config.set("Characters." + Integer.valueOf(i) + ".health", 10);
        config.set("Characters." + Integer.valueOf(i) + ".max-health", 20);
        config.set("Characters." + Integer.valueOf(i) + ".mana", 10);
        config.set("Characters." + Integer.valueOf(i) + ".max-mana", 10);
        Inventory inventory = player.getInventory();
        inventory.addItem(new ItemStack(Material.AIR));
        config.set("Characters." + Integer.valueOf(i) + ".inventory", main.getInventorySerializer().InventoryToString(inventory));
        config.set("Characters." + Integer.valueOf(i) + ".stats.constitution",1);
        config.set("Characters." + Integer.valueOf(i) + ".stats.defence",1);
        config.set("Characters." + Integer.valueOf(i) + ".stats.dexterity",1);
        config.set("Characters." + Integer.valueOf(i) + ".stats.strength",1);
        config.set("Characters." + Integer.valueOf(i) + ".stats.intellect",1);
        config.set("Characters." + Integer.valueOf(i) + ".stats.wisdom",1);
        config.set("Characters." + Integer.valueOf(i) + ".skills.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".skills.tree", "None");
        config.set("Characters." + Integer.valueOf(i) + ".skills.points", 3);
        config.set("Characters." + Integer.valueOf(i) + ".profession.mining.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.mining.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.smithing.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.smithing.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.fishing.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.fishing.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.farming.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.farming.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.cooking.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.cooking.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.woodcutting.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.woodcutting.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.woodworking.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.woodworking.xp", 0);
        config.set("Characters." + Integer.valueOf(i) + ".profession.thieving.level", 1);
        config.set("Characters." + Integer.valueOf(i) + ".profession.thieving.xp", 0);
        World world = Bukkit.getWorld(main.getConfig().getString("config.module.character-creation.first-zone"));
        Location spawn = world.getSpawnLocation();
        config.set("Characters." + Integer.valueOf(i) + ".last-location", spawn);
    }

    //Save File

    public void saveFile(){
        try{
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileExists(){
        if (file.exists()) return true;
        return false;
    }

    public FileConfiguration getFile(){
        return config;
    }
}


