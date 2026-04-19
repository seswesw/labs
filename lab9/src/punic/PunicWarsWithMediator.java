
package punic;

public class PunicWarsWithMediator {
    public static void main(String[] args) {
        System.out.println("ПУНИЧЕСКИЕ ВОЙНЫ С ПАТТЕРНОМ MEDIATOR\n");


        BattleMediator commander = new BattlefieldCommander();


        MediatorWarriorFactory factory = MediatorWarriorFactory.getInstance();
        factory.setCurrentMediator(commander);

        System.out.println("\nРАЗВЕРТЫВАНИЕ РИМСКОЙ АРМИИ");


        BattleUnit[] romanArmy = {
                factory.createUnit("INFANTRY", "Легион I", "ROMAN", "Центр"),
                factory.createUnit("INFANTRY", "Легион II", "ROMAN", "Центр"),
                factory.createUnit("ARCHER", "Велиты", "ROMAN", "Тыл"),
                factory.createUnit("CAVALRY", "Эквестры", "ROMAN", "Правый фланг"),
                factory.createUnit("CAVALRY", "Ауксилии", "ROMAN", "Левый фланг")
        };

        System.out.println("\nРАЗВЕРТЫВАНИЕ КАРФАГЕНСКОЙ АРМИИ");


        BattleUnit[] carthaginianArmy = {
                factory.createUnit("INFANTRY", "Наемники", "CARTHAGINIAN", "Центр"),
                factory.createUnit("ARCHER", "Балеарцы", "CARTHAGINIAN", "Тыл"),
                factory.createUnit("CAVALRY", "Нумидийцы", "CARTHAGINIAN", "Фланги"),
                factory.createUnit("ELEPHANT", "Сур", "CARTHAGINIAN", "Передовая"),
                factory.createUnit("ELEPHANT", "Абдул", "CARTHAGINIAN", "Передовая")
        };

        System.out.println("\nПОДГОТОВКА К БИТВЕ");


        for (BattleUnit unit : romanArmy) {
            unit.performAction();
        }

        for (BattleUnit unit : carthaginianArmy) {
            unit.performAction();
        }


        ((BattlefieldCommander) commander).displayBattleStatus();

        System.out.println("\nНАЧАЛО БИТВЫ");


        System.out.println("\n--- ФАЗА 1: КООРДИНАЦИЯ АТАКИ ---");
        romanArmy[0].sendMessage("Атакую центр! Нужна поддержка лучников!");
        ((RomanInfantryman) romanArmy[0]).formShieldWall();

        System.out.println("\n--- ФАЗА 2: ОТВЕТ КАРФАГЕНА ---");
        carthaginianArmy[3].sendMessage("Атакую слоном прорыв в центре!");
        ((CarthaginianWarElephant) carthaginianArmy[3]).charge();

        System.out.println("\n--- ФАЗА 3: ФЛАНГОВАЯ АТАКА ---");
        romanArmy[3].sendMessage("Начинаю фланговую атаку!");
        ((RomanHorseman) romanArmy[3]).flankAttack();

        System.out.println("\n--- ФАЗА 4: АРТИЛЛЕРИЙСКАЯ ПОДДЕРЖКА ---");
        ((RomanArcher) romanArmy[2]).rainOfArrows();

        System.out.println("\nИТОГИ БИТВЫ");
        ((BattlefieldCommander) commander).displayBattleStatus();

        System.out.println("\nПРЕИМУЩЕСТВА MEDIATOR");
        System.out.println("1. Юниты не знают друг о друге - взаимодействие через посредника");
        System.out.println("2. Легко добавлять новые типы юнитов");
        System.out.println("3. Централизованное управление логикой взаимодействия");
        System.out.println("4. Уменьшение связанности между объектами");
    }
}