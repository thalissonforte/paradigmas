import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class GitHubAnalyzerGUI extends Application {

    // THREAD
    Request requestThread = null;

    // CONTROLADOR
    static Controller controller = new Controller();
    CommitController commitController = null;
            // OBJETOS GRAFICOS
    // MENUS
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem openFile = new MenuItem("Open");
    FileChooser fileChooser = new FileChooser();
    MenuItem exit = new MenuItem("Exit");

    Menu toolsMenu = new Menu("Tools");
    MenuItem commitAnalyzer = new MenuItem("Commit Analyzer");

    Menu helpMenu = new Menu("Help");
    MenuItem about = new MenuItem("About");
    // BOX
    VBox vbox = new VBox();

    // LISTVIEW
    ListView listView = new ListView();

    // TABLEVIEW
    TableView<CommitController> tableView = null;
    private final ObservableList<CommitController> dadosController = FXCollections.observableArrayList();
    ArrayList<CommitController> controladores = new ArrayList<CommitController>();

    //LABEL
    final Label label = new Label("Escolha a ferramenta que deseja na aba Tools");
    ////////////////////////////

    // MAIN
    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage stage){

        // COMMIT ANALYZER
        commitAnalyzer.setOnAction(e -> {
            int indice = listView.getFocusModel().getFocusedIndex();
            if(indice == -1){
                System.out.println("Carregue um arquivo na aba File e escolha um repositório.");
                return;
            }
            // REQUEST DE CADA REPOSITORIO
            requestOpen();

            try {
                janelaRequests(stage);
            } catch (InterruptedException e1) {
                System.out.println("Erro na requisição dos dados.");
                return;
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        // BOTÃO EXIT
        exit.setOnAction(e -> {
            System.out.println("Programa finalizado.");
            System.exit(0);
        });

        // BOTÃO OPEN / FILE CHOOSER
        fileChooser.setTitle("Open Resource File");
        openFile.setOnAction(e -> {
            File fileChoosed = fileChooser.showOpenDialog(stage);
            if(fileChooser != null){
                try {
                    if(fileChoosed != null) {
                        // MANIPULA ARQUIVO E PREENCHE LIST
                        manipulaArquivoAberto(fileChoosed);
                        preencheListView();
                    }else {
                        System.out.println("O arquivo não foi aberto.");
                        if(listView.getItems().size() <= 0) {
                            label.setText("Carregue o arquivo na aba Open.");
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else{
                System.out.println("Erro ao abrir arquivo.");
            }
        });

        // BOXES
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);
        label.setPadding(new Insets(10, 0, 0, 10));
        label.setText("Carregue o arquivo na aba Open.");

        // MENUS
        fileMenu.getItems().addAll(openFile, exit);
        toolsMenu.getItems().addAll(commitAnalyzer);
        helpMenu.getItems().add(about);
        menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);

        // SETANDO BOX
        vbox.getChildren().addAll(menuBar, label, listView);

        // PROPRIEDADES STAGE
        stage.setTitle("GitHub Analyzer");
        stage.setScene(new Scene(vbox, 720, 450));
        stage.show();
    }

    private synchronized void requestOpen() {
        int indice = 0;
        // REQUEST DE TODOS OS REPOSITORIOS DISPONIVEIS
        for(Object o : listView.getItems()){
            System.out.print("Iniciando requests de ");
            // CONTROLADOR DE COMMITS DO REPOSITORIO indice
            commitController = new CommitController(indice, controller.getDadoIdx(indice));
            // CRIA O REQUESTER
            requestThread = new Request(controller.geraCaminhos());
            requestThread.setCommitController(commitController);
            requestThread.setIndice(indice);
            indice++;
            dadosController.add(commitController);
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

    private synchronized void janelaRequests(Stage stage) throws InterruptedException, ParseException {

        // JANELA NOVA
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.setTitle("Dados sobre os repositorios");

        VBox vb = new VBox(20);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        TableColumn<CommitController, String> colRepositorio = new TableColumn<CommitController, String>("Repositório");
        colRepositorio.setCellValueFactory(cellData -> cellData.getValue().repositorioProperty());

        TableColumn<CommitController, String> colQntCommits = new TableColumn<CommitController, String>("Quantia de commits");
        colQntCommits.setCellValueFactory(cellData -> cellData.getValue().quantiaCommitsStrProperty());

        TableColumn<CommitController, String> colTamanhoMedio = new TableColumn<CommitController, String>("Tamanho médio msg");
        colTamanhoMedio.setCellValueFactory(cellData -> cellData.getValue().tamanhoMedioProperty());

        tableView = new TableView<CommitController>();
        tableView.getColumns().addAll(colRepositorio, colQntCommits, colTamanhoMedio);

        // SET DADOS PRA TABELA
        tableView.setItems(dadosController);

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

        Label l1 = new Label("Repositorio do commit mais recente: " + (repositorioMaisNovo.getRepositorioStr().split("/"))[4]);
        Label l2 = new Label("Repositorio do commit mais antigo: " + (repositorioMaisAntigo.getRepositorioStr().split("/"))[4]);
        Label l3 = new Label("Repositorio com mais commits: " + (maisCommits.getRepositorioStr().split("/"))[4]);
        Label l4 = new Label("Repositorio com menos commits: " + (menosCommits.getRepositorioStr().split("/"))[4]);

        PieChart graficoTotalCommits = new PieChart();

        for(CommitController cc : controladores){
            graficoTotalCommits.getData().addAll(new PieChart.Data(cc.getRepositorioStr(), cc.getQuantiaCommits()));
        }

        graficoTotalCommits.setTitle("Proporção do total de commits de cada repositório");
        graficoTotalCommits.setPrefSize(200, 200);

        // VINCULANDO
        vb.getChildren().addAll(l1,l2,l3,l4,tableView, graficoTotalCommits);

        Scene dialogScene = new Scene(vb, 600, 500);
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();
    }

    private void preencheListView() {
        // CLEAR NA LISTA ATUAL (CASO TENHA ALGO)
        listView.getItems().clear();
        // PREENCHER A LISTA
        String elemento;
        int idx = 0;
        // PERCORRE TODOS OS LINKS E ADICIONA NA LISTVIEW
        while(idx < controller.quantiaDados() && (elemento = controller.retornaElemento(idx)) != null){
            listView.getItems().add(elemento);
            idx++;
        }
    }

    private void manipulaArquivoAberto(File fileChoosed) throws IOException {
        // SETA ARQUIVO NO CONTROLE
        controller.setArquivo(fileChoosed);
        // GERA OS DADOS
        controller.collectDados();
        // EXIBE NOME DO ARQUIVO
        System.out.println("Nome do arquivo aberto: " + fileChoosed.getName());
        // MOSTRA LABEL
        label.setText("Escolha a ferramenta que deseja na aba Tools");
    }

}
