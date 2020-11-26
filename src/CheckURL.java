import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CheckURL {
    Map<String, String> accessURL;
    String nameFile;

    public CheckURL(String nameFile) {
        this.accessURL = new HashMap<>();
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public Map<String, String> getAccessURL() {
        return accessURL;
    }

    private void loadingURLFromFile() {
        String url = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(this.nameFile)))) {
            while ((url = bufferedReader.readLine()) != null) {
                if (!accessURL.containsKey(url)) {
                    String responseAccess = check(url);
                    accessURL.put(url, responseAccess);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> resultOfChecking() {
        loadingURLFromFile();

        return this.accessURL;
    }

    private String check(String currentURL) {
        String resultOfChecking = "";
        int codeResponse = 0;
        try {
            URL url = new URL(currentURL);
            HttpURLConnection conтection = (HttpURLConnection) url.openConnection();
            codeResponse = conтection.getResponseCode();
            if (codeResponse >= 200 && codeResponse < 300) {
                resultOfChecking = "site available";
            } else {
                resultOfChecking = "site is not available";
            }

        } catch (MalformedURLException e) {
            resultOfChecking = "site is not available";
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultOfChecking;
    }
}
