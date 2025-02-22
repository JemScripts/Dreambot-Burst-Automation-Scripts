package Branches;

import Leaves.checkHoleLeaf;
import Leaves.enterHoleLeaf;
import org.dreambot.api.script.frameworks.treebranch.Branch;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.message.Message;

import static Config.playerConfig.*;

public class traversalBranch extends Branch implements ChatListener {

    public traversalBranch(){
        addLeaves(
                new checkHoleLeaf(),
                new enterHoleLeaf());
    }

    @Override
    public boolean isValid() {
        return !inHole() && !inMonkeyRoom();
    }

    @Override
    public int onLoop(){
        return super.onLoop();
    }
}
