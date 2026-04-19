package punic;


public interface BattleMediator {
    void sendMessage(String message, BattleUnit sender);
    void registerUnit(BattleUnit unit);
    void coordinateAttack(String attackerType, String targetType);
    void requestSupport(String unitType, BattleUnit requester);
    void reportVictory(BattleUnit unit);
    void reportCasualty(BattleUnit unit);
}
