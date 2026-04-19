
package punic;

public class CarthaginianWarElephant extends AbstractBattleUnit {

    public CarthaginianWarElephant(String name, String position) {
        super(name, "ELITE", "CARTHAGINIAN", position);
        this.health = 200; // Слоны имеют больше здоровья
    }


    public void performAction() {
        System.out.println("Боевой слон " + name + " готов к атаке на " + position);
        sendMessage("Боевой слон готов. Могу прорвать оборону!");
    }

    public void charge() {
        System.out.println("Боевой слон " + name + " начинает сокрушительную атаку!");
        if (mediator != null) {
            mediator.coordinateAttack("ELITE", "INFANTRY");
        }
    }
}