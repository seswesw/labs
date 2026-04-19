
package punic;

public class Main {
    public static void main(String[] args) {
        System.out.println("ИГРА 'ПУНИЧЕСКИЕ ВОЙНЫ'    \n");


        WarriorFactory factory = WarriorFactory.getInstance();
        GameManager gameManager = GameManager.getInstance();


        System.out.println("\nОБЫЧНЫЕ ВОИНЫ");

        Warrior[] basicArmy = {
                factory.createWarrior("archer", "Римский лучник", 1),
                factory.createWarrior("infantryman", "Легионер", 1),
                factory.createWarrior("horseman", "Римский всадник", 1)
        };

        System.out.println("\nДемонстрация обычных воинов:");
        for (Warrior warrior : basicArmy) {
            warrior.info();
            warrior.attack();
            gameManager.warriorCreated();
        }


        System.out.println("\n\nЭЛИТНЫЕ ВОИНЫ (PROXY)");
        System.out.println("Текущий уровень игрока: " + gameManager.getPlayerLevel());

        Warrior[] eliteArmy = {
                factory.createWarrior("elite_archer", "Критский лучник", gameManager.getPlayerLevel()),
                factory.createWarrior("elite_infantryman", "Преторианец", gameManager.getPlayerLevel()),
                factory.createWarrior("elite_horseman", "Катафракт", gameManager.getPlayerLevel())
        };

        System.out.println("\nПопытка использовать элитных воинов:");
        for (Warrior warrior : eliteArmy) {
            warrior.info();
            warrior.attack();
            gameManager.warriorCreated();
        }


        System.out.println("\n\nПОВЫШЕНИЕ УРОВНЯ");
        gameManager.setPlayerLevel(10);

        System.out.println("\nПовторная попытка (уровень 10):");
        for (int i = 0; i < eliteArmy.length; i++) {
            if (eliteArmy[i] instanceof EliteWarriorProxy) {
                EliteWarriorProxy proxy = (EliteWarriorProxy) eliteArmy[i];
                proxy.setPlayerLevel(gameManager.getPlayerLevel());

                System.out.println("\n" + proxy.getName() + ":");
                proxy.info();
                proxy.attack();
                proxy.defend();
                System.out.println("Здоровье: " + proxy.getHealth());
            }
        }


        System.out.println("\n\nПРОВЕРКА SINGLETON");
        WarriorFactory factory2 = WarriorFactory.getInstance();
        GameManager gameManager2 = GameManager.getInstance();

        System.out.println("Один и тот же экземпляр фабрики? " + (factory == factory2));
        System.out.println("Один и тот же экземпляр GameManager? " + (gameManager == gameManager2));

        System.out.println("\nИтоговая статистика:");
        System.out.println("Всего создано воинов: " + gameManager.getTotalWarriors());
        System.out.println("Текущий уровень игрока: " + gameManager.getPlayerLevel());

        System.out.println("\n=== ИГРА ЗАВЕРШЕНА ===");
    }
}