
package punic;

public class RomanInfantryman extends AbstractBattleUnit {

    public RomanInfantryman(String name, String position) {
        super(name, "INFANTRY", "ROMAN", position);
    }


    public void performAction() {
        System.out.println(name + " формирует щитовую стену на " + position);
        sendMessage("Сформировал щитовую стену. Нужна поддержка лучников!");
    }

    public void formShieldWall() {
        System.out.println(name + " формирует тестудо!");
        if (mediator != null) {
            mediator.requestSupport("ARCHER", this);
        }
    }
}