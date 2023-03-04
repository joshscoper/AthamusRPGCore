package net.athamus.rpg.classes;

import net.athamus.rpg.classes.types.hunter.Hunter;
import net.athamus.rpg.classes.types.mage.Mage;
import net.athamus.rpg.classes.types.paladin.Paladin;
import net.athamus.rpg.classes.types.rogue.Rogue;
import net.athamus.rpg.classes.types.warrior.Warrior;

public enum CharacterClass {

    WARRIOR(7, 17, Warrior.class),
    MAGE(5, 15, Mage.class),
    ROGUE(3, 13, Rogue.class),
    HUNTER(4, 14, Hunter.class),
    PALADIN(6, 16, Paladin.class);

    private int modelData;
    private int greyData;
    private String icon;
    private Class<? extends AthamusClass> characterClass;

    CharacterClass(int modelData, int greyData, Class<? extends AthamusClass> characterClass) {
        this.modelData = modelData;
        this.greyData = greyData;
        this.characterClass = characterClass;
    }

    public int getModelData() {
        return modelData;
    }

    public String getIcon(){
        return icon;
    }

    public int getGreyData() {
        return greyData;
    }

    public Class<? extends AthamusClass> getCharacterClass() {
        return characterClass;
    }
}
