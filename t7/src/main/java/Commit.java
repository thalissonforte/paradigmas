import javafx.beans.property.SimpleStringProperty;

public class Commit {
    private SimpleStringProperty author;
    private SimpleStringProperty msg;
    private SimpleStringProperty date;
    private int indiceAssociado;

    public Commit(String author, String msg, String date, int indice){
        this.author = new SimpleStringProperty(author);
        this.msg = new SimpleStringProperty(msg);
        this.date = new SimpleStringProperty(date);
        this.indiceAssociado = indice;
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public String getMsg() {
        return msg.get();
    }

    public SimpleStringProperty msgProperty() {
        return msg;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public int getIndiceAssociado() {
        return indiceAssociado;
    }
}
