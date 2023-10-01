package utility;
import java.util.Random;
public class RandomUtility {
    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Isaac", "Jack"};
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 65;

    private static final int MIN_SALARY = 100000;
    private static final int MAX_SALARY = 1000000;

    private static final Random RANDOM = new Random();

    private RandomUtility() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String getRandomName() {
        int randomIndex = RANDOM.nextInt(NAMES.length);
        return NAMES[randomIndex];
    }

    public static String getRandomAge() {
        int randomAge = RANDOM.nextInt(MAX_AGE - MIN_AGE + 1) + MIN_AGE;
        return String.valueOf(randomAge);
    }
    public static String getRandomSalary() {
        int randomSalary = RANDOM.nextInt(MAX_SALARY - MIN_SALARY + 1) + MIN_SALARY;
        return String.valueOf(randomSalary);
    }
}
