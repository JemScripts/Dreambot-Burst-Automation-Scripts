package Leaves;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;

import static Config.playerConfig.*;

public class drinkMagicLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return inMonkeyRoom() && drinkMagicPot;
    }

    @Override
    public int onLoop() {
        String[] potionEnd = {"(1)", "(2)", "(3)", "(4)"};

        for(String pot : potionEnd){
            if(Inventory.interact("Magic potion"+pot, "Drink")){
                Logger.log("Drank magic pot");
                drinkMagicPot = false;
                break;
            }

        }

        return 600;
    }
}
