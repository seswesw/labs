package punic.aspects;

public aspect ProxyLoggingAspect {

    pointcut proxyOperation(Object proxy):
            execution(* punic.*Proxy+. *(..)) && this(proxy);

    pointcut realSubjectAccess(Object proxy):
            call(* punic.*Subject+. *(..)) && this(proxy);   // вызов реального объекта из прокси

    before(Object proxy): proxyOperation(proxy) {
        System.out.println("[Proxy] Вызов метода на прокси: "
                + proxy.getClass().getSimpleName());
    }

    before(Object proxy): realSubjectAccess(proxy) {
        System.out.println("[Proxy] Прокси делегирует вызов реальному субъекту");
    }

    after(Object proxy) returning(Object result): proxyOperation(proxy) {
        System.out.println("[Proxy] Метод завершён, результат: "
                + (result == null ? "null" : result.getClass().getSimpleName()));
    }
}