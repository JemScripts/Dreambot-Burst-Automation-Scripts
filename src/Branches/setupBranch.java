package Branches;

import Config.playerConfig;
import org.dreambot.api.script.frameworks.treebranch.Branch;
import org.dreambot.api.utilities.Logger;

import static Config.playerConfig.*;

public class setupBranch extends Branch {


    public setupBranch(){
        addLeaves();
    }


    @Override
    public boolean isValid() {
        /*if(!isEquipped()){
            return true;
        }*/
        return false;
    }

    @Override
    public int onLoop(){
        return super.onLoop();
    }
}
