import Branches.burstBranch;
import Branches.setupBranch;
import Branches.traversalBranch;
import Config.playerConfig;
import org.dreambot.api.Client;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.combat.CombatStyle;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.frameworks.treebranch.TreeScript;
import org.dreambot.api.script.frameworks.treebranch.Root;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.map.Region;
import org.dreambot.api.wrappers.widgets.message.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static Config.playerConfig.emptyHole;

@ScriptManifest(category = Category.COMBAT, name = "MonkeyBurst", author = "Cem", version = 1.0)

public class Main extends TreeScript implements ChatListener {

    private static int currentmagXP;
    private static int startmagXP;
    private static int gainedmagXP;
    private static int startmagLvl;
    private static int currentmagLvl;
    private static int gainedmagLvl;

    private long timeBegan;
    private long timeRan;

    private final Image bg = getImage("https://i.ibb.co/m5z51gWv/Ottoman.png");

    @Override
    public void onStart(){

        timeBegan = System.currentTimeMillis();

        Root root = getRoot();

            root.addBranches
            (
                new setupBranch(),
                new traversalBranch(),
                new burstBranch()

            );
    }

    @Override
    public void onMessage(Message message){
        String text = message.getMessage();

        if(text.contains("You peek down and see no adventurers")){
            Logger.log("The hole is empty!");
            emptyHole = true;
            Sleep.sleep(1000,2000);
        }
    }

    private final BasicStroke stroke1 = new BasicStroke(3);

    private final Font font1 = new Font("Arial", 1, 30);
    private final Font font2 = new Font("Arial", 1, 20);
    private final Font font3 = new Font("Arial", 1, 13);
    private final Font font4 = new Font("Arial", 1, 16);
    private final Font font5 = new Font("Arial", 1, 12);

    @Override
    public void onPaint(Graphics g1) {
        timeRan = System.currentTimeMillis() - timeBegan;

        currentmagXP = Skills.getExperience(Skill.MAGIC);
        currentmagLvl = Skills.getRealLevel(Skill.MAGIC);
        gainedmagXP = currentmagXP - startmagXP;
        gainedmagLvl = currentmagLvl - startmagLvl;



        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(initialised) {
            // WidgetChild chatBox = chatWidget.getChild(0);

            g.drawImage(bg, 1, 340, null);
            g.setColor(Color.WHITE);
            g.setFont(font4);
            g.drawString("Current Status:", 30, 400);
            g.drawString("Time Ran:", 30, 380);
            g.setFont(font3);
            g.drawString("Initialising...", 150, 400);
            g.setFont(font4);
            g.drawString("Combat Style:", 30, 420);
            g.setFont(font3);
            g.drawString("" + Combat.getCombatStyle(), 140, 420);
            g.setFont(font4);
            g.drawString("XP Gained:", 30, 440);
            g.drawString("Levels Gained:", 30, 460);
            g.setFont(font3);
            g.drawString("" + gainedmagXP, 120, 440);
            g.drawString("" + gainedmagLvl, 150, 460);
            g.drawString(ft(timeRan), 120, 380);
        }
    }

    private Image getImage(String url){
        try{
            return ImageIO.read(new URL(url));
        }
        catch (IOException e){}
        return null;
    }

    private String ft(long duration)
    {
        String res = "";
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));

        if(days == 0) {
            res = (hours + ":" + minutes + ":" + seconds);
        }else{
            res = (days + ":" + hours + ":" + minutes + ":" + seconds);
        }
        return res;

    }

    private boolean initialised = false;

    @Override
    public int onLoop(){
        if(!initialised) {
            if (Client.isLoggedIn()) {
                startmagXP = Skills.getExperience(Skill.MAGIC);
                startmagLvl = Skills.getRealLevel(Skill.MAGIC);
                initialised = true;
            }
            return 500;
        }
        return getRoot().onLoop();
    }
}