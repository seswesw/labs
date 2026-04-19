
package punic;

public class EliteInfantryman extends Infantryman {
    private boolean shieldWall = true;

    public EliteInfantryman(String name) {
        super(name);
    }


    public void info() {
        super.info();
        System.out.println("Щитовой строй: " + (shieldWall ? "готов" : "нарушен"));
    }


    public void defend() {
        if (shieldWall) {
            System.out.println(getName() + " формирует щитовую стену!");
            shieldWall = false;
        } else {
            super.defend();
        }
    }


    public int getHealth() {
        return 150;
    }
}