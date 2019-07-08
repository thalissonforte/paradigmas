import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class Request extends Thread {

    ArrayList caminho = new ArrayList();
    int indice = -1;
    private CommitController commitController;

    public void run(){
        System.out.println(caminho.get(indice));

        // MANIPULAR E REALIZAR REQUISIÇÕES
        try {
            requestGitHub();
        } catch (IOException e) {
            System.out.println("Requisição falhou, verifique sua conexão à internet e tente novamente.");
            return;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private synchronized void requestGitHub() throws IOException, ParseException {
        String urlstr = (String) caminho.get(indice);
        JsonArray results;
        int paginaAtual = 1;

        do{
            // PAGINAÇÃO
            String urlPag = urlstr + "?page="+paginaAtual;
            // CONEXÃO
            URL url = new URL(urlPag);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Parse a nested JSON response using Gson
            JsonParser parser = new JsonParser();
            results = parser.parse(in.readLine()).getAsJsonArray();
            System.out.println("Size: " + results.size());
            Commit commitAux;

            // MANIPULA OS DADOS BUSCADOS
            for (JsonElement linha : results) {
                JsonElement base = linha.getAsJsonObject().get("commit");

                JsonElement name = base
                        .getAsJsonObject().get("author")
                        .getAsJsonObject().get("name");

                JsonElement data = base
                        .getAsJsonObject().get("author")
                        .getAsJsonObject().get("date");

                JsonElement mensagem = base
                        .getAsJsonObject().get("message");

                // CRIA E ADICIONA COMMIT
                commitAux = new Commit(name.toString(), mensagem.toString(), data.toString(), this.indice);
                commitController.addCommit(commitAux);
            }

            // FECHAR CONEXÃO
            in.close();
            // PROXIMA PAGINA
            paginaAtual++;
        }while(results.size() >= 30);
        // AVISANDO QUE TERMINOU
        commitController.conditionSignal();
    }

    public Request(ArrayList caminhos){
        for(Object cam:caminhos){
            this.caminho.add(cam);
        }
    }


    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setCommitController(CommitController commitController) {
        this.commitController = commitController;
    }
}
