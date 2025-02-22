package Enums;

public enum RUNES {
    CHAOS("Chaos rune", 500),
    WATER("Water rune", 500),
    DEATH("Death rune", 500);

private String name;
private int quantity;

    RUNES(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getItem(){
        return this.name = name;
    }

    public int getQuantity(){
        return this.quantity = quantity;
    }


}
