package net.athamus.rpg.classes.types.warrior;

import net.athamus.rpg.Main;
import net.athamus.rpg.classes.AthamusClass;
import net.athamus.rpg.classes.equipment.ArmorType;
import net.athamus.rpg.classes.equipment.WeaponType;
import net.athamus.rpg.classes.skilltrees.Tree;

import java.util.Arrays;
import java.util.List;

public class Warrior extends AthamusClass {
    public Warrior(Main main) {
        super(main);
    }

    @Override
    public List<ArmorType> wearableArmorTypes() {
        return Arrays.asList(ArmorType.PLATE);
    }

    @Override
    public List<WeaponType> usableWeaponTypes() {
        return Arrays.asList(WeaponType.SWORD,WeaponType.HAMMER);
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }

}
