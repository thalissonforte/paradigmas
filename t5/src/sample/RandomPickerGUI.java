package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class RandomPickerGUI extends Application {

    // CONTROLADOR
    FileController controller = null;

    // OBJETOS GRÁFICOS
    Button shuffleButton = new Button("Shuffle");
    Button nextButton = new Button("Next");
    Button restart = new Button("Restart");
    CheckBox checkBox = new CheckBox("Online Mode");
    FileChooser fileChooser = new FileChooser();
    Label labelNome = new Label();
    TextArea textArea = new TextArea();
        // MENUS
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        MenuItem openFile = new MenuItem("Open");
        MenuItem exitFile = new MenuItem("Exit");
        MenuItem about = new MenuItem("About");
    /////////////////////

    @Override
    public void start(Stage stage) throws IOException {

        // PROPRIEDADES INICIAIS
        controller = new FileController();
        fileMenu.getItems().addAll(openFile, exitFile);
        helpMenu.getItems().add(about);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        nextButton.setDisable(true);
        shuffleButton.setDisable(true);
        restart.setDisable(true);

        // BOTÃO OPEN / FILE CHOOSER
        fileChooser.setTitle("Open Resource File");
        openFile.setOnAction(e -> {
            File fileChoosed = fileChooser.showOpenDialog(stage);
            if(fileChooser != null){
                try {
                    if(fileChoosed != null) {
                        System.out.println("Nome do arquivo aberto: " + fileChoosed.getName());
                        textArea.setText("");
                        controller.setArquivo(fileChoosed);
                        textArea.appendText(controller.arquivoToString());
                        if(textArea.getLength() > 0){
                            shuffleButton.setDisable(false);
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

        // BOTÃO EXIT
        exitFile.setOnAction(e -> System.exit(0));

        // BOTÃO SHUFFLE
        shuffleButton.setOnAction(e ->{
            try {
                // ANALIZAR TEXTAREA
                controller.manipularTextArea(textArea.getText());

                // RODAR SHUFFLE
                controller.runShuffle();
                // ZERAR E ALTERAR ALGUNS OBJETOS
                textArea.setText("");
                shuffleButton.setDisable(true);
                checkBox.setVisible(false);
                nextButton.setDisable(false);
                restart.setDisable(false);
                labelNome.setVisible(true);
                nextButton.fire();

            } catch (IOException e1) {
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
            vb.getChildren().add(new Text("Shuffle GUI desenvolvida por \nThalisson Forte"));
            Scene dialogScene = new Scene(vb, 200, 120);
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.show();
        });

        // BOTÃO NEXT
        nextButton.setOnAction(e ->{
            String nome = controller.getDado();
            if(nome != null){
                labelNome.setText(nome);
            }
            if(controller.dados.size()<1 || nome == null){
                nextButton.setDisable(true);
            }
        });

        // CHECKBOX MODE
        checkBox.setOnAction(e ->{
            if(checkBox.isSelected()){
                try {
                    if(controller.verificaConexao()){
                        controller.setOffline(false);
                    }else{
                        System.out.println("Conexão com o site random.org não pode ser estabelecida.");
                        checkBox.setSelected(false);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else{
                controller.setOffline(true);
            }
        });

        // TEXT AREA
        textArea.setOnKeyPressed(e ->{
            if(textArea.getLength() > 0){
                shuffleButton.setDisable(false);
            }else shuffleButton.setDisable(true);
        });

        // BOTÃO RESTART
        restart.setOnAction(e ->{
            restart.setDisable(true);
            labelNome.setVisible(false);
            checkBox.setVisible(true);
            shuffleButton.setDisable(true);
            nextButton.setDisable(true);
        });

        // BOXES
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.BOTTOM_CENTER);
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.BOTTOM_CENTER);
        hbox3.setSpacing(10);
        hbox3.setPadding(new Insets(10,10,10,10));
        hbox3.setAlignment(Pos.BOTTOM_CENTER);

        // VINCULANDO
        hbox1.getChildren().addAll(shuffleButton, nextButton, restart);
        hbox2.getChildren().addAll(labelNome);
        labelNome.setVisible(false);
        hbox3.getChildren().addAll(checkBox);
        vbox.getChildren().addAll(menuBar, textArea, hbox1, hbox2, hbox3);

        // PROPRIEDADES STAGE
        stage.setTitle("Shuffle GUI");
        stage.setScene(new Scene(vbox, 295, 310));
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
