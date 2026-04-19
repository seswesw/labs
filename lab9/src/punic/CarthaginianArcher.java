
package punic;

public class CarthaginianArcher extends AbstractBattleUnit {

    public CarthaginianArcher(String name, String position) {
        super(name, "ARCHER", "CARTHAGINIAN", position);
    }


    public void performAction() {
        System.out.println(name + " из Балеарских островов готов к стрельбе");
        sendMessage("Балеарский пращник готов. Могу атаковать на средней дистанции!");
    }
}