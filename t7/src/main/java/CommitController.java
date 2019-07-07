import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CommitController {
    int indiceRepositorio;

    ArrayList<Commit> commits = null;
    private final ObservableList<Commit> dados = FXCollections.observableArrayList();

    public CommitController(int indice){
        this.indiceRepositorio = indice;
        commits = new ArrayList<Commit>();
    }

    public void addCommit(Commit commit){
        commits.add(commit);
        dados.add(commit);
    }

    public ObservableList<Commit> getCommits(){ return this.dados; }

    public synchronized void conditionSignal(){
        notifyAll();
    }

    public synchronized void conditionWait() throws InterruptedException {
        wait();
    }

}
