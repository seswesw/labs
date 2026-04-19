
package punic;

public class WarriorFactory {
    private static WarriorFactory instance;

    private WarriorFactory() {
        System.out.println("WarriorFactory создан (с поддержкой Proxy)");
    }

    public static synchronized WarriorFactory getInstance() {
        if (instance == null) {
            instance = new WarriorFactory();
        }
        return instance;
    }


    public Warrior createWarrior(String type) {
        return createWarrior(type, "Обычный воин", 1);
    }


    public Warrior createWarrior(String type, String name, int playerLevel) {
        switch (type.toLowerCase()) {
            case "archer":
                return new Archer(name);
            case "infantryman":
                return new Infantryman(name);
            case "horseman":
                return new Horseman(name);
            case "elite_archer":
            case "elite_infantryman":
            case "elite_horseman":

                return new EliteWarriorProxy(type, name, playerLevel);
            default:
                throw new IllegalArgumentException("Неизвестный тип воина: " + type);
        }
    }


    public enum WarriorType {
        ARCHER, INFANTRYMAN, HORSEMAN,
        ELITE_ARCHER, ELITE_INFANTRYMAN, ELITE_HORSEMAN
    }


    public Warrior createWarrior(WarriorType type) {
        return createWarrior(type, "Воин", 1);
    }

    public Warrior createWarrior(WarriorType type, String name, int playerLevel) {
        switch (type) {
            case ARCHER:
                return createWarrior("archer", name, playerLevel);
            case INFANTRYMAN:
                return createWarrior("infantryman", name, playerLevel);
            case HORSEMAN:
                return createWarrior("horseman", name, playerLevel);
            case ELITE_ARCHER:
                return createWarrior("elite_archer", name, playerLevel);
            case ELITE_INFANTRYMAN:
                return createWarrior("elite_infantryman", name, playerLevel);
            case ELITE_HORSEMAN:
                return createWarrior("elite_horseman", name, playerLevel);
            default:
                throw new IllegalArgumentException("Неизвестный тип воина");
        }
    }
}