package net.athamus.rpg.player;

import net.athamus.rpg.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RPGPlayer {

    private final Main main;
    private final Player player;


    private int characterSlot;
    private int combatLevel;
    private int experience;
    private String characterClass;
    private Inventory characterInventory;
    private String characterName;


    private int miningLevel;
    private int smithingLevel;
    private int fishingLevel;
    private int woodcuttingLevel;
    private int woodworkingLevel;
    private int farmingLevel;
    private int cookingLevel;
    private int thievingLevel;

    private int miningExp;
    private int smithingExp;
    private int fishingExp;
    private int woodcuttingExp;
    private int woodworkingExp;
    private int farmingExp;
    private int cookingExp;
    private int thievingExp;

    private float health;
    private float maxhealth;
    private int mana;
    private int maxmana;

    private int defence; //Reduces damage taken
    private int strength; //Increases damage dealt
    private int dexterity; //Increases movement speed
    private int constitution; //Increases HP
    private int intellect; //Increases spell damage
    private int wisdom; //Increases mana

    private int skillLevel;
    private String skillTree;
    private int skillPoints;

    private Location lastLocation;

    public RPGPlayer(Main main, Player player, int character){
        this.main = main;
        this.player = player;
        this.characterSlot = character;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public float getHealth() {
        return health;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public String getSkillTree() {
        return skillTree;
    }

    public void setSkillTree(String skillTree) {
        this.skillTree = skillTree;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public float getMana() {
        return mana;
    }

    public float getMaxhealth() {
        return maxhealth;
    }

    public float getMaxmana() {
        return maxmana;
    }

    public int getCharacterSlot() {
        return characterSlot;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getCookingExp() {
        return cookingExp;
    }

    public int getCookingLevel() {
        return cookingLevel;
    }

    public void setCombatLevel(int combatLevel) {
        this.combatLevel = combatLevel;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public void setCharacterInventory(Inventory characterInventory) {
        this.characterInventory = characterInventory;
    }

    public void setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
    }

    public void setSmithingLevel(int smithingLevel) {
        this.smithingLevel = smithingLevel;
    }

    public void setFishingLevel(int fishingLevel) {
        this.fishingLevel = fishingLevel;
    }

    public void setWoodcuttingLevel(int woodcuttingLevel) {
        this.woodcuttingLevel = woodcuttingLevel;
    }

    public void setWoodworkingLevel(int woodworkingLevel) {
        this.woodworkingLevel = woodworkingLevel;
    }

    public void setFarmingLevel(int farmingLevel) {
        this.farmingLevel = farmingLevel;
    }

    public void setCookingLevel(int cookingLevel) {
        this.cookingLevel = cookingLevel;
    }

    public void setMiningExp(int miningExp) {
        this.miningExp = miningExp;
    }

    public void setSmithingExp(int smithingExp) {
        this.smithingExp = smithingExp;
    }

    public int getThievingLevel() {
        return thievingLevel;
    }

    public void setThievingLevel(int thievingLevel) {
        this.thievingLevel = thievingLevel;
    }

    public int getThievingExp() {
        return thievingExp;
    }

    public void setThievingExp(int thievingExp) {
        this.thievingExp = thievingExp;
    }

    public void setFishingExp(int fishingExp) {
        this.fishingExp = fishingExp;
    }

    public void setWoodcuttingExp(int woodcuttingExp) {
        this.woodcuttingExp = woodcuttingExp;
    }

    public void setWoodworkingExp(int woodworkingExp) {
        this.woodworkingExp = woodworkingExp;
    }

    public void setFarmingExp(int farmingExp) {
        this.farmingExp = farmingExp;
    }

    public void setCookingExp(int cookingExp) {
        this.cookingExp = cookingExp;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setMaxhealth(float maxhealth) {
        this.maxhealth = maxhealth;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxmana(int maxmana) {
        this.maxmana = maxmana;
    }

    public int getExperience() {
        return experience;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public Inventory getCharacterInventory() {
        return characterInventory;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public int getSmithingLevel() {
        return smithingLevel;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public int getWoodcuttingLevel() {
        return woodcuttingLevel;
    }

    public int getWoodworkingLevel() {
        return woodworkingLevel;
    }

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public int getMiningExp() {
        return miningExp;
    }

    public int getSmithingExp() {
        return smithingExp;
    }

    public int getFishingExp() {
        return fishingExp;
    }

    public int getWoodcuttingExp() {
        return woodcuttingExp;
    }

    public int getWoodworkingExp() {
        return woodworkingExp;
    }

    public int getFarmingExp() {
        return farmingExp;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharacterSlot(int characterSlot) {
        this.characterSlot = characterSlot;
    }
}
