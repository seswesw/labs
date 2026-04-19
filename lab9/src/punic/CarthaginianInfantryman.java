
package punic;

public class CarthaginianInfantryman extends AbstractBattleUnit {

    public CarthaginianInfantryman(String name, String position) {
        super(name, "INFANTRY", "CARTHAGINIAN", position);
    }


    public void performAction() {
        System.out.println(name + " - наемник готов к бою");
        sendMessage("Наемная пехота готова. Жду приказов!");
    }
}