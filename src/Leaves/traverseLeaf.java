package Leaves;

import Config.playerConfig;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.Arrays;
import java.util.List;

import static Config.playerConfig.*;

public class traverseLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return inHole() || inMonkeyRoom() && !walkToSpot;
    }

    @Override
    public int onLoop() {
        GameObject rope = GameObjects.closest(28775);

        if(Tabs.isOpen(Tab.PRAYER)){
            Tabs.open(Tab.INVENTORY);
            Sleep.sleepUntil(() -> Tabs.open(Tab.INVENTORY), 5000);
        }

        if(!walkToSpot) {
            Logger.log("Walk to spot is indeed not true");
            if (rope != null) {
                Logger.log("Rope is indeed not null");
                //Tile spot = new Tile(2572, 9168, 1);
                Tile spot = rope.getTile();
                Walking.shouldWalk(5);
                // Walking.walkExact(spot.translate(71, -9));

                /*Tile[] path = {
                        new Tile(2388, 9170, 1),
                        new Tile(2395, 9165, 1),
                        new Tile(2412, 9163, 1),
                        new Tile(2417, 9163, 1),
                        new Tile(2427, 9160, 1),
                        new Tile(2434, 9161, 1),
                        new Tile(2442, 9160, 1),
                        new Tile(2452, 9159, 1)
                };

                for(Tile nextPath : path){
                    if(Walking.shouldWalk(5)){
                        Walking.walkExact(nextPath);
                        Sleep.sleepUntil(() -> Players.getLocal().getTile().equals(nextPath), 5000);
                    }
                }

                Logger.log("Walked to spot.");
                walkToSpot = true;*/

                if(Walking.walkExact(new Tile(2388, 9170, 1))){
                    Sleep.sleep(2000, 3000);

                }
                if(Walking.walkExact(new Tile(2395, 9165, 1))){
                    Sleep.sleep(2000,3000);
                }
                if(Walking.walkExact(new Tile(2412, 9163, 1))) {
                    Sleep.sleep(2000, 3000);
                }
                if(Walking.walkExact(new Tile(2417, 9163, 1))) {
                    Sleep.sleep(2000, 3000);
                }
                if(Walking.walkExact(new Tile(2427, 9160, 1))) {
                    Sleep.sleep(2000, 3000);
                }
                if(Walking.walkExact(new Tile(2434, 9161, 1))){
                    Sleep.sleep(2000,3000);
                }
                if(Walking.walkExact(new Tile(2442, 9160, 1))) {
                    Sleep.sleep(2000, 3000);
                }
                if(Walking.walkExact(new Tile(2452, 9159, 1))) {
                    Sleep.sleep(2000, 3000);
                    Logger.log("Walked to spot is true?");
                    Sleep.sleepUntil(playerConfig::inMonkeyRoom, 5000);
                    walkToSpot = true;
                }

                //Sleep.sleepUntil(() -> spot.translate(71, -9).getArea(5).contains(Players.getLocal().getTile()), 5000);
                //walkToSpot = true;
            }
        }
        return 600;
    }
}
