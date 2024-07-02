/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.gabrafo.projetovendas.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author gabrafo
 */
public class ConnectionFactory {

    private static Connection conn = null;

    public static Connection getConnection(){ // Instanciação de um objeto do tipo Connection
        try {
            if (conn == null) {
                Properties props = loadProperties(); // Pega as propriedades do DB
                String url = props.getProperty("dburl"); // Pega a URL do DB
                conn = DriverManager.getConnection(url, props);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(){ // Fechar conexão
        if(conn != null){ // Testa se a conexão está instanciada
            try {
                conn.close();
            } catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){ // Carregar propriedades do DB
        try(FileInputStream fs = new FileInputStream("src/db.properties")){
            Properties props = new Properties();
            props.load(fs); // Guarda as propriedades do DB dentro do objeto props
            return props;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
