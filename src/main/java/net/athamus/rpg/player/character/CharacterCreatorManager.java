package net.athamus.rpg.player.character;

import net.athamus.rpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CharacterCreatorManager {

    public Map<Player, CharacterCreator> creatorMap = new HashMap<>();

    private final Main main;

    public CharacterCreatorManager(Main main) {
        this.main = main;
    }

    public void newCharacterCreator(Player p) {
                CharacterCreator creator = new CharacterCreator(p, main);
                creatorMap.put(p, creator);
                creator.startCreator();
    }

    public Map<Player, CharacterCreator> getCreatorMap(){
        return creatorMap;
    }

    public boolean hasCreator(Player p){
        if (creatorMap.containsKey(p)){
            return true;
        } else {
            return false;
        }
    }

    public CharacterCreator getCharacterCreator(Player p) {
        return creatorMap.get(p);
    }

    public void removeCharacterCreator(Player p) {
        creatorMap.remove(p);
    }


}
