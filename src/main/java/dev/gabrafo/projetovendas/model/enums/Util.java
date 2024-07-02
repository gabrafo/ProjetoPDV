/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.gabrafo.projetovendas.model.enums;

import java.awt.Component;
import javax.swing.*;

/**
 *
 * @author gabrafo
 */
public class Util {
    public static void limparTela(JPanel container){
        Component[] components = container.getComponents();
        for(Component field : components){
            if(field instanceof JTextField){
            ((JTextField) field).setText(null);
            }
        }
    }
}
