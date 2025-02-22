package Leaves;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.magic.Ancient;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.frameworks.treebranch.Leaf;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.Arrays;

import static Config.playerConfig.*;

public class burstLeaf extends Leaf {

    private final Timer castSpell = new Timer(Calculations.random(600,1500));
    private final Timer walkBack = new Timer(Calculations.random(20000,30000));
    private final Timer resetAggro = new Timer(Calculations.random(600000, 700000));
    private final Timer drinkMagic = new Timer(Calculations.random(300000, 400000));

    @Override
    public boolean isValid() {
       return inMonkeyRoom() && hasPots() && walkToSpot && !groundPots() && !combinePots() && !drinkMagicPot;
    }

    @Override
    public int onLoop() {

        NPC monkey = NPCs.all().stream().filter(npc -> npc.getName().equals("Maniacal monkey") && npc.distance(Players.getLocal()) == 3).findFirst().orElse(null);
                //NPCs.closest("Maniacal monkey");

        //return Arrays.stream(RUNES.values()).allMatch(rune -> Inventory.all().stream().filter(item -> item != null && item.getName().startsWith(rune.getItem())).mapToInt(Item::getAmount).sum() >= rune.getQuantity());

        if(resetAggro.finished()){
            if(Walking.walkExact(new Tile(2442, 9160, 1))) {
                Sleep.sleep(2000, 3000);
            }
            if(Walking.walkExact(new Tile(2434, 9161, 1))){
                Sleep.sleep(2000,3000);
            }
            if(Walking.walkExact(new Tile(2427, 9160, 1))) {
                Sleep.sleep(2000, 3000);
            }
            if(Walking.walkExact(new Tile(2417, 9163, 1))) {
                Sleep.sleep(2000, 3000);
            }
            if(Walking.walkExact(new Tile(2412, 9163, 1))) {
                Sleep.sleep(2000, 3000);
            }
            if(Walking.walkExact(new Tile(2395, 9165, 1))){
                Sleep.sleep(2000,3000);
                walkToSpot = false;
                resetAggro.reset();
            }
        }

        if(walkBack.finished()){
            if(!Players.getLocal().getTile().equals(new Tile(2450, 9157, 1)) && !Players.getLocal().getTile().equals(new Tile(2454, 9164, 1))) {
                Walking.shouldWalk(5);
                if (Walking.walkExact(new Tile(2450, 9157, 1))) {
                    Sleep.sleep(2000, 3000);
                }
                if (Walking.walkExact(new Tile(2454, 9164, 1))) {
                    Sleep.sleep(2000, 3000);
                    walkBack.reset();
                }
            }else{
                walkBack.reset();
            }
        }

        if(Skills.getBoostedLevel(Skill.PRAYER) <= 40){
            String[] potionEnd = {"(1)", "(2)", "(3)", "(4)"};

            for(String potion : potionEnd){
                if (hasPots() && Skills.getBoostedLevel(Skill.PRAYER) <= 40){
                    if (Inventory.interact("Prayer potion" + potion)) {
                        Sleep.sleep(2000, 2500);
                    }
                }
            }
        }

        if(Skills.getBoostedLevel(Skill.PRAYER) <= 20 || !hasRunes() || Skills.getBoostedLevel(Skill.HITPOINTS) <= 30){
            if(Inventory.contains("Royal seed pod")){
                Inventory.interact("Royal seed pod", "Commune");
                Sleep.sleepUntil(() -> !inHole() && !inDungeon(), 5000);
            }
        }


        if(castSpell.finished()) {

                if (Magic.canCast(Ancient.ICE_BURST)) {
                        Magic.castSpellOn(Ancient.ICE_BURST, monkey);
                        castSpell.reset();
                        Sleep.sleep(1000, 2000);
                }
        }

        if(drinkMagic.finished()){
            if(hasMagicPots()){
                drinkMagicPot = true;
            }
        }



        return 600;
    }
}
