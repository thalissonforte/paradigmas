package t6.paradigmas;

import javafx.beans.property.SimpleStringProperty;

import java.io.UnsupportedEncodingException;

public class Row {
    private final SimpleStringProperty curso;
    private final SimpleStringProperty ano;
    private final SimpleStringProperty prova;
    private final SimpleStringProperty tipoQuestao;
    private final SimpleStringProperty idQuestao;
    private final SimpleStringProperty objeto;
    private final SimpleStringProperty objetoDetalhado;
    private final SimpleStringProperty gabarito;
    private final SimpleStringProperty acertosCurso;
    private final SimpleStringProperty acertosRegiao;
    private final SimpleStringProperty acertosBrasil;
    private final SimpleStringProperty diferencaAcertos;
    private final SimpleStringProperty texto;

    public String getCurso() {
        return curso.get();
    }

    public SimpleStringProperty cursoProperty() {
        return curso;
    }

    public String getAno() {
        return ano.get();
    }

    public SimpleStringProperty anoProperty() {
        return ano;
    }

    public String getProva() {
        return prova.get();
    }

    public SimpleStringProperty provaProperty() {
        return prova;
    }

    public String getTipoQuestao() {
        return tipoQuestao.get();
    }

    public SimpleStringProperty tipoQuestaoProperty() {
        return tipoQuestao;
    }

    public String getIdQuestao() {
        return idQuestao.get();
    }

    public SimpleStringProperty idQuestaoProperty() {
        return idQuestao;
    }

    public String getObjeto() {
        return objeto.get();
    }

    public SimpleStringProperty objetoProperty() {
        return objeto;
    }

    public String getObjetoDetalhado() {
        return objetoDetalhado.get();
    }

    public SimpleStringProperty objetoDetalhadoProperty() {
        return objetoDetalhado;
    }

    public String getGabarito() {
        return gabarito.get();
    }

    public SimpleStringProperty gabaritoProperty() {
        return gabarito;
    }

    public String getAcertosCurso() {
        return acertosCurso.get();
    }

    public SimpleStringProperty acertosCursoProperty() {
        return acertosCurso;
    }

    public String getAcertosRegiao() {
        return acertosRegiao.get();
    }

    public SimpleStringProperty acertosRegiaoProperty() {
        return acertosRegiao;
    }

    public String getAcertosBrasil() {
        return acertosBrasil.get();
    }

    public SimpleStringProperty acertosBrasilProperty() {
        return acertosBrasil;
    }

    public String getDiferencaAcertos() {
        return diferencaAcertos.get();
    }

    public SimpleStringProperty diferencaAcertosProperty() {
        return diferencaAcertos;
    }

    public String getTexto() {
        return texto.get();
    }

    public SimpleStringProperty textoProperty() {
        return texto;
    }

    public String getImagem() {
        return imagem.get();
    }

    public SimpleStringProperty imagemProperty() {
        return imagem;
    }

    public String getUrlProva() {
        return urlProva.get();
    }

    public SimpleStringProperty urlProvaProperty() {
        return urlProva;
    }

    public String getUrlSintese() {
        return urlSintese.get();
    }

    public SimpleStringProperty urlSinteseProperty() {
        return urlSintese;
    }

    public String getUrlCurso() {
        return urlCurso.get();
    }

    public SimpleStringProperty urlCursoProperty() {
        return urlCurso;
    }

    public String getUrlCrop() {
        return urlCrop.get();
    }

    public SimpleStringProperty urlCropProperty() {
        return urlCrop;
    }

    private final SimpleStringProperty imagem;
    private final SimpleStringProperty urlProva;
    private final SimpleStringProperty urlSintese;
    private final SimpleStringProperty urlCurso;
    private final SimpleStringProperty urlCrop;

    public Row(String curso, String ano, String prova, String tipoQuestao, String idQuestao, String objeto, String objetoDetalhado, String gabarito, String acertosCurso, String acertosRegiao, String acertosBrasil, String diferencaAcertos, String texto, String imagem, String urlProva, String urlSintese, String urlCurso, String urlCrop) throws UnsupportedEncodingException {
        this.curso = new SimpleStringProperty(curso);
        this.ano = new SimpleStringProperty(ano);
        this.prova = new SimpleStringProperty(new String(prova.getBytes(), "UTF-8"));
        this.tipoQuestao = new SimpleStringProperty(new String(tipoQuestao.getBytes(), "UTF-8"));
        this.idQuestao = new SimpleStringProperty(idQuestao);
        this.objeto = new SimpleStringProperty(new String(objeto.getBytes(), "UTF-8"));
        this.objetoDetalhado = new SimpleStringProperty(new String(objetoDetalhado.getBytes(), "UTF-8"));
        this.gabarito = new SimpleStringProperty(new String(gabarito.getBytes(), "UTF-8"));
        this.acertosCurso = new SimpleStringProperty(acertosCurso);
        this.acertosRegiao = new SimpleStringProperty(acertosRegiao);
        this.acertosBrasil = new SimpleStringProperty(acertosBrasil);
        this.diferencaAcertos = new SimpleStringProperty(diferencaAcertos);
        this.texto = new SimpleStringProperty(new String(texto.getBytes(), "UTF-8"));
        this.imagem = new SimpleStringProperty(imagem);
        this.urlProva = new SimpleStringProperty(urlProva);
        this.urlSintese = new SimpleStringProperty(urlSintese);
        this.urlCurso = new SimpleStringProperty(urlCurso);
        this.urlCrop = new SimpleStringProperty(urlCrop);
    }
}
