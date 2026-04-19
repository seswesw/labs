
package punic;

public class RomanArcher extends AbstractBattleUnit {

    public RomanArcher(String name, String position) {
        super(name, "ARCHER", "ROMAN", position);
    }


    public void performAction() {
        System.out.println(name + " занимает позицию для стрельбы на " + position);
        sendMessage("Занял позицию для стрельбы. Готов поддерживать пехоту!");
    }

    public void rainOfArrows() {
        System.out.println(name + " выпускает град стрел!");
        if (mediator != null) {
            mediator.coordinateAttack("ARCHER", "INFANTRY");
        }
    }
}