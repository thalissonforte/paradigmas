import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

public class CommitController {
    int indiceRepositorio;
    int quantiaCommits = 0;
    int tamanhoMsg = 0;

    public String getRepositorioStr() {
        return repositorioStr;
    }

    String repositorioStr;

    Commit antigo = null;

    public Commit getAntigo() {
        return antigo;
    }

    public Commit getNovo() {
        return novo;
    }

    Commit novo = null;

    private SimpleStringProperty repositorio;
    private SimpleStringProperty quantiaCommitsStr;
    private SimpleStringProperty tamanhoMedio;

    private final ObservableList<Commit> dados = FXCollections.observableArrayList();
    public ObservableList<Commit> getCommits(){ return this.dados; }


    public CommitController(int indice, String repositorio){
        this.indiceRepositorio = indice;
        this.repositorioStr = repositorio;
        this.repositorio = new SimpleStringProperty(repositorio);
    }

    public SimpleStringProperty repositorioProperty() {
        return repositorio;
    }

    public SimpleStringProperty quantiaCommitsStrProperty() {
        quantiaCommitsStr = new SimpleStringProperty(Integer.toString(quantiaCommits));
        return quantiaCommitsStr;
    }

    public SimpleStringProperty tamanhoMedioProperty() {
        float tamanhoMedia = (float)tamanhoMsg / (float) quantiaCommits;
        tamanhoMedio = new SimpleStringProperty(Float.toString(tamanhoMedia));
        return tamanhoMedio;
    }

    public void addCommit(Commit commit) throws ParseException {
        dados.add(commit);
        this.quantiaCommits++;
        this.tamanhoMsg += commit.getTamanhoMsg();
        if(novo == null) setCommitNovo(commit);
        setCommitAntigo(commit);
    }

    public void setCommitNovo(Commit atual){
        novo = atual;
    }

    public void setCommitAntigo(Commit atual){
        antigo = atual;
    }

    public int getQuantiaCommits(){ return this.quantiaCommits;}

    public synchronized void conditionSignal(){
        notifyAll();
    }

    public synchronized void conditionWait() throws InterruptedException {
        wait();
    }

















}
