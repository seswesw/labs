
package punic;

public class EliteWarriorProxy implements Warrior {
    private Warrior realWarrior;
    private String warriorName;
    private String warriorType;
    private int playerLevel;
    private boolean isUnlocked;

    public EliteWarriorProxy(String type, String name, int playerLevel) {
        this.warriorType = type;
        this.warriorName = name;
        this.playerLevel = playerLevel;
        this.isUnlocked = checkUnlockStatus();

        System.out.println("Создан Proxy для элитного воина: " + name);
        System.out.println("Требуется уровень: " + getRequiredLevel() + ", ваш уровень: " + playerLevel);

        if (isUnlocked) {
            initializeRealWarrior();
        }
    }

    private boolean checkUnlockStatus() {

        return playerLevel >= getRequiredLevel();
    }

    private int getRequiredLevel() {
        switch(warriorType.toLowerCase()) {
            case "elite_archer": return 5;
            case "elite_infantryman": return 10;
            case "elite_horseman": return 8;
            default: return 1;
        }
    }

    private void initializeRealWarrior() {

        System.out.println("Загрузка элитного воина " + warriorName + "...");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch(warriorType.toLowerCase()) {
            case "elite_archer":
                realWarrior = new EliteArcher(warriorName);
                break;
            case "elite_infantryman":
                realWarrior = new EliteInfantryman(warriorName);
                break;
            case "elite_horseman":
                realWarrior = new EliteHorseman(warriorName);
                break;
        }

        System.out.println("Элитный воин " + warriorName + " готов к бою!");
    }

    private void checkAccess() {
        if (!isUnlocked) {
            System.out.println("Доступ запрещен! Необходим уровень " +
                    getRequiredLevel() + " (ваш уровень: " + playerLevel + ")");
        }
    }


    public void info() {
        if (!isUnlocked) {
            checkAccess();
            System.out.println("??? - элитный воин (заблокирован)");
            return;
        }

        if (realWarrior == null) {
            initializeRealWarrior();
        }

        System.out.print("[Элитный] ");
        realWarrior.info();
    }


    public void attack() {
        if (!isUnlocked) {
            checkAccess();
            return;
        }

        if (realWarrior == null) {
            initializeRealWarrior();
        }

        System.out.print("[Элитный] ");
        realWarrior.attack();
    }


    public void defend() {
        if (!isUnlocked) {
            checkAccess();
            return;
        }

        if (realWarrior == null) {
            initializeRealWarrior();
        }

        System.out.print("[Элитный] ");
        realWarrior.defend();
    }


    public int getHealth() {
        if (!isUnlocked) {
            checkAccess();
            return 0;
        }

        if (realWarrior == null) {
            initializeRealWarrior();
        }

        return realWarrior.getHealth();
    }


    public String getName() {
        return warriorName + (isUnlocked ? "" : " (заблокирован)");
    }


    public void setPlayerLevel(int newLevel) {
        this.playerLevel = newLevel;
        this.isUnlocked = checkUnlockStatus();

        if (isUnlocked && realWarrior == null) {
            System.out.println("Уровень повышен! Элитный воин " + warriorName + " разблокирован!");
        }
    }
}