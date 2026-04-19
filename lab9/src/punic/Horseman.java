package punic;

public class Horseman implements Warrior {
    private String name;
    private int health = 80;

    public Horseman() {
        this.name = "Всадник";
    }

    public Horseman(String name) {
        this.name = name;
    }


    public void info() {
        System.out.println(name + " - высокая мобильность, кавалерийская атака");
    }


    public void attack() {
        System.out.println(name + " атакует с копьем!");
    }


    public void defend() {
        System.out.println(name + " отступает на безопасное расстояние");
    }


    public int getHealth() {
        return health;
    }


    public String getName() {
        return name;
    }
}