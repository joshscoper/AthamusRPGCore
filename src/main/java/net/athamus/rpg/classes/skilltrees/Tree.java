package net.athamus.rpg.classes.skilltrees;

public enum Tree {
    //TODO replace 0 with item data
    MERCILESS_STRIKES(TreeName.BLOODLUST, 1, 0, 27, 5),
    ROARING_VOICE(TreeName.BLOODLUST, 1, 0, 18, 5),
    TERROR_SHOCK(TreeName.BLOODLUST, 2, 0, 28, 5),
    SAVAGERY(TreeName.BLOODLUST, 2, 0, 19, 5),
    GAUNTLET_MASTERY(TreeName.BLOODLUST, 3, 0, 38, 5),
    WARMONGER_SHOUT(TreeName.BLOODLUST, 3, 0, 29, 1),
    FOCUSED_CLEAVE(TreeName.BLOODLUST, 3, 0, 20, 3),
    RECKLESS(TreeName.BLOODLUST, 3, 0, 11, 3),
    DUAL_WIELDING(TreeName.BLOODLUST, 4, 0, 39, 1),
    IMPROVED_WARMONGER_SHOUT(TreeName.BLOODLUST, 4, 0, 30, 5),
    IMPROVED_BLOODFURY(TreeName.BLOODLUST, 4, 0, 12, 3),
    LEG_DAY(TreeName.BLOODLUST, 5, 0, 31, 2),
    BLOODY_RAGE(TreeName.BLOODLUST, 5, 0, 22, 3),
    AMBIDEXTROUS(TreeName.BLOODLUST, 6, 0, 41, 5),
    SKIRMISH(TreeName.BLOODLUST, 6, 0, 32, 1),
    EVEN_STANCE(TreeName.BLOODLUST, 7, 0, 33, 2),
    WHIRLWIND(TreeName.BLOODLUST, 7, 0, 15, 5),
    SANGUINE_STRIKE(TreeName.BLOODLUST, 8, 0, 35, 1);

    private int tier;
    private int customItemData;
    private int slot;
    private int maxLevel;
    private TreeName tree;


    Tree(TreeName tree, int tier, int customItemData, int slot, int maxLevel) {
        this.tier = tier;
        this.maxLevel = maxLevel;
        this.slot = slot;
        this.customItemData = customItemData;
        this.tree = tree;
    }

    public int getTier() {
        return tier;
    }
    public int getCustomItemData(){
        return customItemData;
    }
    public int getSlot(){
        return slot;
    }
    public int getMaxLevel(){
        return maxLevel;
    }

    public TreeName getTree(){
        return tree;
    }

    public enum TreeName{
        BLOODLUST, FORCE, FORTITUDE;
    }

}
