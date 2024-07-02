/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.gabrafo.projetovendas.jdbc;

import javax.swing.*;
import java.sql.Connection;

/**
 *
 * @author gabrafo
 */
public class ConnectionTest {

    public static void main(String[] args) {
        try{
            Connection conn = ConnectionFactory.getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        }catch (DBException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }
}
