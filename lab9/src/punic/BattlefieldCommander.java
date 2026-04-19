
package punic;

import java.util.*;

public class BattlefieldCommander implements BattleMediator {
    private Map<String, List<BattleUnit>> unitsByType = new HashMap<>();
    private List<BattleUnit> allUnits = new ArrayList<>();
    private int romanScore = 0;
    private int carthaginianScore = 0;

    public BattlefieldCommander() {

        unitsByType.put("ARCHER", new ArrayList<>());
        unitsByType.put("INFANTRY", new ArrayList<>());
        unitsByType.put("CAVALRY", new ArrayList<>());
        unitsByType.put("ELITE", new ArrayList<>());

        System.out.println("Командующий полем боя назначен!");
    }


    public void registerUnit(BattleUnit unit) {
        allUnits.add(unit);
        unitsByType.get(unit.getType().toUpperCase()).add(unit);
        System.out.println(unit.getName() + " (" + unit.getFaction() + ") прибыл на поле боя");
    }


    public void sendMessage(String message, BattleUnit sender) {
        System.out.println("\n=== СООБЩЕНИЕ ПО СИСТЕМЕ СВЯЗИ ===");
        System.out.println("От: " + sender.getName() + " (" + sender.getFaction() + ")");
        System.out.println("Сообщение: " + message);
        System.out.println("---");


        for (BattleUnit unit : allUnits) {
            if (unit != sender && unit.getFaction().equals(sender.getFaction())) {
                unit.receiveMessage(message);
            }
        }
    }


    public void coordinateAttack(String attackerType, String targetType) {
        System.out.println("\n=== КООРДИНАЦИЯ АТАКИ ===");
        System.out.println("Атакующие: " + attackerType + ", Цель: " + targetType);

        List<BattleUnit> attackers = unitsByType.get(attackerType.toUpperCase());
        List<BattleUnit> targets = new ArrayList<>();


        for (BattleUnit unit : allUnits) {
            if (unit.getType().equalsIgnoreCase(targetType)) {
                targets.add(unit);
            }
        }

        if (!attackers.isEmpty() && !targets.isEmpty()) {

            for (BattleUnit attacker : attackers) {
                if (!targets.isEmpty()) {
                    BattleUnit target = targets.get(0);
                    attacker.attackTarget(target);
                    targets.remove(0);
                }
            }
        } else {
            System.out.println("Нет доступных юнитов для координации атаки");
        }
    }


    public void requestSupport(String unitType, BattleUnit requester) {
        System.out.println("\n=== ЗАПРОС ПОДДЕРЖКИ ===");
        System.out.println("Запросил: " + requester.getName());
        System.out.println("Требуется: " + unitType + " поддержка");

        List<BattleUnit> availableUnits = unitsByType.get(unitType.toUpperCase());
        List<BattleUnit> allies = new ArrayList<>();


        for (BattleUnit unit : availableUnits) {
            if (unit.getFaction().equals(requester.getFaction()) && unit != requester) {
                allies.add(unit);
            }
        }

        if (!allies.isEmpty()) {
            System.out.println("Направлена поддержка от:");
            for (BattleUnit ally : allies) {
                System.out.println("  - " + ally.getName());
                ally.moveToSupport(requester.getPosition());
            }
        } else {
            System.out.println("Поддержка недоступна!");
        }
    }


    public void reportVictory(BattleUnit unit) {
        if (unit.getFaction().equals("ROMAN")) {
            romanScore += 10;
        } else {
            carthaginianScore += 10;
        }

        System.out.println("\n=== ДОКЛАД О ПОБЕДЕ ===");
        System.out.println(unit.getName() + " одержал победу!");
        System.out.println("Счет: Римляне " + romanScore + " - " + carthaginianScore + " Карфагеняне");


        unit.receiveAward("За победу в бою");
    }


    public void reportCasualty(BattleUnit unit) {
        System.out.println("\n=== ДОКЛАД О ПОТЕРЕ ===");
        System.out.println(unit.getName() + " (" + unit.getFaction() + ") выбыл из боя!");


        allUnits.remove(unit);
        unitsByType.get(unit.getType().toUpperCase()).remove(unit);


        for (BattleUnit ally : allUnits) {
            if (ally.getFaction().equals(unit.getFaction())) {
                ally.receiveCasualtyReport(unit.getName());
            }
        }
    }

    public void displayBattleStatus() {
        System.out.println("\n=== СТАТУС БИТВЫ ===");
        System.out.println("Всего юнитов на поле: " + allUnits.size());
        System.out.println("Римский счет: " + romanScore);
        System.out.println("Карфагенский счет: " + carthaginianScore);

        for (Map.Entry<String, List<BattleUnit>> entry : unitsByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " юнитов");
        }
    }
}