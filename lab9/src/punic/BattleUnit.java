
package punic;

public interface BattleUnit {
    String getName();
    String getType();
    String getFaction();
    String getPosition();

    void setMediator(BattleMediator mediator);
    void sendMessage(String message);
    void receiveMessage(String message);
    void attackTarget(BattleUnit target);
    void moveToSupport(String position);
    void receiveAward(String award);
    void receiveCasualtyReport(String unitName);

    void performAction();
}