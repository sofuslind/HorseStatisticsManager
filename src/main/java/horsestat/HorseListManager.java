package horsestat;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HorseListManager {
    protected File horsesFile;
    protected ArrayList<Horse> horses;

    public HorseListManager(String path) {
        this.horsesFile = new File(path);
        this.horses = new ArrayList<>();
        try {
            if (horsesFile.createNewFile()) {
                System.out.println("File created: " + horsesFile.getName());
            } else {
                System.out.println("File already exists.");
                this.load();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(ArrayList<Horse> horsesSave) throws IOException {
        horsesFile.getParentFile().mkdirs();
        horsesFile.createNewFile();
        FileWriter writer = new FileWriter(horsesFile);

        horsesSave.forEach(horse -> {
            try {
                writer.write(horseInfo(horse) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }

    public void load() throws IOException {
        this.horses.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.horsesFile));

            reader.lines().forEach(string -> {
                String[] values = string.split(" ");
                this.horses.add(new Horse(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), values[4], values[5]));
            });

            reader.close();
        } catch (NoSuchElementException e) {
            throw new IOException("File corrupted");
        }
    }

    public void save(Horse horse) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(horsesFile));
        try {
            writer.append(horseInfo(horse) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }

    public String horseInfo(Horse horse) {
        return horse.getName() + " " + horse.getSpeed() + " " + horse.getJump() + " "
                + horse.getHealth() + " " + horse.getColor() + " " + horse.getPattern();
    }

    public ArrayList<Horse> getHorses() {
        return new ArrayList<Horse>(this.horses);
    }

    public void addHorse(Horse horse) throws IOException {
        this.horses.add(horse);
        this.save(this.horses);
    }

    public void removeHorse(int index) throws IOException {
        this.horses.remove(index);
        this.save(this.horses);
    }

    public void sortList() throws IOException {
        this.horses.sort(null);
        this.save(this.horses);
    }

    // Main metode for å teste

    public static void main(String[] args) throws IOException {

        HorseListManager me = new HorseListManager("./horses.txt");

        me.addTestHorses();

        me.load();
        for (Horse horses : me.getHorses()) {
            System.out.println(horses.getName());
        }
    }

    // Liste av hester for testing
    public void addTestHorses() throws IOException {
        List.of(
                new Horse("Aster", 0.15, 0.45, 18, "brown", "none"),
                new Horse("Blaze", 0.30, 0.90, 25, "black", "whitefield"),
                new Horse("Comet", 0.22, 0.60, 20, "gray", "whitedots"),
                new Horse("Dune", 0.18, 0.50, 17, "chestnut", "none"),
                new Horse("Echo", 0.33, 0.95, 28, "white", "white"),
                new Horse("Fjord", 0.12, 0.40, 15, "creamy", "none"),
                new Horse("Gale", 0.28, 0.75, 22, "darkbrown", "blackdots"),
                new Horse("Halo", 0.25, 0.65, 19, "gray", "whitefield"),
                new Horse("Iris", 0.20, 0.55, 16, "brown", "whitedots"),
                new Horse("Jade", 0.31, 0.85, 27, "black", "none"),
                new Horse("Koda", 0.27, 0.70, 23, "chestnut", "white"),
                new Horse("Luna", 0.34 - 0.0025, 0.98, 29, "white", "whitedots"),
                new Horse("Milo", 0.19, 0.52, 18, "creamy", "none"),
                new Horse("Nova", 0.29, 0.80, 24, "darkbrown", "blackdots"),
                new Horse("Orion", 0.24, 0.68, 21, "gray", "white"),
                new Horse("Pico", 0.14, 0.42, 16, "brown", "none"),
                new Horse("Quartz", 0.32, 0.88, 26, "black", "whitefield"),
                new Horse("Rune", 0.21, 0.58, 19, "chestnut", "whitedots"),
                new Horse("Sable", 0.26, 0.72, 22, "darkbrown", "none"),
                new Horse("Tango", 0.23, 0.66, 20, "creamy", "blackdots")).forEach(horse -> {
                    try {
                        this.addHorse(horse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
