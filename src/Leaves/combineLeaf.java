package Leaves;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;

import java.util.Arrays;
import java.util.List;

import static Config.playerConfig.inMonkeyRoom;
import static Config.playerConfig.combinePots;

public class combineLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return combinePots() && inMonkeyRoom();
    }

    @Override
    public int onLoop() {
        if(Magic.isSpellSelected()){
            Magic.deselect();
        }

        String[] potionEnd = {"(1)","(2)","(3)"};

        for (String potion : potionEnd){

            List<Item> potions = Inventory.all(i -> i.getName().equals("Prayer potion"+potion));

            if(potions.size() > 1){
                Inventory.combine(potions.get(0), potions.get(1));
            }
            /*Inventory.interact("Vial", "Drop");
            if(!Inventory.contains("Vial")) {
                break;
            }*/
        }

        if(Inventory.contains("Vial")){
            for(int i = Inventory.count("Vial"); i > 0; i--){
                Inventory.interact("Vial", "Drop");
                Sleep.sleepUntil(() -> !Inventory.contains("Vial"), 500);
            }
        }

        if(Inventory.isFull() && !Inventory.contains("Vial")){
            if(Inventory.contains("Royal seed pod")){
                Inventory.interact("Royal seed pod", "Commune");
                Logger.log("Inventory is full of good stuff so I'm getting tf outta here");
                Sleep.sleepUntil(() -> !inMonkeyRoom(), 5000);
            }
        }
        return 600;
    }
}
