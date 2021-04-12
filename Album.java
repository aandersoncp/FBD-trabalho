package model;

public class Album {
    private int cod_album;
    private String descricao;

    public Album(int cod_faixa, String descricao) {
        this.cod_album = cod_faixa;
        this.descricao = descricao;
    }

    public int getCod_album() {
        return cod_album;
    }

    public void setCod_album(int cod_album) {
        this.cod_album = cod_album;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
