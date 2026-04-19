
package punic;

public class EliteHorseman extends Horseman {
    private int chargePower = 3;

    public EliteHorseman(String name) {
        super(name);
    }


    public void info() {
        super.info();
        System.out.println("Сила таранной атаки: " + chargePower);
    }


    public void attack() {
        if (chargePower > 0) {
            System.out.println(getName() + " проводит сокрушительную таранную атаку!");
            chargePower--;
        } else {
            super.attack();
        }
    }


    public int getHealth() {
        return 120;
    }
}