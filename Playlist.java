package model;

public class Playlist {
    private String nome;
    private int cod_playlist;
    private float tempo;

    public Playlist(String nome, int cod_playlist, float tempo) {
        this.nome = nome;
        this.cod_playlist = cod_playlist;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_playlist() {
        return cod_playlist;
    }

    public void setCod_playlist(int cod_playlist) {
        this.cod_playlist = cod_playlist;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
    
    
}
