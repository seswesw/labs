package punic;

public class Infantryman implements Warrior {
    private String name;
    private int health = 100;

    public Infantryman() {
        this.name = "Пехотинец";
    }

    public Infantryman(String name) {
        this.name = name;
    }


    public void info() {
        System.out.println(name + " - ближний бой, тяжелая броня");
    }


    public void attack() {
        System.out.println(name + " атакует мечом!");
    }


    public void defend() {
        System.out.println(name + " поднимает щит!");
    }


    public int getHealth() {
        return health;
    }


    public String getName() {
        return name;
    }
}