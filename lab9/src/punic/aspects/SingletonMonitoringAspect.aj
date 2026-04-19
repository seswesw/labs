package punic.aspects;

public aspect SingletonMonitoringAspect {

    pointcut singletonInstanceAccess():
            call(* punic.*.getInstance(..));

    pointcut directSingletonCreation():
            call(punic.*.new(..)) &&
                    !within(punic.aspects.*) &&
                    withincode(* punic.*.getInstance(..));

    before(): singletonInstanceAccess() {
        System.out.println("[Singleton] Запрошено получение экземпляра Singleton");
    }

    before(): directSingletonCreation() {
        System.out.println("[Singleton] Создание экземпляра через конструктор (внутри getInstance())");
    }

    // Опционально: предупреждение при попытке создать Singleton напрямую извне
    before(): call(punic.*.new(..)) && !withincode(* punic.*.getInstance(..)) && !within(punic.aspects.*) {
        System.out.println("[Singleton] ВНИМАНИЕ: Прямая попытка создания Singleton через new!");
    }
}
