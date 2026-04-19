
// Все в одном файле специально (Spaghetti + God Object)

import java.util.*;

//  God Object + Singleton Abuse + Global State
class AppManager {
    public static AppManager instance = new AppManager(); //  не приватный

    public List<User> users = new ArrayList<>(); //  глобальное состояние
    public Map<String, Object> cache = new HashMap<>();

    public void init() {
        System.out.println("Init app");
    }

    //  Long Method + Spaghetti Code
    public void process(String type, String data) {
        if (type.equals("CREATE")) {
            if (data != null) {
                String[] parts = data.split(",");
                User u = new User();
                u.name = parts[0];
                u.age = Integer.parseInt(parts[1]);
                users.add(u);
                System.out.println("User created");
            }
        } else if (type.equals("DELETE")) {
            for (User u : users) {
                if (u.name.equals(data)) {
                    users.remove(u);
                    break;
                }
            }
        } else if (type.equals("PRINT")) {
            for (User u : users) {
                System.out.println(u.name + " " + u.age);
            }
        }
    }
}

//  Anemic Domain Model
class User {
    public String name; //  public поля
    public int age;
}

//  Tight Coupling + Hardcode
class UserService {
    private AppManager manager = AppManager.instance; //  жесткая зависимость

    public void register(String name, int age) {
        if (age > 18) { //  magic number
            User u = new User();
            u.name = name;
            u.age = age;
            manager.users.add(u);
        }
    }
}

//  Factory (плохая реализация)
class BadNotificationFactory {
    public Object create(String type) {
        if (type.equals("EMAIL")) return new EmailSender();
        if (type.equals("SMS")) return new SmsSender();
        return null; //  null
    }
}

class EmailSender {
    public void send(String msg) {
        System.out.println("email: " + msg);
    }
}

class SmsSender {
    public void send(String msg) {
        System.out.println("sms: " + msg);
    }
}

//  Exception Swallowing
class PaymentService {
    public void pay() {
        try {
            int x = 10 / 0;
        } catch (Exception e) {
            //  ничего не делаем
        }
    }
}

//  Copy-Paste Programming
class ReportService {
    public void printUsers(List<User> users) {
        for (User u : users) {
            System.out.println(u.name + " " + u.age);
        }
    }

    public void printAdmins(List<User> users) {
        for (User u : users) {
            System.out.println(u.name + " " + u.age);
        }
    }
}

//  Feature Envy
class UserPrinter {
    public void print(User u) {
        System.out.println(u.name + " " + u.age);
    }
}

//  Dead Code
class LegacySystem {
    public void oldMethod() {
        System.out.println("unused");
    }
}

//  Main
public class Main {
    public static void main(String[] args) {
        AppManager.instance.init();

        AppManager.instance.process("CREATE", "John,25");
        AppManager.instance.process("CREATE", "Anna,17");
        AppManager.instance.process("PRINT", "");

        UserService us = new UserService();
        us.register("Mike", 30);

        PaymentService ps = new PaymentService();
        ps.pay();

        BadNotificationFactory factory = new BadNotificationFactory();
        EmailSender email = (EmailSender) factory.create("EMAIL");
        email.send("Hello");

        ReportService rs = new ReportService();
        rs.printUsers(AppManager.instance.users);
    }
}


//  Mutable Shared State + Race Condition (потенциальная проблема)
class Counter {
    public int value = 0;

    public void increment() {
        value++; //  не потокобезопасно
    }
}

// Overengineering (слишком сложно для простой задачи)
interface NameProvider { String getName(); }
class SimpleNameProvider implements NameProvider {
    public String getName() { return "default"; }
}
class NameService {
    private NameProvider provider;
    public NameService(NameProvider provider) {
        this.provider = provider;
    }
    public String fetch() { return provider.getName(); }
}

//  Switch Hell
class DiscountCalculator {
    public double calculate(String type, double price) {
        switch (type) {
            case "A": return price * 0.9;
            case "B": return price * 0.8;
            case "C": return price * 0.7;
            default: return price;
        }
    }
}

//  Data Clumps
class OrderService {
    public void createOrder(String name, String address, String phone) {
        System.out.println(name + address + phone);
    }
}

//  Lazy Class (бесполезный класс)
class Helper {
    public void help() {
        System.out.println("help");
    }
}

// Inappropriate Intimacy (слишком тесная связь)
class A {
    B b = new B();
    void doSomething() {
        b.secret = 42; //  прямой доступ
    }
}
class B {
    public int secret;
}

//  Message Chains
class Address {
    String city;
}
class Profile {
    Address address;
}
class Client {
    Profile profile;
}
class ClientService {
    public String getCity(Client c) {
        return c.profile.address.city; // цепочка вызовов
    }
}

//  Temporary Field
class TempFieldExample {
    private String temp;

    public void process(boolean flag) {
        if (flag) {
            temp = "used";
            System.out.println(temp);
        }
    }
}

//  Refused Bequest
class Bird {
    void fly() {}
}
class Penguin extends Bird {
    void fly() { throw new UnsupportedOperationException(); } // не умеет летать
}
