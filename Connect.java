package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
import model.Faixa;

public class Connect {
    public Statement stm; // Responsavel por preparar e realizar pesquisas no banco de dados;
    public ResultSet rs; // Responsavel por armazenar o resultado de um pesquisa passada para o statement;
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String caminho = "jdbc:sqlserver://localhost:1433;databaseName=spotper"; // O "control" representa a minha database 
    private String usuario = "SA";
    private String senha = "87970551aA@";
    public Connection conexao; // Responsavel por realizar a conexão com o banco de dados;

    public Connect() {
        driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        caminho = "jdbc:sqlserver://localhost:1433;databaseName=spotper"; // O "control" representa a minha database 
        usuario = "SA";
        senha = "87970551aA@";
    }
    
    public void conectar() { // Metodo responsavel por realizar a conexão;
        try {
            System.setProperty("jdbc.Drivers", driver); // Seta a propriedade do driver de conexão;
            conexao = DriverManager.getConnection(caminho, usuario, senha); // Realiza a conexão com o banco;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO DE CONEXÃO!!");
        }
    }
}

