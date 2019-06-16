package sample;

import java.io.*;
import java.net.*;
import java.util.*;

public class FileController{

    // VARIÁVEIS BASE
    protected ArrayList dados = null;
    boolean offline_option = true;
    protected File file;

    // CONSTRUTOR SEM O ARQUIVO (CHAMADO PELO PickerGUI)
    FileController(){
        this.file = null;
    }

    // CONSTRUTOR QUANDO SE TEM O ARQUIVO (CHAMADO PELO PickerCMD)
    FileController(String arq) throws IOException {
        if(arq == null){ // ARQUIVO NULO
            System.out.println("Nenhum arquivo passado por parâmetro.");
            System.exit(1);
        }

        File file = new File(arq);
        if(!file.exists()){ // ARQUIVO NÃO ENCONTRADO
            System.out.println("Arquivo passado por parâmetro não existe neste diretório.");
            System.exit(1);
        }
        // ARQUIVO PASSADO
        collectDados(file);
    }

    // FUNÇÃO PARA SETAR ARQUIVO (CASO NECESSÁRIO)
    void setArquivo(File file) throws IOException {
        this.file = file;
        if(file != null){
            collectDados(file);
        }
    }

    // FUNÇÃO PARA COLETAR DADOS DE UM ARQUIVO ARQUIVO
    void collectDados(File file) throws IOException{
        BufferedReader bufferRead = new BufferedReader(new FileReader(file));
        if(bufferRead == null) System.exit(1);
        this.dados = new ArrayList();
        String line = null;
        while((line = bufferRead.readLine()) != null){
            dados.add(line);
        }
        bufferRead.close();
    }

    // FUNÇÃO DE IMPRESSÃO PELO ENTER
    public void exibirEnter() throws IOException {
        for(Object valor : dados) {
            System.out.println(valor);
            System.out.println("Pressione enter para continuar...");
            System.in.read();
        }
    }

    // REMOVER E RETORNAR PRIMEIRO DADO DA LIST
    String getDado(){
        if(this.dados.size()>0) return (String)this.dados.remove(0);
        else return null;
    }

    // SETA MODO DE EMBARALHAMENTO
    void setOffline(boolean bool){
        this.offline_option = bool;
    }

    // EXECUTA O EMBARALHAMENTO DOS DADOS NO ARRAYLIST
    public void runShuffle() throws IOException {
        if(this.offline_option) shuffleOffline();
        else shuffleOnline();
    }

    // VERIFICAÇÃO DA CONEXÃO COM O SITE RANDOM.ORG
    boolean verificaConexao() throws IOException {
        try{
            String urlstr = "https://www.random.org/lists/?mode=advanced";
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            return true;
        }catch(IOException e){
            return false;
        }

    }

    // MODOS DE SHUFFLE
    void shuffleOffline(){
        Collections.shuffle(this.dados);
    }
    void shuffleOnline() throws IOException {
        // VERIFICAR CONEXAO
        if(!verificaConexao()){
            System.out.println("Problema na conexão com o site random.org.");
            System.out.println("Embaralhamento operando em modo offline.\n");
            shuffleOffline();
            return;
        }
        // CONEXÃO FUNCIONANDO
        String urlstr = "https://www.random.org/lists/?mode=advanced";
        URL url = new URL(urlstr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setDoOutput(true);

        // FASE DE CRIAÇÃO DA URL DE BUSCA
        String nome;
        StringBuffer busca = new StringBuffer();
        busca.append("list=");
        while(dados.size()>0){
            nome = (String) dados.remove(0); // BUSCA NOME
            String nomeEncode = URLEncoder.encode(nome, "UTF-8"); // ENCODE NOME
            busca.append(nomeEncode); // ADICIONA NOME NA URL
            if(dados.size() != 0) busca.append("%0D%0A"); // ADICIONA DIVISOR
        }
        busca.append("&format=plain&rnd=new"); // ADICIONA FORMATO
        String stringBusca = busca.toString(); // PASSA O STRING BUFFER PARA UMA STRING

        // REALIZANDO CONEXÃO E BUSCA
        con.getOutputStream().write(stringBusca.getBytes("UTF-8"));

        // OBJETO QUE FAZ A LEITURA
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        // FAZ A LEITURA ATÉ NÃO ENCONTRAR MAIS ELEMENTOS
        String responseLine;
        while ((responseLine = in.readLine()) != null) {
            String resp = URLDecoder.decode(responseLine, "UTF-8");
            this.dados.add(resp); // SALVA OS ELEMENTOS EMBARALHADOS NO ARRAYLIST (QUE ESTÁ VAZIO)
        }
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

    public void manipularTextArea(String textStr){
        dados = new ArrayList();
        String lines[] = textStr.split("\\n");
        for(String line : lines){
            dados.add(line);
        }
    }
}