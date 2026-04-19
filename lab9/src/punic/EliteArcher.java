
package punic;

public class EliteArcher extends Archer {
    private int specialArrows = 10;

    public EliteArcher(String name) {
        super(name);
    }


    public void info() {
        super.info();
        System.out.println("Особые стрелы: " + specialArrows + " шт.");
    }


    public void attack() {
        if (specialArrows > 0) {
            System.out.println(getName() + " выпускает зажигательную стрелу!");
            specialArrows--;
        } else {
            super.attack();
        }
    }


    public int getHealth() {
        return 80;
    }
}