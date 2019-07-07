import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    TableView<Commit> tableView = null;

    //LABEL
    final Label label = new Label("Selecione o repositório e escolha a ferramenta que deseja na aba Tools");
    ////////////////////////////

    // MAIN
    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage stage){

        // COMMIT ANALYZER
        commitAnalyzer.setOnAction(e -> {
            // PRINT DO INDICE
            int indice = listView.getFocusModel().getFocusedIndex();
            if(indice == -1){
                System.out.println("Carregue um arquivo na aba File e escolha um repositório.");
                return;
            }

            // CONTROLADOR DE COMMITS DO REPOSITORIO indice
            commitController = new CommitController(indice);

            // MANDA OS CAMINHOS CERTOS PARA O REQUESTER, SETA O INDICE ATUAL E O COMMITCONTROLLER
            requestThread = new Request(controller.geraCaminhos());
            requestThread.setCommitController(commitController);
            requestThread.setIndice(indice);

            // INICIA REQUESTS
            requestThread.start();

            try {
                janelaRequests(stage);
            } catch (InterruptedException e1) {
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
                        // SETA, BUSCA E GERA CAMINHOS
                        //requestThread = new Request(controller.geraCaminhos());
                    }else {
                        System.out.println("O arquivo não foi aberto.");
                        if(listView.getItems().size() <= 0) label.setVisible(false);
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
        label.setVisible(false);

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

    private synchronized void janelaRequests(Stage stage) throws InterruptedException {
        // ESPERA A OUTRA THREAD
        //commitController.wait();
        // JANELA NOVA
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);;
        dialog.setTitle("Dados sobre o repositorio");

        VBox vb = new VBox(20);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        // COLUNAS
        TableColumn<Commit, String> colAuthor = new TableColumn<Commit, String>("Autor");
        colAuthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Commit, String> colMessage = new TableColumn<Commit, String>("Mensagem");
        colMessage.setCellValueFactory(cellData -> cellData.getValue().msgProperty());

        TableColumn<Commit, String> colDate = new TableColumn<Commit, String>("Data");
        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        tableView = new TableView<Commit>();

        // ADICIONANDO COLUNAS NA TABELA
        tableView.getColumns().addAll(colAuthor, colMessage, colDate);
        // SET DADOS PRA TABELA
        tableView.setItems(commitController.getCommits());

        // VINCULANDO
        vb.getChildren().addAll(tableView);

        Scene dialogScene = new Scene(vb, 500, 300);
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
        label.setVisible(true);
    }

}
