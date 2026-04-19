
package punic;

public class RomanHorseman extends AbstractBattleUnit {

    public RomanHorseman(String name, String position) {
        super(name, "CAVALRY", "ROMAN", position);
    }


    public void performAction() {
        System.out.println(name + " патрулирует фланг на " + position);
        sendMessage("Патрулирую фланг. Могу атаковать по команде!");
    }

    public void flankAttack() {
        System.out.println(name + " начинает фланговую атаку!");
        if (mediator != null) {
            mediator.coordinateAttack("CAVALRY", "ARCHER");
        }
    }
}