package in.engineeringhelp.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_UP = "Site is up!";
    private final String SITE_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect";


    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if(responseCodeCategory !=2 && responseCodeCategory !=3){
                returnMessage = SITE_DOWN;
                System.out.println(responseCodeCategory);
                System.out.println(returnMessage);
            } else{
                returnMessage = SITE_UP;
                System.out.println(returnMessage);
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_DOWN;
        }
        return returnMessage;
    }
    
}
