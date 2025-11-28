public class PNJStats {
    private String name;
    private String gender;
    private double height;
    private int age;
    private int strength;
    private int stamina;
    private int healthIndicator;
    private int hungerIndicator;
    private int aggressivenessIndicator;
    private int magicPotionLevel;

    public PNJStats() {
    }

    public PNJStats(String name, String gender, double height, int age, int strength, int stamina,
                    int healthIndicator, int hungerIndicator, int aggressivenessIndicator, int magicPotionLevel) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.stamina = stamina;
        this.healthIndicator = healthIndicator;
        this.hungerIndicator = hungerIndicator;
        this.aggressivenessIndicator = aggressivenessIndicator;
        this.magicPotionLevel = magicPotionLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getHealthIndicator() {
        return healthIndicator;
    }

    public void setHealthIndicator(int healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    public int getHungerIndicator() {
        return hungerIndicator;
    }

    public void setHungerIndicator(int hungerIndicator) {
        this.hungerIndicator = hungerIndicator;
    }

    public int getAggressivenessIndicator() {
        return aggressivenessIndicator;
    }

    public void setAggressivenessIndicator(int aggressivenessIndicator) {
        this.aggressivenessIndicator = aggressivenessIndicator;
    }

    public int getMagicPotionLevel() {
        return magicPotionLevel;
    }

    public void setMagicPotionLevel(int magicPotionLevel) {
        this.magicPotionLevel = magicPotionLevel;
    }

    @Override
    public String toString() {
        return "Stats {" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", age=" + age +
                ", strength=" + strength +
                ", stamina=" + stamina +
                ", healthIndicator=" + healthIndicator +
                ", hungerIndicator=" + hungerIndicator +
                ", aggressivenessIndicator=" + aggressivenessIndicator +
                ", magicPotionLevel=" + magicPotionLevel +
                '}';
    }
}
