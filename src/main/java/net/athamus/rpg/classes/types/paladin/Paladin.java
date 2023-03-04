package net.athamus.rpg.classes.types.paladin;

import net.athamus.rpg.Main;
import net.athamus.rpg.classes.AthamusClass;
import net.athamus.rpg.classes.equipment.ArmorType;
import net.athamus.rpg.classes.equipment.WeaponType;
import net.athamus.rpg.classes.skilltrees.Tree;

import java.util.List;

public class Paladin extends AthamusClass {
    public Paladin(Main main) {
        super(main);
    }

    @Override
    public List<ArmorType> wearableArmorTypes() {
        return null;
    }

    @Override
    public List<WeaponType> usableWeaponTypes() {
        return null;
    }

    @Override
    public Tree.TreeName skillTreeOne() {
        return null;
    }
}
