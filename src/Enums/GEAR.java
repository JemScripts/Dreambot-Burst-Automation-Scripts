package Enums;

public enum GEAR {
    STAFF_OF_DEAD("Staff of the dead"),
    OCCULT("Occult necklace"),
    CAPE("Imbued saradomin cape"),
    AHRIM_HOOD("Ahrim's hood"),
    AHRIM_ROBETOP("Ahrim's robetop"),
    AHRIM_BOTTOM("Ahrim's robeskirt"),
    BOOK("Mage's book"),
    BOOTS("Infinity boots"),
    GLOVES("Barrows gloves"),
    RING("Brimstone ring"),
    BLESSING("Honourable blessing");

    private String item;

    GEAR(String item) {
        this.item = item;
    }

    public String getItem(){
        return this.item = item;
    }

}
