package Leaves;

import Config.playerConfig;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.prayer.Prayers;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

import static Config.playerConfig.*;

public class enterHoleLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return atHole() && emptyHole;
    }

    @Override
    public int onLoop() {

        GameObject hole = GameObjects.closest(28772);

        if(hasMagicPots()){
            String[] potionEnd = {"(1)", "(2)", "(3)", "(4)"};
            for(String pot : potionEnd){
                Inventory.interact("Magic potion" +pot, "Drink");
                break;
            }
        }

        if(Prayers.toggle(true, Prayer.PROTECT_FROM_MELEE)){
            Sleep.sleepUntil(() -> Prayers.toggle(true,Prayer.PROTECT_FROM_MELEE), 5000);
            if(hole != null){
                hole.interact("Enter");
                Sleep.sleepUntil(playerConfig::inHole, 5000);
            }
        }
        Logger.log("Melee prayer turned on!");
        return 600;
    }
}
