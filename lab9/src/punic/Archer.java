package punic;

public class Archer implements Warrior {
    private String name;
    private int health = 60;

    public Archer() {
        this.name = "Лучник";
    }

    public Archer(String name) {
        this.name = name;
    }


    public void info() {
        System.out.println(name + " - стреляет на расстоянии, легкая броня");
    }


    public void attack() {
        System.out.println(name + " выпускает стрелу!");
    }

    public void defend() {
        System.out.println(name + " укрывается за щитом");
    }


    public int getHealth() {
        return health;
    }


    public String getName() {
        return name;
    }
}