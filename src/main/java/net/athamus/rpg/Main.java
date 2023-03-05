package net.athamus.rpg;

import net.athamus.rpg.classes.ClassManager;
import net.athamus.rpg.events.PlayerEvents;
import net.athamus.rpg.menu.MenuManager;
import net.athamus.rpg.player.CharacterManager;
import net.athamus.rpg.player.InventoryHandler;
import net.athamus.rpg.player.RPGPlayer;
import net.athamus.rpg.player.character.CharacterCreator;
import net.athamus.rpg.player.character.CharacterCreatorManager;
import net.athamus.rpg.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    //Configurable Files Initialization
    private FileManager configManager;
    private FileManager langManager;


    //Serializers
    private ItemSerializer itemSerializer;
    private InventorySerializer inventorySerializer;


    //Character Map
    private HashMap<Player, RPGPlayer> activeCharacterMap;

    //Menu Managers
    private MenuManager menuManager;

    //Util
    private SkullUtil skullUtil;
    private StringFilter stringFilter;

    //Character Creator
    private CharacterCreatorManager characterCreatorManager;
    private CharacterCreator characterCreator;

    //Classes
    private ClassManager classManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        activeCharacterMap = new HashMap<>();

        configManager = new FileManager(this, "config.yml");
        langManager = new FileManager(this, "lang.yml");

        itemSerializer = new ItemSerializer();
        inventorySerializer = new InventorySerializer();

        configManager.setupFile();
        langManager.setupFile();
        characterCreatorManager = new CharacterCreatorManager(this);

        skullUtil = new SkullUtil();
        stringFilter = new StringFilter();
        stringFilter.loadConfigs();

        classManager = new ClassManager(this);

        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupFiles(){
        configManager.setupFile();
        langManager.setupFile();
    }

    public void registerEvents(){
        new PlayerEvents(this);
        menuManager = new MenuManager(this);
    }



    public void registerCommands(){}

    public String formatString(String input){return ChatColor.translateAlternateColorCodes('&', input);
    }

    public FileManager getLangManager(){return langManager;}

    public CharacterCreatorManager getCharacterCreatorManager() {
        return characterCreatorManager;
    }

    public ItemSerializer getItemSerializer() {
        return itemSerializer;
    }

    public InventorySerializer getInventorySerializer() {
        return inventorySerializer;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public SkullUtil getSkullUtil() {
        return skullUtil;
    }

    public StringFilter getStringFilter() {
        return stringFilter;
    }

    public ClassManager getClassManager() {
        return classManager;
    }

    public HashMap<Player, RPGPlayer> getActiveCharacterMap(){return activeCharacterMap;}

}
