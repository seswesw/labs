package punic;

public class GameManager {
    private static GameManager instance;
    private int totalWarriorsCreated = 0;
    private int playerLevel = 1;

    private GameManager() {
        System.out.println("GameManager создан");
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void warriorCreated() {
        totalWarriorsCreated++;
        System.out.println("Создан воин. Всего воинов: " + totalWarriorsCreated);


        if (totalWarriorsCreated % 3 == 0) {
            playerLevel++;
            System.out.println("Игрок достиг уровня " + playerLevel + "!");
        }
    }

    public int getTotalWarriors() {
        return totalWarriorsCreated;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int level) {
        this.playerLevel = level;
        System.out.println("Уровень игрока установлен: " + level);
    }
}