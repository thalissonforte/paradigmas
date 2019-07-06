import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GitHubAnalyzerGUI extends Application {

    // CONTROLADOR
    static Controller controller = new Controller();

    // TABLEVIEW
    //TableView<Row> table = new TableView<Row>();

    // OBJETOS GRAFICOS
    //TextArea textArea = new TextArea();
    ListView listView = new ListView();

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
    /////////////////////

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
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
                        System.out.println("Nome do arquivo aberto: " + fileChoosed.getName());
                        controller.setArquivo(fileChoosed);
                        listView.getItems().clear();
                        String elemento;
                        int idx = 0;
                        // PERCORRE TODOS OS LINKS E ADICIONA NA LISTVIEW
                        while(idx < controller.quantiaDados() && (elemento = controller.retornaElemento(idx)) != null){
                            listView.getItems().add(elemento);
                            idx++;
                        }
                    }
                    else System.out.println("O arquivo não foi aberto.");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else{
                System.out.println("Erro ao abrir arquivo.");
            }
        });

        // BOXES
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);

        // MENUS
        fileMenu.getItems().addAll(openFile, exit);
        toolsMenu.getItems().addAll(commitAnalyzer);
        helpMenu.getItems().add(about);
        menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);

        // SETANDO BOX
        vbox.getChildren().addAll(menuBar, listView);

        // PROPRIEDADES STAGE
        stage.setTitle("GitHub Analyzer");
        stage.setScene(new Scene(vbox, 720, 450));
        stage.show();

    }
}
