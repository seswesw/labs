
package punic;

public class SimpleMediatorDemo {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ MEDIATOR\n");


        BattleMediator mediator = new BattlefieldCommander();


        BattleUnit archer = new RomanArcher("Лучник Марк", "Холм");
        BattleUnit infantry = new RomanInfantryman("Легионер Гай", "Центр");
        BattleUnit cavalry = new RomanHorseman("Всадник Луций", "Фланг");


        archer.setMediator(mediator);
        infantry.setMediator(mediator);
        cavalry.setMediator(mediator);


        System.out.println("\n1. Лучник отправляет сообщение:");
        archer.sendMessage("Вижу противника!");

        System.out.println("\n2. Пехотинец запрашивает поддержку:");
        infantry.sendMessage("Нужна поддержка лучников!");

        System.out.println("\n3. Медиатор координирует атаку:");
        mediator.coordinateAttack("ARCHER", "INFANTRY");

        System.out.println("\n4. Кавалерия запрашивает помощь:");
        cavalry.sendMessage("Атакую фланг, нужна поддержка!");
        mediator.requestSupport("ARCHER", cavalry);

        System.out.println("\nВЫВОД");
        System.out.println("Без Mediator: юниты хранили бы ссылки друг на друга");
        System.out.println("С Mediator: все взаимодействия через один объект");
        System.out.println("Снижение связанности: с O(n²) до O(n)");
    }
}