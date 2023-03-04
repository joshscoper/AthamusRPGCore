package net.athamus.rpg.menu;

import net.athamus.rpg.Main;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScrollMenu extends Menu {

    protected Map<Integer, ItemStack> scrollMap = new HashMap<>();
    protected Map<Integer, ItemStack> greyMap = new HashMap<>();

    protected int middleslot = 31;
    protected int leftslot = 19;
    protected int rightslot = 25;

    protected int current = 1;

    protected List<Integer> forwardButton = Arrays.asList(42, 43, 44, 51, 52, 53, 15, 16, 17, 24, 25, 26, 33, 34, 35);
    protected List<Integer> backwardButton = Arrays.asList(36, 37, 38, 45, 46, 47, 9, 10, 11, 18, 19, 20, 27, 28, 29);

    public ScrollMenu(Main main, String name) {
        super(main, 54, name);
        populateScrollMap();
        populateGreyScrollMap();
        populateCards();
    }


    public abstract void onClose(InventoryCloseEvent event);


    public abstract void populateScrollMap();
    public abstract void populateGreyScrollMap();

    public void populateCards() {
        if (scrollMap.containsKey(current)) {
            inventory.setItem(middleslot, scrollMap.get(current));
        } else {
            inventory.setItem(middleslot, null);
        }
        if (greyMap.containsKey(current + 1)) {
            inventory.setItem(rightslot, greyMap.get(current + 1));

        } else {
            inventory.setItem(rightslot, null);
        }
        if (greyMap.containsKey(current - 1)) {
            inventory.setItem(leftslot, greyMap.get(current - 1));

        } else {
            inventory.setItem(leftslot, null);
        }
    }

    @Override
    public void execute(InventoryClickEvent e) {
        if (forwardButton.contains(e.getSlot())) {
            if (current + 1 <= scrollMap.size()){
                current = current + 1;
                populateCards();
            }
        }
        if (backwardButton.contains(e.getSlot())) {
            if (current - 1  > 0){
                current = current - 1;
                populateCards();
            }
        }

    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }
}

