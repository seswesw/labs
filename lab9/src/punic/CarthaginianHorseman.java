
package punic;

public class CarthaginianHorseman extends AbstractBattleUnit {

    public CarthaginianHorseman(String name, String position) {
        super(name, "CAVALRY", "CARTHAGINIAN", position);
    }


    public void performAction() {
        System.out.println(name + " - нумидийский всадник готов");
        sendMessage("Нумидийская конница готова к рейдам!");
    }
}