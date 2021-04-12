package controller;

import DAO.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import model.Album;
import model.Faixa;
import model.Playlist;

public class Controller {

    public Controller() {
    }
    
    public ArrayList<Album> mostrarAlbuns (){
        Connect conexao = new Connect();
        conexao.conectar();
        ArrayList<Album> lista = new ArrayList<Album>();
  
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select * from album");
            pst.execute();
            ResultSet albuns = pst.getResultSet();
            int codigo;
            String descricao;
            while(albuns.next()){
                codigo = albuns.getInt("cod_album");
                descricao = albuns.getString("descricao");
                Album novo = new Album(codigo, descricao);
                lista.add(novo);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    public ArrayList<Faixa> mostrarFaixasdeAlbum(int id){
        Connect conexao = new Connect();
        conexao.conectar();
        ArrayList<Faixa> lista = new ArrayList<Faixa>();
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select * from faixas where cod_album = ?");
            pst.setInt(1, id);
            pst.execute();
            ResultSet faixas = pst.getResultSet();
            int codigo;
            String descricao;
            float tempo;
            while(faixas.next()){
                codigo = faixas.getInt("cod_faixa");
                descricao = faixas.getString("descricao");
                tempo = faixas.getFloat("tempo_execucao");
                Faixa novo =  new Faixa(codigo, descricao, tempo);
                lista.add(novo);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Faixa buscarFaixa(int id){
        Connect conexao = new Connect();
        conexao.conectar();
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select * from faixas where cod_faixa = ?");
            pst.setInt(1, id);
            pst.execute();
            
            ResultSet faixa = pst.getResultSet();
            if(faixa.next()){
                 Faixa novo =  new Faixa(id, faixa.getString("descricao"), faixa.getFloat("tempo_execucao"));
                 return novo;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList<Playlist> mostrarPlaylists(){
        Connect conexao = new Connect();
        conexao.conectar();
        ArrayList<Playlist> lista = new ArrayList<Playlist>();
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select * from playlist");
            pst.execute();
            ResultSet playlists = pst.getResultSet();
            int codigo;
            String nome;
            float tempo;
            while(playlists.next()){
                codigo = playlists.getInt("cod_playlist");
                nome = playlists.getString("nome");
                tempo = playlists.getFloat("tempo_total");
                Playlist novo = new Playlist(nome, codigo, tempo);
                lista.add(novo);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList<Faixa> mostrarFaixasdePlaylist(int id){
        Connect conexao = new Connect();
        conexao.conectar();
  
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select * from faixas f, participa p where p.cod_playlist = ? and p.cod_faixa = f.cod_faixa");
            pst.setInt(1, id);
            pst.execute();
            ResultSet faixas = pst.getResultSet();
            ArrayList<Faixa> lista = new ArrayList<Faixa>();
            int codigo;
            String descricao;
            float tempo;
            while(faixas.next()){
                codigo = faixas.getInt("cod_faixa");
                descricao = faixas.getString("descricao");
                tempo = faixas.getFloat("tempo_execucao");
                Faixa novo =  new Faixa(codigo, descricao, tempo);
                lista.add(novo);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList<Faixa> mostrarFaixasForadePlaylist(int id){
        Connect conexao = new Connect();
        conexao.conectar();
  
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("select f.cod_faixa, f.descricao, f.tempo_execucao from faixas f EXCEPT select f.cod_faixa, f.descricao, f.tempo_execucao from faixas f, participa p where p.cod_playlist = ? and p.cod_faixa = f.cod_faixa");
            pst.setInt(1, id);
            pst.execute();
            ResultSet faixas = pst.getResultSet();
            ArrayList<Faixa> lista = new ArrayList<Faixa>();
            int codigo;
            String descricao;
            float tempo;
            while(faixas.next()){
                codigo = faixas.getInt("cod_faixa");
                descricao = faixas.getString("descricao");
                tempo = faixas.getFloat("tempo_execucao");
                Faixa novo =  new Faixa(codigo, descricao, tempo);
                lista.add(novo);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    
    public void deletarFaixaDePlaylist(int cod_faixa, int cod_playlist){
        Connect conexao = new Connect();
        conexao.conectar();
  
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("delete participa where cod_playlist = ? and cod_faixa = ?");
            pst.setInt(1, cod_playlist);
            pst.setInt(2, cod_faixa);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("");
        }
    }
    
    public void inserirFaixaEmPlaylist(int cod_faixa, int cod_playlist){
        Connect conexao = new Connect();
        conexao.conectar();
  
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("insert participa values (?, ?, 0, ?)");
            pst.setInt(1, cod_playlist);
            pst.setInt(2, cod_faixa);
            pst.setString(3, "01/01/2000");
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("");
        }
    }
    
    public void criarPlaylist(String nome, int id, ArrayList<Faixa> faixas){
        Connect conexao = new Connect();
        conexao.conectar();
        int tamanho = faixas.size();
        float tempoTotal = 0;
        Calendar hora = Calendar.getInstance();
        String momento = hora.get(Calendar.DAY_OF_MONTH) + "/" + hora.get(Calendar.MONTH)+ "/" + hora.get(Calendar.YEAR);
        for(int i = 0; i < tamanho; i++){
            tempoTotal = tempoTotal + faixas.get(i).getTempo();
        }
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("insert playlist values(?, ?, ?, ?)");
            pst.setInt(1, id);
            pst.setString(2, nome);
            pst.setString(3, momento);
            pst.setFloat(4, tempoTotal);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("");
        }
    }
    
    public void inserirParticipa(int cod_playlist, ArrayList<Faixa> faixas){
        Connect conexao = new Connect();
        conexao.conectar();
        Calendar hora = Calendar.getInstance();
        String momento = hora.get(Calendar.DAY_OF_MONTH) + "/" + hora.get(Calendar.MONTH)+ "/" + hora.get(Calendar.YEAR);
  
        try {
            for(int i = 0; i < faixas.size(); i++){
                PreparedStatement pst = conexao.conexao.prepareStatement("insert participa values (?, ?, 0, ?)");
                pst.setInt(1, cod_playlist);
                pst.setInt(2, faixas.get(i).getCod_faixa());
                pst.setString(3, momento);
                pst.execute();
            }
        } catch (SQLException ex) {
            System.out.println("");
        }
    }
    
    public void reproduzirPlaylist(int id){
        Connect conexao = new Connect();
        conexao.conectar();
        try {
            PreparedStatement pst = conexao.conexao.prepareStatement("update participa set numero_de_vezes_tocadas = numero_de_vezes_tocadas + 1 where cod_playlist = ?");
            pst.setInt(1, id);
            pst.execute();
            Calendar hora = Calendar.getInstance();
            String momento = hora.get(Calendar.DAY_OF_MONTH) + "/" + hora.get(Calendar.MONTH)+ "/" + hora.get(Calendar.YEAR);
            pst = conexao.conexao.prepareStatement("update participa set data_da_ultima_vez_tocada = ? where cod_playlist = ?");
            pst.setString(1, momento);
            pst.setInt(2, id);
            pst.execute();
        } catch (SQLException ex) {
            System.out.println("");
        }
    } 
}
