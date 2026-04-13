package horsestat;

import java.text.DecimalFormat;
import java.util.Set;

public class Horse extends Animal {

    protected double speed;
    protected double jump;
    protected double health;

    protected String color;
    protected String pattern;

    protected double speedPro;
    protected double jumpPro;
    protected double healthPro;

    public Horse(String name, double speed, double jump, double health) {
        this.setName(name);
        this.setSpeed(speed);
        this.setJump(jump);
        this.setHealth(health);
        this.findAverage();
        this.setColor(null);
        this.setPattern(null);
    }

    public Horse(String name, double speed, double jump, double health, String color, String pattern) {
        this.setSpeed(speed);
        this.setJump(jump);
        this.setHealth(health);
        this.findAverage();
        this.setName(name);
        this.setColor(color);
        this.setPattern(pattern);
    }

    public static final Set<String> Colors = Set.of("none", "white", "creamy", "chestnut", "brown", "black", "gray",
            "darkbrown");
    public static final Set<String> Patterns = Set.of("none", "white", "whitefield", "whitedots", "blackdots");

    // Getters og setters for valgfrie datapunkter til heste

    public void setColor(String color) {
        if (validStringCheck(color)) {
            this.color = "none";
            return;
        }

        if (!Horse.Colors.contains(color)) {
            throw new IllegalArgumentException("must be valid color, not: '" + color + "' " + color.getClass());
        }
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setPattern(String pattern) {
        if (validStringCheck(pattern)) {
            this.pattern = "none";
            return;

        }
        if (!Horse.Patterns.contains(pattern)) {
            throw new IllegalArgumentException("must be valid pattern, not: '" + pattern + "' " + pattern.getClass());
        }
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void findPercent() {
        this.speedPro = (this.speed - Horse.minSpeed) / (Horse.maxSpeed - Horse.minSpeed);
        this.jumpPro = (this.jump - Horse.minJump) / (Horse.maxJump - Horse.minJump);
        this.healthPro = (this.health - Horse.minHealth) / (Horse.maxHealth - Horse.minHealth);
    }

    @Override
    public void findAverage() {
        this.findPercent();
        this.average = (this.speedPro + this.jumpPro + this.healthPro) / 3;
    }

    // Getters for prosent av hestestatistikk

    public double getSpeedPro() {
        return this.speedPro;
    }

    public double getJumpPro() {
        return this.jumpPro;
    }

    public double getHealthPro() {
        return this.healthPro;
    }

    // Standard getters og setters for hester

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        validCheck(speed, minSpeed, maxSpeed, "speed");
        this.speed = speed;
    }

    public double getJump() {
        return this.jump;
    }

    public void setJump(double jump) {
        validCheck(jump, minJump, maxJump, "jump");
        this.jump = jump;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        validCheck(health, minHealth, maxHealth, "health");
        this.health = health;
    }

    // Utregning av faktiske (ingame) verdier for hester

    public double getRealJump() {
        return formatDouble(0.534090909091226 * Math.pow(this.jump, 4)
                - 2.376010101010921 * Math.pow(this.jump, 3)
                + 7.953750000000762 * Math.pow(this.jump, 2)
                - 0.352006132756435 * this.jump + 0.159747619047662);
        // Denne funksjonen er en approksimasjon laget ved regresjon
    }

    public double getRealSpeed() {
        return formatDouble(this.speed * 43.17);
    }

    public static Double formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        String formatted = df.format(value);
        return Double.parseDouble(formatted);
    }

    public double getRealHealth() {
        return this.health / 2;
    }

    public int getRealAverage() {
        return (int) Math.round(this.average * 100);
    }

    // statiske verdier som er generelle for hester

    public static final double minSpeed = 0.1125;
    public static final double maxSpeed = 0.3375;

    public static final double minJump = 0.4;
    public static final double maxJump = 1.0;

    public static final double minHealth = 15;
    public static final double maxHealth = 30;
}
