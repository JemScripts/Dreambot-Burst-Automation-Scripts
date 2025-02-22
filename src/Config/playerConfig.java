package Config;

import Enums.RUNES;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import Enums.GEAR;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.map.Region;

import java.util.Arrays;

public class playerConfig {

    public static boolean isEquipped(){
        return Arrays.stream(GEAR.values()).allMatch(setup -> Equipment.all().stream().anyMatch(item -> item != null && item.getName().startsWith(setup.getItem())));
    }

    //10795 ape atoll region id?

    public static boolean atHole(){
        return Players.getLocal().getTile().equals(new Tile(2572, 9168, 1));
    }

    public static boolean inDungeon(){
            int KRUK_DUNG_REGION = 10126;

            return Region.getRegion().getId() == KRUK_DUNG_REGION;
    }

    public static boolean emptyHole = false;

    public static boolean inHole(){
        int MONKEY_HOLE_REGION = 9358;

        return Region.getRegion().getId() == MONKEY_HOLE_REGION;
    }

    public static boolean inMonkeyRoom(){
        int[] MONKEY_ROOM_REGION = {9614, 9615, 9870, 9871};

        return Players.getLocal().getZ() > 0 && Arrays.equals(Region.getMapRegions(), MONKEY_ROOM_REGION);
    }

    public static boolean walkToSpot = false;

    public static boolean combinePots(){
        return Inventory.all(item -> item != null && item.getName().equals("Prayer potion(1)")).size() >= 2;
    }

    public static boolean groundPots(){
        return GroundItems.all().stream().anyMatch(groundItem -> groundItem != null && groundItem.getName() != null && groundItem.getName().startsWith("Prayer potion(1)")) && !Inventory.isFull();
    }

    public static boolean hasMagicPots(){
        return Inventory.all().stream().anyMatch(item -> item != null && item.getName() != null && item.getName().startsWith("Magic potion"));
    }

    public static boolean drinkMagicPot = false;

    public static boolean hasRunes(){
        return Arrays.stream(RUNES.values()).allMatch(rune -> Inventory.all().stream().filter(item -> item != null && item.getName().startsWith(rune.getItem())).mapToInt(Item::getAmount).sum() >= rune.getQuantity());
    }

    public static boolean hasPots(){
        return Inventory.all().stream().anyMatch(item -> item != null && item.getName() != null && item.getName().startsWith("Prayer potion"));
    }
}
