import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CheckURL checkURL = new CheckURL("URL.txt");
        Map<String, String> map = checkURL.resultOfChecking();
        map.forEach((key, vale) -> System.out.println(key + "a " + vale));
    }
}
