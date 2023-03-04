package net.athamus.rpg.player.character;

import net.athamus.rpg.Main;
import net.athamus.rpg.menu.menus.SelectClass;
import net.athamus.rpg.player.RPGPlayer;
import org.bukkit.entity.Player;

public class CharacterCreator {

    private int characterSlot;

    private final Main main;
    private final Player player;

    public CharacterCreator(Player player, Main main){
        this.main = main;
        this.player = player;
    }

    public void startCreator(){
        main.getMenuManager().getPlayerSession(player).newMenu(new SelectClass(main, main.formatString(main.getLangManager().getFile().getString("en.menu-title.character-creation"))));
    }

    public void finishCreator(){
        RPGPlayer rpgPlayer = main.getActiveCharacterMap().get(player);
        rpgPlayer.setCharacterSlot(characterSlot);
        main.getCharacterCreatorManager().removeCharacterCreator(player);
        player.closeInventory();
    }

    public void setClass(String className){
        RPGPlayer rpgPlayer = main.getActiveCharacterMap().get(player);
        rpgPlayer.setCharacterClass(className);
    }


}
