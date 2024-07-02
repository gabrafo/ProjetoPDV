package dev.gabrafo.projetovendas.dao;

import dev.gabrafo.projetovendas.jdbc.ConnectionFactory;
import dev.gabrafo.projetovendas.jdbc.DBException;
import dev.gabrafo.projetovendas.model.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn = null;
    private PreparedStatement st = null;

    public ClienteDAO(){
        this.conn = ConnectionFactory.getConnection();
    }

    public void cadastrarCliente(Cliente obj) {
        try {
            st = conn.prepareStatement("INSERT INTO tb_clientes(nome, celular, cep, endereco," +
                    "numero, bairro, complemento, cidade) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, obj.getNome());
            st.setString(2, obj.getCelular());
            st.setString(3, obj.getCep());
            st.setString(4, obj.getEndereco());
            st.setInt(5, obj.getNumero());
            st.setString(6, obj.getBairro());
            st.setString(7, obj.getComplemento());
            st.setString(8, obj.getCidade());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public void alterarCliente(Cliente obj) {
        try {
            st = conn.prepareStatement("UPDATE tb_clientes SET nome=?, celular=?, cep=?, endereco=?," +
                    "numero=?, bairro=?, complemento=?, cidade=? WHERE id=?");
            st.setString(1, obj.getNome());
            st.setString(2, obj.getCelular());
            st.setString(3, obj.getCep());
            st.setString(4, obj.getEndereco());
            st.setInt(5, obj.getNumero());
            st.setString(6, obj.getBairro());
            st.setString(7, obj.getComplemento());
            st.setString(8, obj.getCidade());
            st.setInt(9, obj.getId());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluirCliente(Cliente obj) {
        try {
            st = conn.prepareStatement("DELETE FROM tb_clientes WHERE id = ?");
            st.setInt(1, obj.getId());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodosOsClientes(){
        PreparedStatement st = null; // Consulta SQL
        ResultSet rs = null; // Tem forma de tabela
        List<Cliente> lista = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConnection();

            st = conn.prepareStatement("SELECT * FROM tb_clientes");
            rs = st.executeQuery();

            while (rs.next()){ // rs.next() retorna falso caso não exista mais nenhuma posição
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setCidade(rs.getString("cidade"));

                lista.add(obj);
            }

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return lista;
    }

    public List<Cliente> listarClientePorNome(String nome) {
        PreparedStatement st = null; // Consulta SQL
        ResultSet rs = null; // Tem forma de tabela
        List<Cliente> lista = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConnection();

            st = conn.prepareStatement("SELECT * FROM tb_clientes WHERE nome LIKE ?");
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            while (rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setCidade(rs.getString("cidade"));

                lista.add(obj);
            }

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return lista;
    }
    
    public Cliente buscarClientePorNome(String nome) {
        // É diferente do listarClientePorNome
        // Sua função é buscar APENAS UM resultado e mostrar no painel de dados pessoais
        // Nome tem que estar certo
        
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.getConnection();

            st = conn.prepareStatement("SELECT * FROM tb_clientes WHERE nome LIKE ?");
            st.setString(1, nome);
            rs = st.executeQuery();

            if (rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setCidade(rs.getString("cidade"));
                return obj;
            }
        } catch(Exception e ){
            throw new DBException(e.getMessage());
        }
        return null;
    }
}
