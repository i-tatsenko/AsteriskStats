/**
 * Created with IntelliJ IDEA.
 * User: docent
 * Date: 02.09.13
 * Time: 12:08
 */
public class ErrHandler {
    public static void fileMissing(String file){
        System.err.println("There is no settings file: " + file);
        System.err.println("Program shutdown.");
        System.err.println("Operation system: " + System.getProperties().getProperty("os.name"));
        System.exit(0);
    }
}
