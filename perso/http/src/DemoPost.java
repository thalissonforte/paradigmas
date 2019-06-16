import java.net.*;
import java.io.IOException;

public class DemoPost {

    public static void main(String[] args) throws IOException {

        String urlstr = "https://script.google.com/a/inf.ufsm.br/macros/s/AKfycbwKrcRHm08L9bIxBIATDI65zWj7tb244VOq4kcPog/exec?";
        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //con.setRequestMethod("POST");
        //con.setDoOutput(true);

        //String data = "luckynumber=369&nccid=tfforte&msg=So then, let’s suppose that you were able every night to dream any dream you wanted to dream, and that you could, for example, have the power within one night to dream 75 years of time, or any length of time you wanted to have.";
        //con.getOutputStream().write(data.getBytes("UTF-8"));

        System.out.println("Classe do objeto con: " + con.getClass());
        System.out.println("Classe do objeto url: " + url.getClass());
        System.out.println("Response code: " + con.getResponseCode());
    }

}