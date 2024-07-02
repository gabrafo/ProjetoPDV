package dev.gabrafo.projetovendas.dao;

import dev.gabrafo.projetovendas.jdbc.ConnectionFactory;
import dev.gabrafo.projetovendas.jdbc.DBException;
import dev.gabrafo.projetovendas.model.Compra;
import dev.gabrafo.projetovendas.model.enums.Status;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    private Connection conn = null;
    private PreparedStatement st = null;

    public CompraDAO(){
        this.conn = ConnectionFactory.getConnection();
    }

    public List<Compra> listarComprasPorCliente(int clienteId){
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Compra> lista = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConnection();

            st = conn.prepareStatement("SELECT * FROM tb_compras WHERE cliente_id = ?");
            st.setInt(1, clienteId);
            rs = st.executeQuery();
            while (rs.next()) {
                Compra obj = new Compra();
                obj.setId(rs.getInt("id"));
                obj.setClienteId(rs.getInt("cliente_id"));
                obj.setDataCompra(rs.getTimestamp("data_compra"));
                obj.setTotalCompra(rs.getDouble("total_compra"));
                obj.setStatus(Status.valueOf(rs.getInt("status")));
                obj.setObservacoes(rs.getString("observacoes"));

                lista.add(obj);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listarClientes compras: " + e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection();
        }
        return lista;
    }
}
