package t6.paradigmas;

import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // DEFAULT
    String csvFile = "enade.csv";
    String urlDefaultCC = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";
    String urlDefaultSI = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=1285855524&single=true&output=csv";
    boolean sourceDefault = true; // TRUE - CC, FALSE - SI
    CSVReader reader = null;
    private final ObservableList<Row> data = FXCollections.observableArrayList();

    public Controller() throws Exception {
        if(new File(csvFile).exists()){
            System.out.println("Arquivo default carregado.");
        }else if(testaConexao("https://docs.google.com")){
            downloadCsv();
            System.out.println("Arquivo default online carregado.");
        }else{
            JOptionPane.showMessageDialog(null, "Arquivo default não pode ser carregado.\nVerifique sua conexão e tente novamente.");
            System.exit(0);
        }

        // CARREGAR DADOS APÓS INICIAR
        collectDados();
    }

    public void collectDados() throws Exception {
        String[] line;
        int linha = 0;
        data.clear();
        reader = new CSVReader(new FileReader(csvFile));
        reader.readNext(); // JUMP DO HEADER
        while ((line = reader.readNext()) != null) {
            linha++;
            data.add(new Row(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16], line[17]));
        }
    }

    public ObservableList<Row> getRows(){
        return this.data;
    }

    public boolean testaConexao(String address) throws IOException {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void downloadCsv() throws IOException {
        // VERIFICA SOURCE (CC OU SI)
        String urlDefault;
        if(sourceDefault) urlDefault = urlDefaultCC;
        else urlDefault = urlDefaultSI;
        // BUSCA PLANILHA
        URL url = new URL(urlDefault);
        File file = new File("enade.csv");
        // DOWNLOAD DA PLANILHA PARA O LOCAL DA FILE
        FileUtils.copyURLToFile(url, file);
    }

    private void reloadCsvOnline() throws Exception {
        // FAZ O DOWNLOAD
        downloadCsv();
    }

    public void changeSource() throws Exception {
        if(sourceDefault) sourceDefault = false;
        else sourceDefault = true;
        reloadCsvOnline();
    }

}
