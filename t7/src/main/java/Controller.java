import java.io.*;
import java.util.ArrayList;

public class Controller {
    protected ArrayList dados = null;
    protected File file;

    // CONSTRUTOR SEM O ARQUIVO
    public Controller(){
        this.file = null;
    }

    public void setArquivo(File file) throws IOException {
        this.file = file;
        if(file != null){
            collectDados();
        }
    }

    private void collectDados() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new FileReader(file));
        this.dados = new ArrayList();
        String line = null;
        while((line = bufferRead.readLine()) != null){
            dados.add(line);
        }
        bufferRead.close();
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

}
