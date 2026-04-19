package punic.aspects;

import punic.BattleMediator;
import punic.BattleUnit;

public aspect MediatorMonitoringAspect {

    // ====================== Основные действия медиатора ======================
    pointcut registerUnit(BattleMediator mediator, BattleUnit unit):
            execution(void punic.BattleMediator.registerUnit(BattleUnit))
                    && this(mediator) && args(unit);

    pointcut sendMessage(BattleMediator mediator, String message, BattleUnit sender):
            execution(void punic.BattleMediator.sendMessage(String, BattleUnit))
                    && this(mediator) && args(message, sender);

    pointcut coordinateAttack(BattleMediator mediator, String attackerType, String targetType):
            execution(void punic.BattleMediator.coordinateAttack(String, String))
                    && this(mediator) && args(attackerType, targetType);

    pointcut requestSupport(BattleMediator mediator, String unitType, BattleUnit requester):
            execution(void punic.BattleMediator.requestSupport(String, BattleUnit))
                    && this(mediator) && args(unitType, requester);

    pointcut reportVictory(BattleMediator mediator, BattleUnit unit):
            execution(void punic.BattleMediator.reportVictory(BattleUnit))
                    && this(mediator) && args(unit);

    pointcut reportCasualty(BattleMediator mediator, BattleUnit unit):
            execution(void punic.BattleMediator.reportCasualty(BattleUnit))
                    && this(mediator) && args(unit);

    // ====================== Advice ======================

    before(BattleMediator mediator, BattleUnit unit): registerUnit(mediator, unit) {
        System.out.println("[Mediator] Зарегистрирован юнит: "
                + unit.getClass().getSimpleName() + " (" + unit.getName() + ")");
    }

    before(BattleMediator mediator, String message, BattleUnit sender): sendMessage(mediator, message, sender) {
        System.out.println("[Mediator] " + sender.getClass().getSimpleName()
                + " отправляет сообщение: \"" + message + "\"");
    }

    before(BattleMediator mediator, String attackerType, String targetType): coordinateAttack(mediator, attackerType, targetType) {
        System.out.println("[Mediator] Координация атаки: " + attackerType + " → " + targetType);
    }

    before(BattleMediator mediator, String unitType, BattleUnit requester): requestSupport(mediator, unitType, requester) {
        System.out.println("[Mediator] Запрос поддержки от "
                + requester.getClass().getSimpleName() + " (тип: " + unitType + ")");
    }

    before(BattleMediator mediator, BattleUnit unit): reportVictory(mediator, unit) {
        System.out.println("[Mediator] Получен отчёт о ПОБЕДЕ от " + unit.getClass().getSimpleName());
    }

    before(BattleMediator mediator, BattleUnit unit): reportCasualty(mediator, unit) {
        System.out.println("[Mediator] Получен отчёт о ПОТЕРЕ от " + unit.getClass().getSimpleName());
    }

    after(BattleMediator mediator):
            coordinateAttack(mediator, .., ..) ||
                    requestSupport(mediator, .., ..) ||
                    reportVictory(mediator, ..) ||
                    reportCasualty(mediator, ..) {
        System.out.println("[Mediator] Действие медиатора завершено\n");
    }
}