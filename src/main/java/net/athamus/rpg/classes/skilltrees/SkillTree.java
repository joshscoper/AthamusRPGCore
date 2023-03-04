package net.athamus.rpg.classes.skilltrees;

import net.athamus.rpg.Main;
import net.athamus.rpg.menu.Menu;
import net.athamus.rpg.player.RPGPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillTree extends Menu {

    private Player p;
    private List<Tree> skills = new ArrayList<>();
    private final Main main;


    public SkillTree(Main main, Tree.TreeName name, Player p) {
        super(main, 54, name.toString());
        this.main = main;
        this.p = p;
        for (Tree t : Tree.values()) {
            if (t.getTree().equals(name)) {

                inventory.setItem(t.getSlot(), skillItem(t));
                skills.add(t);
            }
        }
    }

    public ItemStack skillItem(Tree t){
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(main.formatString("&a" + t.toString().replaceAll("_", " ")));
        im.setCustomModelData(t.getCustomItemData());
        item.setItemMeta(im);
        return item;
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent e) {
        if (e.getCurrentItem() == null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;
        Tree tree = null;
        int totalPoints = 0;
        RPGPlayer character = main.getActiveCharacterMap().get(p);
        for (Tree t : skills) {
            if (e.getSlot() == t.getSlot()) {
                tree = t;
                totalPoints = totalPoints + character.getSkillLevel();
                break;
            }
        }
        if (tree == null)
            return;
        int tier = tree.getTier();
        int requiredPoints = tier * 5 - 5;
        if (totalPoints < requiredPoints) {
            p.sendMessage(main.formatString(main.getLangManager().getFile().getString("en.insufficient-skill-points")));
            return;
        } else {
            try {
                if ((int) character.getSkillPoints()> 0) {
                    character.setSkillPoints((int) character.getSkillPoints() - 1);
                    character.setSkillLevel(character.getSkillLevel() + 1);
                    e.getClickedInventory().setItem(e.getSlot(), skillItem(tree));
                }
            } catch (ClassCastException n){

                System.out.println(main.formatString("&c&lTried to fetch the users skill points but it returned a value that was not an integer"));

                n.printStackTrace();
            }
        }
    }
}
