import java.io.*;
import java.util.ArrayList;

public class Controller {
    protected ArrayList dados = null;
    private ArrayList caminhos = null;
    protected File file;
    private Request request;

    // CONSTRUTOR SEM O ARQUIVO
    public Controller(){
        this.file = null;
    }

    public void setArquivo(File file) throws IOException {
        this.file = file;
    }

    public void collectDados() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new FileReader(file));
        this.dados = new ArrayList();
        String line = null;
        while((line = bufferRead.readLine()) != null){
            if(line.contains("github.com")) dados.add(line);
        }
        bufferRead.close();
    }

    public ArrayList geraCaminhos() {
        caminhos = new ArrayList();
        for(Object obj : dados){
            String url = ((String) obj).toLowerCase();
            String urlReplace = null;
            String caminho = null;
            if(url.contains("github.com")){
                // VERIFICA A URL
                urlReplace = url.replace("github.com","api.github.com/repos");
                if(urlReplace.contains("api.github.com") == false){
                    // NAO ERA UMA URL DO GITHUB
                    return null;
                }

                caminho = urlReplace + "/commits";
                caminhos.add(caminho);
            }
        }
        return caminhos;
    }

    // TRANSFORMA OS DADOS DA LIST EM STRING
    String arquivoToString(){
        StringBuffer texto = new StringBuffer();
        if(dados != null){
            for(Object dado : dados){
                texto.append((String) dado);
                texto.append("\n");
            }
            return texto.toString();
        }
        return "";
    }

    String retornaElemento(int idx){
        if(dados != null && dados.get(idx) != null && dados.size()>0){
            String elemento = (String) dados.get(idx);
            return elemento;
        }
        return null;
    }

    public int quantiaDados(){
        return dados.size();
    }

    public String getDadoIdx(int idx){
        return (String) dados.get(idx);
    }

    public int getQuantiaRepositorios() {
        return dados.size();
    }
}
