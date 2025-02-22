package Leaves;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

import static Config.playerConfig.atHole;
import static Config.playerConfig.emptyHole;

public class checkHoleLeaf extends Leaf implements ChatListener {

    @Override
    public boolean isValid() {
        return atHole() && !emptyHole;
    }

    @Override
    public int onLoop() {
        GameObject hole = GameObjects.closest(28772);

        Logger.log("Checking hole...");

        if(hole != null)
        {
            hole.interact("Look-in");
            Sleep.sleepUntil(() -> hole.interact("Look-in"), 5000);
            Sleep.sleep(3000,5000);

            //Validation to check if there are any players
            if(emptyHole)
            {
                Logger.log("The hole is empty!");
            }
            else
            {
                Logger.log("Still full.");
                WorldHopper.hopWorld(Worlds.getRandomWorld());
                Logger.log("Hopping worlds");
                Sleep.sleepUntil(() -> WorldHopper.hopWorld(Worlds.getRandomWorld()), 5000);
                Sleep.sleep(1500,2500);
            }
         /*   if(!emptyHole){
                WorldHopper.hopWorld(Worlds.getRandomWorld());
                Logger.log("Hopping worlds");
                Sleep.sleepUntil(() -> WorldHopper.hopWorld(Worlds.getRandomWorld()), 5000);
            }*/
        }

        return 2400;
    }
}
