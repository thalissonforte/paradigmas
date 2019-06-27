import com.opencsv.CSVReader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("TableView Example");

        TableView table = new TableView();
        TableColumn colAno = new TableColumn("Ano");
        TableColumn colProva = new TableColumn("Prova");
        TableColumn colTipo = new TableColumn("Tipo");
        TableColumn colQuestao = new TableColumn("Questão");
        TableColumn colIdQuestao = new TableColumn("ID Questão");
        TableColumn colObjeto = new TableColumn("Objeto");
        TableColumn colAcertosRegiao = new TableColumn("Acertos Região");
        TableColumn colAcertosBrasil = new TableColumn("Acertos Brasil");
        TableColumn colDifAcertos = new TableColumn("Diferença Acertos");

        table.getColumns().addAll(colAno, colProva, colTipo, colQuestao, colIdQuestao, colObjeto, colAcertosRegiao, colAcertosBrasil, colDifAcertos);
        //Ano, Prova, Tipo Questão, Id Questão, Objeto, Acertos Curso, Acertos Região, Acertos Brasil, Dif. (Curso-Brasil).

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);
        stage.setScene(new Scene(vbox, 750, 400));
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        CSVReader reader = new CSVReader(new FileReader("ENADE  CC SI UFSM Percentual de Acerto - CC.csv"));
        List<String[]> myEntries = reader.readAll();

        for(String[] a : myEntries){
            for(String b : a){
                System.out.println(b);
            }
            System.exit(0);
        }
        //launch(args);
    }
}
