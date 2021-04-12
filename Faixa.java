package model;


public class Faixa {
    private int cod_faixa;
    private String descricao;
    private float tempo;

    public Faixa(int cod_faixa, String descricao, float tempo) {
        this.cod_faixa = cod_faixa;
        this.descricao = descricao;
        this.tempo = tempo;
    }

    public int getCod_faixa() {
        return cod_faixa;
    }

    public void setCod_faixa(int cod_faixa) {
        this.cod_faixa = cod_faixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
    
    
    
}
