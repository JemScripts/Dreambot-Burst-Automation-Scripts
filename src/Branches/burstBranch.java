package Branches;

import Leaves.*;
import org.dreambot.api.script.frameworks.treebranch.Branch;

import static Config.playerConfig.inHole;
import static Config.playerConfig.inMonkeyRoom;

public class burstBranch extends Branch {

    public burstBranch(){
        addLeaves(
                new traverseLeaf(),
                new burstLeaf(),
                new pickupPrayerLeaf(),
                new combineLeaf(),
                new drinkMagicLeaf());
    }

    @Override
    public boolean isValid() {
        return inHole() || inMonkeyRoom();
    }

    @Override
    public int onLoop(){
        return super.onLoop();
    }
}
