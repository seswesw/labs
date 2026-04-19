
package punic;

public class MediatorWarriorFactory {
    private static MediatorWarriorFactory instance;
    private BattleMediator currentMediator;

    private MediatorWarriorFactory() {
        System.out.println("Фабрика воинов с Mediator создана");
    }

    public static synchronized MediatorWarriorFactory getInstance() {
        if (instance == null) {
            instance = new MediatorWarriorFactory();
        }
        return instance;
    }

    public void setCurrentMediator(BattleMediator mediator) {
        this.currentMediator = mediator;
    }

    public BattleUnit createUnit(String type, String name, String faction, String position) {
        BattleUnit unit;

        switch (type.toUpperCase()) {
            case "ARCHER":
                if (faction.equals("ROMAN")) {
                    unit = new RomanArcher(name, position);
                } else {
                    unit = new CarthaginianArcher(name, position);
                }
                break;

            case "INFANTRY":
                if (faction.equals("ROMAN")) {
                    unit = new RomanInfantryman(name, position);
                } else {
                    unit = new CarthaginianInfantryman(name, position);
                }
                break;

            case "CAVALRY":
                if (faction.equals("ROMAN")) {
                    unit = new RomanHorseman(name, position);
                } else {
                    unit = new CarthaginianHorseman(name, position);
                }
                break;

            case "ELEPHANT":
                unit = new CarthaginianWarElephant(name, position);
                break;

            default:
                throw new IllegalArgumentException("Неизвестный тип юнита: " + type);
        }


        if (currentMediator != null) {
            unit.setMediator(currentMediator);
        }

        return unit;
    }
}