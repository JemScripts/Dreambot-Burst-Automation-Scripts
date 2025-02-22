package Leaves;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

import static Config.playerConfig.*;

public class pickupPrayerLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return inMonkeyRoom() && groundPots();
    }

    @Override
    public int onLoop() {

        if(Magic.isSpellSelected()){
            Magic.deselect();
        }

        GroundItem pot = GroundItems.closest(143);

        Logger.log("Picking up nearby potions...");
        if(pot != null){
                pot.interact("Take");
            }

        return 600;
    }
}
