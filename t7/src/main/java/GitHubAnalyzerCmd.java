import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class GitHubAnalyzerCmd {

    // CONTROLLER COMMIT
    private static CommitController commitController = null;
    // THREAD
    private static Request requestThread = null;
    // LISTAS
    private static ArrayList<CommitController> controladores = new ArrayList<CommitController>();

    // CONTROLLER FILE
    static Controller controller = new Controller();

    public static void main(String[] args) throws IOException {
        File file = new File("github.txt");

        manipulaArquivoAberto(file);
        requisita();

    }

    private static void requisita() {
        // REQUEST DE CADA REPOSITORIO
        requestOpen();
        try {
            janelaRequests();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    private static void janelaRequests() throws ParseException {
        // PRINT REPOSITORIOS
        for(CommitController controlador : controladores){
            System.out.println(controlador.getRepositorioStr()+"\tQuantia de commits: "+controlador.getQuantiaCommits()+"\tTamanho medio das mensagens: "+controlador.getTamanhoMedio());
        }



        // MAIORES E MENORES
        CommitController repositorioMaisAntigo = null;
        for(CommitController o : controladores){
            if(repositorioMaisAntigo == null) repositorioMaisAntigo = o;
            else{
                SimpleDateFormat formato = new SimpleDateFormat("\"yyyy-MM-dd'T'HH:mm:ss'Z'\"");
                formato.setTimeZone(TimeZone.getTimeZone("GMT"));

                String dataAntigo = repositorioMaisAntigo.getAntigo().getDate();
                String dataAtual = o.getAntigo().getDate();

                Date antigo = formato.parse(dataAntigo);
                Date atual = formato.parse(dataAtual);

                if(atual.before(antigo)){
                    repositorioMaisAntigo = o;
                }
            }
        }

        CommitController repositorioMaisNovo = null;
        for(CommitController o : controladores){
            if(repositorioMaisNovo == null) repositorioMaisNovo = o;
            else{
                SimpleDateFormat formato = new SimpleDateFormat("\"yyyy-MM-dd'T'HH:mm:ss'Z'\"");
                formato.setTimeZone(TimeZone.getTimeZone("GMT"));

                String dataNovo = repositorioMaisNovo.getNovo().getDate();
                String dataAtual = o.getNovo().getDate();

                Date novo = formato.parse(dataNovo);
                Date atual = formato.parse(dataAtual);

                if(atual.after(novo)){
                    repositorioMaisNovo = o;
                }
            }
        }

        CommitController maisCommits = null;
        for(CommitController o : controladores){
            if(maisCommits == null) maisCommits = o;
            else{
                if(o.getQuantiaCommits() > maisCommits.getQuantiaCommits()){
                    maisCommits = o;
                }
            }
        }

        CommitController menosCommits = null;
        for(CommitController o : controladores){
            if(menosCommits == null) menosCommits = o;
            else{
                if(o.getQuantiaCommits() < menosCommits.getQuantiaCommits()){
                    menosCommits = o;
                }
            }
        }

        System.out.println("Repositorio com mais commits: " + maisCommits.getRepositorioStr());
        System.out.println("Repositorio com menos commits: " + menosCommits.getRepositorioStr());
        System.out.println("Repositorio com commit mais recente: " + repositorioMaisNovo.getRepositorioStr());
        System.out.println("Repositorio com commit mais antigo: " + repositorioMaisAntigo.getRepositorioStr());
    }

    private static void requestOpen() {
        int indice = 0;
        // REQUEST DE TODOS OS REPOSITORIOS DISPONIVEIS
        int o;
        for(o = 0; o < controller.getQuantiaRepositorios(); o++){
            System.out.print("Iniciando requests de ");
            // CONTROLADOR DE COMMITS DO REPOSITORIO indice
            commitController = new CommitController(indice, controller.getDadoIdx(indice));
            // CRIA O REQUESTER
            requestThread = new Request(controller.geraCaminhos());
            requestThread.setCommitController(commitController);
            requestThread.setIndice(indice);
            indice++;
            controladores.add(commitController);
            requestThread.start();

            // ESPERA A OUTRA THREAD
            try {
                commitController.conditionWait();
            } catch (InterruptedException e1) {
                System.out.println("Erro na thread wait.");
                return;
            }
        }
    }

    private static void manipulaArquivoAberto(File file) throws IOException {
        // SETA ARQUIVO NO CONTROLE
        controller.setArquivo(file);
        // GERA OS DADOS
        controller.collectDados();
    }
}
