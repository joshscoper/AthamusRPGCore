package net.athamus.rpg.player;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.nbtapi.iface.ReadableNBT;
import net.athamus.rpg.Main;
import net.athamus.rpg.player.file.PlayerFileManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler {

    private final Main main;

    public InventoryHandler(Main main){
        this.main = main;
    }

    public void saveInventory(Inventory inventory, Player player, int slot) {
        ReadWriteNBT contents = NBT.itemStackArrayToNBT(inventory.getContents());
        String str = contents.toString();
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        player.sendMessage(main.getActiveCharacterMap().get(player).getCharacterSlot() + " || " + str );
        playerFileManager.getFile().set("Characters." + slot + ".inventory", str);
        playerFileManager.saveFile();
    }

    public void loadInventory(Player player) {
        PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
        String str = playerFileManager.getFile().getString("Characters." + main.getActiveCharacterMap().get(player).getCharacterSlot() + ".inventory");
        ReadWriteNBT nbt = NBT.parseNBT(str);
        ItemStack[] contents = NBT.itemStackArrayFromNBT(nbt);
        Inventory inventory = player.getInventory();
        inventory.setContents(contents);
        main.getActiveCharacterMap().get(player).setCharacterInventory(player.getInventory());
    }


}
