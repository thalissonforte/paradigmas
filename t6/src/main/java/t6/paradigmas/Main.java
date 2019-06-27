package t6.paradigmas;

import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    // CONTROLADOR
    static Controller controller;

    // TABLEVIEW
    TableView<Row> table = new TableView<Row>();

    // OBJETOS GRAFICOS
    // MENUS
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    Menu helpMenu = new Menu("Help");
    MenuItem reloadFile = new MenuItem("Reload");
    MenuItem sourceFile = new MenuItem("Source");
    MenuItem exitFile = new MenuItem("Exit");
    MenuItem about = new MenuItem("About");
    /////////////////////

    public static void main(String[] args) throws Exception {
        controller = new Controller();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // BOTÃO RELOAD
        reloadFile.setOnAction(e -> {
            try {
                reloadTable();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // BOTÃO SOURCE
        sourceFile.setOnAction(e -> {
            try {
                controller.changeSource();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        // BOTÃO ABOUT
        about.setOnAction(e ->{
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);;
            dialog.setTitle("About");
            VBox vb = new VBox(20);
            vb.setAlignment(Pos.CENTER);
            vb.setSpacing(10);
            vb.getChildren().add(new Text("\tENADE UFSM Explorer \ndesenvolvida por Thalisson Forte"));
            Scene dialogScene = new Scene(vb, 200, 120);
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.show();
        });

        // BOTÃO EXIT
        exitFile.setOnAction(e -> System.exit(0));

        // LINHA CLICADA
        table.setOnMouseClicked(e ->{
            // PROPRIEDADES INICIAIS
            int index = table.getSelectionModel().getSelectedIndex();
            if(index > -1) {
                Row linha = controller.getRows().get(index);
                int heightScene = 600, widthScene = 500;

                // CRIANDO NOVA JANELA
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                dialog.setTitle("Questão " + linha.getIdQuestao());

                // BOXES
                VBox vb = new VBox(10);
                vb.setAlignment(Pos.CENTER);
                VBox vb2 = new VBox(10);
                vb2.setAlignment(Pos.CENTER);
                VBox vb3 = new VBox(10);
                vb3.setAlignment(Pos.CENTER);

                // ANALISANDO A EXISTENCIA DE IMAGEM E SETANDO IMGVIEW
                ImageView imageView = null;
                if (linha.getUrlCrop().length() > 0) { // CASO HAJA IMAGEM
                    URL url = null;
                    try {
                        url = new URL(linha.getUrlCrop());
                        imageView = new ImageView(linha.getUrlCrop());
                        imageView.setFitHeight(heightScene);
                        imageView.setPreserveRatio(true);
                        vb2.getChildren().add(imageView);
                    } catch (MalformedURLException e1) {
                        System.out.println("Erro de URL.");
                        e1.printStackTrace();
                    }
                } else { // OPCAO CASO NAO HAJA IMAGEM
                    heightScene = 80;
                    widthScene = 200;
                }

                // VINCULANDO
                Text gabarito = new Text("Gabarito: " + linha.getGabarito());
                vb3.getChildren().add(gabarito);
                vb.getChildren().addAll(vb2, vb3);
                // EXIBIR SCENE
                Scene dialogScene = new Scene(vb, widthScene, heightScene + 50);
                dialog.setScene(dialogScene);
                dialog.setResizable(false);
                dialog.show();
            }
            // LIMPAR "BUFFER" DE SELECAO
            table.getSelectionModel().clearSelection();
        });

        // MENUS
        fileMenu.getItems().addAll(reloadFile, sourceFile, exitFile);
        helpMenu.getItems().add(about);
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // BOXES
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);
        HBox hbox1 = new HBox();
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.BOTTOM_CENTER);


        // COLUNAS
        TableColumn<Row, String> colAno = new TableColumn<Row, String>("Ano");
        colAno.setCellValueFactory(cellData -> cellData.getValue().anoProperty());

        TableColumn<Row, String> colProva = new TableColumn<Row, String>("Prova");
        colProva.setCellValueFactory(cellData -> cellData.getValue().provaProperty());

        TableColumn<Row, String> colTipo = new TableColumn<Row, String>("Tipo");
        colTipo.setCellValueFactory(cellData -> cellData.getValue().tipoQuestaoProperty());

        TableColumn<Row, String> colIdQuestao = new TableColumn<Row, String>("ID Questão");
        colIdQuestao.setCellValueFactory(cellData -> cellData.getValue().idQuestaoProperty());

        TableColumn<Row, String> colObjeto = new TableColumn<Row, String>("Objeto");
        colObjeto.setCellValueFactory(cellData -> cellData.getValue().objetoProperty());

        TableColumn<Row, String> colAcertosCurso = new TableColumn<Row, String>("Acertos Curso");
        colAcertosCurso.setCellValueFactory(cellData -> cellData.getValue().acertosCursoProperty());

        TableColumn<Row, String> colAcertosRegiao = new TableColumn<Row, String>("Acertos Região");
        colAcertosRegiao.setCellValueFactory(cellData -> cellData.getValue().acertosRegiaoProperty());

        TableColumn<Row, String> colAcertosBrasil = new TableColumn<Row, String>("Acertos Brasil");
        colAcertosBrasil.setCellValueFactory(cellData -> cellData.getValue().acertosBrasilProperty());

        TableColumn<Row, String> colDifAcertos = new TableColumn<Row, String>("Diferença Acertos");
        colDifAcertos.setCellValueFactory(cellData -> cellData.getValue().diferencaAcertosProperty());

        // ADICIONANDO COLUNAS NA TABELA
        table.getColumns().addAll(colAno, colProva, colTipo, colIdQuestao, colObjeto,colAcertosCurso, colAcertosRegiao, colAcertosBrasil, colDifAcertos);

        // SET DADOS PRA TABELA
        table.setItems(controller.getRows());

        // VINCULANDO
        vbox.getChildren().addAll(menuBar, table);

        // PROPRIEDADES STAGE
        stage.setTitle("ENADE UFSM Explorer");
        stage.setScene(new Scene(vbox, 720, 450));
        stage.show();
    }

    private void reloadTable() throws Exception {
        table.getItems().clear();
        controller.collectDados();
        table.setItems(controller.getRows());
    }
}
