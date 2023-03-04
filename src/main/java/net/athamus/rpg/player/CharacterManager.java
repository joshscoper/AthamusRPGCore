package net.athamus.rpg.player;

import net.athamus.rpg.Main;
import net.athamus.rpg.player.file.PlayerFileManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CharacterManager {

    private final Main main;

    public CharacterManager(Main main){
        this.main = main;
    }

    public void loadCharacter(Player player, int characterSlot){
        RPGPlayer rpgPlayer = new RPGPlayer(main,player,characterSlot);
        main.getActiveCharacterMap().put(player, rpgPlayer);
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        //Setting Values for RPGPlayer
        String path = "Characters." + characterSlot;
        rpgPlayer.setCharacterSlot(characterSlot);
        rpgPlayer.setCharacterName(String.valueOf(characterSlot));
        rpgPlayer.setCombatLevel(playerFileManager.getFile().getInt(path + ".combatLevel"));
        rpgPlayer.setCharacterClass(playerFileManager.getFile().getString(path + ".class"));
        rpgPlayer.setExperience(playerFileManager.getFile().getInt(path + ".xp"));
        rpgPlayer.setHealth(playerFileManager.getFile().getInt(path + ".health"));
        rpgPlayer.setMaxhealth(playerFileManager.getFile().getInt(path + ".max-health"));
        //Deserialize Inventory
        Inventory inventory = player.getInventory();
        rpgPlayer.setCharacterInventory(main.getInventorySerializer().StringToInventory(playerFileManager.getFile().getString(path+".inventory")));
        //Stats
        rpgPlayer.setConstitution(playerFileManager.getFile().getInt(path + ".stats.constitution"));
        rpgPlayer.setDefence(playerFileManager.getFile().getInt(path + ".stats.defence"));
        rpgPlayer.setDexterity(playerFileManager.getFile().getInt(path+".stats.dexterity"));
        rpgPlayer.setStrength(playerFileManager.getFile().getInt(path+".stats.strength"));
        rpgPlayer.setIntellect(playerFileManager.getFile().getInt(path+".stats.intellect"));
        rpgPlayer.setWisdom(playerFileManager.getFile().getInt(path+".stats.wisdom"));
        //Skills
        rpgPlayer.setSkillLevel(playerFileManager.getFile().getInt(path + ".skills.level"));
        rpgPlayer.setSkillTree(playerFileManager.getFile().getString(path + ".skills.tree"));
        rpgPlayer.setSkillPoints(playerFileManager.getFile().getInt(path + ".skills.points"));
        //Professions
        rpgPlayer.setCookingLevel(playerFileManager.getFile().getInt(path + ".cooking.level"));
        rpgPlayer.setCookingExp(playerFileManager.getFile().getInt(path + ".cooking.xp"));
        rpgPlayer.setFarmingLevel(playerFileManager.getFile().getInt(path + ".farming.level"));
        rpgPlayer.setFarmingExp(playerFileManager.getFile().getInt(path + ".farming.xp"));
        rpgPlayer.setMiningLevel(playerFileManager.getFile().getInt(path + ".mining.level"));
        rpgPlayer.setMiningExp(playerFileManager.getFile().getInt(path + ".mining.xp"));
        rpgPlayer.setFishingLevel(playerFileManager.getFile().getInt(path + ".fishing.level"));
        rpgPlayer.setFishingExp(playerFileManager.getFile().getInt(path + ".fishing.xp"));
        rpgPlayer.setSmithingLevel(playerFileManager.getFile().getInt(path + ".smithing.level"));
        rpgPlayer.setSmithingExp(playerFileManager.getFile().getInt(path + ".smithing.xp"));
        rpgPlayer.setWoodcuttingLevel(playerFileManager.getFile().getInt(path + ".woodcutting.level"));
        rpgPlayer.setWoodcuttingExp(playerFileManager.getFile().getInt(path + ".woodcutting.xp"));
        rpgPlayer.setWoodworkingLevel(playerFileManager.getFile().getInt(path + ".woodworking.level"));
        rpgPlayer.setWoodworkingExp(playerFileManager.getFile().getInt(path + ".woodworking.xp"));
        rpgPlayer.setThievingLevel(playerFileManager.getFile().getInt(path + "thieving.level"));
        rpgPlayer.setThievingExp(playerFileManager.getFile().getInt(path + ".thieving.xp"));
        player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &aCharacter " + (characterSlot + 1) + " has been created."));
        saveCharacter(player);


        //Setting Player's Data
        if (rpgPlayer.getCharacterInventory().getContents().length > 0) {
            player.getInventory().setContents(rpgPlayer.getCharacterInventory().getContents());
        }
        player.setHealth(rpgPlayer.getHealth());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(rpgPlayer.getMaxhealth());
        player.setLevel(rpgPlayer.getCombatLevel());
        player.setExp(rpgPlayer.getExperience());
    }

    public void unloadCharacter(Player player){
        saveCharacter(player);
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setHealth(10);
        player.setFoodLevel(20);
        player.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.system-prefix") + " &cCharacter " + (main.getActiveCharacterMap().get(player).getCharacterSlot() + 1) + " has been unloaded."));
        main.getActiveCharacterMap().remove(player);
        playerFileManager.saveFile();
    }

    public void resetCharacter(Player player, int characterSlot){
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        playerFileManager.resetCharacter(characterSlot);
        playerFileManager.saveFile();
    }

    public void saveCharacter(Player player) {
        RPGPlayer rpgPlayer = main.getActiveCharacterMap().get(player);
        if (rpgPlayer != null) {
            PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
            String path = "Characters." + rpgPlayer.getCharacterSlot();
            FileConfiguration playerFile = playerFileManager.getFile();
            playerFile.set(path + ".name", rpgPlayer.getCharacterName());
            playerFile.set(path + ".combatLevel", rpgPlayer.getCombatLevel());
            playerFile.set(path + ".xp", rpgPlayer.getExperience());
            playerFile.set(path + ".class", rpgPlayer.getCharacterClass());
            playerFile.set(path + ".inventory", main.getInventorySerializer().InventoryToString(player.getInventory()));
            playerFile.set(path + ".cooking.level", rpgPlayer.getCookingLevel());
            playerFile.set(path + ".cooking.xp", rpgPlayer.getCookingExp());
            playerFile.set(path + ".farming.level", rpgPlayer.getFarmingLevel());
            playerFile.set(path + ".farming.xp", rpgPlayer.getFarmingExp());
            playerFile.set(path + ".mining.level", rpgPlayer.getMiningLevel());
            playerFile.set(path + ".mining.xp", rpgPlayer.getMiningExp());
            playerFile.set(path + ".fishing.level", rpgPlayer.getFishingLevel());
            playerFile.set(path + ".fishing.xp", rpgPlayer.getFishingExp());
            playerFile.set(path + ".smithing.level", rpgPlayer.getSmithingLevel());
            playerFile.set(path + ".smithing.xp", rpgPlayer.getSmithingExp());
            playerFile.set(path + ".woodcutting.level", rpgPlayer.getWoodcuttingLevel());
            playerFile.set(path + ".woodcutting.xp", rpgPlayer.getWoodcuttingExp());
            playerFile.set(path + ".woodworking.level", rpgPlayer.getWoodworkingLevel());
            playerFile.set(path + ".woodworking.xp", rpgPlayer.getWoodworkingExp());
            playerFile.set(path + ".thieving.level", rpgPlayer.getThievingLevel());
            playerFile.set(path + ".thieving.xp", rpgPlayer.getThievingExp());
            playerFile.set(path + ".last-location", player.getLocation().serialize());
            playerFile.set(path + ".stats.constitution", rpgPlayer.getConstitution());
            playerFile.set(path + ".stats.defence", rpgPlayer.getDefence());
            playerFile.set(path + ".stats.dexterity", rpgPlayer.getDexterity());
            playerFile.set(path + ".stats.strength", rpgPlayer.getStrength());
            playerFile.set(path + ".stats.intellect", rpgPlayer.getIntellect());
            playerFile.set(path + ".stats.wisdom", rpgPlayer.getWisdom());
            playerFile.set(path + ".skills.level", rpgPlayer.getSkillLevel());
            playerFile.set(path + ".skills.tree", rpgPlayer.getSkillTree());
            playerFile.set(path + ".skills.points", rpgPlayer.getSkillPoints());
            playerFileManager.saveFile();
        }
    }

    public RPGPlayer getRPGPlayer(Player player){return main.getActiveCharacterMap().get(player);}

    public boolean hasActivePlayer(Player player){
        if (main.getActiveCharacterMap().containsKey(player)){
            return true;
        }
        return false;
    }

    public void loadInventory(Player player){
        player.getInventory().setContents(getRPGPlayer(player).getCharacterInventory().getContents());
    }

    public void saveInventory(Player player){
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        String path = "Characters." + main.getActiveCharacterMap().get(player).getCharacterSlot();
        RPGPlayer rpgPlayer = main.getActiveCharacterMap().get(player);
        rpgPlayer.setCharacterInventory(player.getInventory());
        playerFileManager.saveFile();
    }



}
