package horsestat;

public abstract class Animal implements Comparable<Animal> {

    protected double average;
    protected String name;

    public boolean validStringCheck(String string) {
        return (string == null || string.equals("") || string.isEmpty());
    }

    public void validCheck(double value, double min, double max, String error) {
        if (min > value || max < value) {
            throw new IllegalArgumentException("invalid" + error + ": outside of expected range");
        }
    }

    public double getAverage() {
        return this.average;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        validStringCheck(name);
        this.name = name;
    }

    public abstract void findAverage();

    @Override
    public int compareTo(Animal o) {
        return -Double.compare(this.getAverage(), o.getAverage());
    }

}