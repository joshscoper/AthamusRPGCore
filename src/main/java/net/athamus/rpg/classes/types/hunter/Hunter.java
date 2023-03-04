package net.athamus.rpg.classes.types.hunter;

import net.athamus.rpg.Main;
import net.athamus.rpg.classes.AthamusClass;
import net.athamus.rpg.classes.equipment.ArmorType;
import net.athamus.rpg.classes.equipment.WeaponType;
import net.athamus.rpg.classes.skilltrees.Tree;

import java.util.Arrays;
import java.util.List;

public class Hunter extends AthamusClass {

    public Hunter(Main main) {
        super(main);
    }

    public List<ArmorType> wearableArmorTypes() {
        return Arrays.asList(ArmorType.CLOTH, ArmorType.LEATHER);
    }


    public List<WeaponType> usableWeaponTypes() {
        return Arrays.asList(WeaponType.BOW);
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }
}

