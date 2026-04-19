
package punic;

public abstract class AbstractBattleUnit implements BattleUnit {
    protected String name;
    protected String type;
    protected String faction;
    protected String position;
    protected BattleMediator mediator;
    protected int health;

    public AbstractBattleUnit(String name, String type, String faction, String position) {
        this.name = name;
        this.type = type;
        this.faction = faction;
        this.position = position;
        this.health = 100;
    }


    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public String getFaction() {
        return faction;
    }


    public String getPosition() {
        return position;
    }


    public void setMediator(BattleMediator mediator) {
        this.mediator = mediator;
        mediator.registerUnit(this);
    }


    public void sendMessage(String message) {
        if (mediator != null) {
            mediator.sendMessage(message, this);
        }
    }


    public void receiveMessage(String message) {
        System.out.println(name + " получает сообщение: " + message);
    }


    public void attackTarget(BattleUnit target) {
        System.out.println(name + " атакует " + target.getName());

        if (Math.random() > 0.3) { // 70% шанс успеха
            System.out.println(name + " побеждает " + target.getName());
            if (mediator != null) {
                mediator.reportVictory(this);
                mediator.reportCasualty(target);
            }
        } else {
            System.out.println(name + " промахивается по " + target.getName());
        }
    }


    public void moveToSupport(String position) {
        this.position = position;
        System.out.println(name + " перемещается для поддержки на позицию " + position);
    }


    public void receiveAward(String award) {
        System.out.println(name + " получает награду: " + award);
    }


    public void receiveCasualtyReport(String unitName) {
        System.out.println(name + " узнает о потере: " + unitName);
    }


    public abstract void performAction();
}