package br.com.fiap.dao;

import br.com.fiap.dto.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class clienteDAO {
    private Connection con;

    public clienteDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Cliente cli) {
        String sql = "INSERT INTO ddd_cliente VALUES (?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, cli.getIdCliente());
            ps.setString(2, cli.getNomeCliente());
            ps.setString(3, cli.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Cliente cadastrado com sucesso";
            } else {
                return "Cliente não cadastrado";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String alterar(Cliente cli) {
        String sql = "UPDATE ddd_cliente SET nome_cliente=?, placa=? WHERE id_cliente=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(3, cli.getIdCliente());
            ps.setString(1, cli.getNomeCliente());
            ps.setString(2, cli.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Cliente alterado com sucesso";
            } else {
                return "Cliente não alterado";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String excluir(Cliente cli) {
        String sql = "DELETE ddd_cliente WHERE id_cliente=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, cli.getIdCliente());
            if (ps.executeUpdate() > 0) {
                return "Cliente removido com sucesso";
            } else {
                return "Cliente não removido";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cliente> listarTodos() {
        String sql = "SELECT * FROM ddd_cliente ORDER BY id_cliente";
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setNomeCliente(rs.getString(2));
                cliente.setPlaca(rs.getString(3));
                listaCliente.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCliente;
    }
}
