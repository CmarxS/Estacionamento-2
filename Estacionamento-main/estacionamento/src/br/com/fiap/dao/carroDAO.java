package br.com.fiap.dao;

import br.com.fiap.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class carroDAO {
    private Connection con;

    public carroDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Carro carro) {
        // Código para inserir um carro no banco de dados
        String sql = "INSERT INTO DDD_CARRO (PLACA, COR, DESCRICAO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            if (ps.executeUpdate() > 0) {
                return "Carro inserido com sucesso!";
            } else {
                return "Erro ao inserir o carro no banco de dados!";
            }
        } catch (SQLException e) {
            return "Erro de SQL!\n" + e.getMessage();
        }
    }

    public String alterar(Carro carro) {
        // Código para alterar um carro no banco de dados
        String sql = "UPDATE ddd_carro SET COR=?, DESCRICAO=? WHERE PLACA=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            ps.setString(3, carro.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Carro alterado com sucesso!";
            } else {
                return "Erro ao alterar o carro no banco de dados!";
            }
        } catch (SQLException e) {
            return "Erro de SQL!\n" + e.getMessage();
        }

    }

    public String excluir(Carro carro) {
        // Código para alterar um carro no banco de dados
        String sql = "DElETE FROM ddd_carro WHERE PLACA=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, carro.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Carro alterado com sucesso!";
            } else {
                return "Erro ao alterar o carro no banco de dados!";
            }
        } catch (SQLException e) {
            return "Erro de SQL!\n" + e.getMessage();
        }
    }

    public ArrayList<Carro> listarTodos() {
        // Código para listar todos os carros do banco de dados
        String sql = "SELECT * FROM ddd_carro ORDER BY PLACA";
        ArrayList<Carro> listaCarro = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Carro carro = new Carro();
                    carro.setPlaca(rs.getString(1));
                    carro.setCor(rs.getString(2));
                    carro.setDescricao(rs.getString(3));
                    listaCarro.add(carro);
                }
                return listaCarro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public Carro listarUm(Carro carro) {
        // Código para listar um carro do banco de dados
        String sql = "SELECT * FROM ddd_carro WHERE PLACA=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, carro.getPlaca());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carro.setPlaca(rs.getString(1));
                carro.setCor(rs.getString(2));
                carro.setDescricao(rs.getString(3));
                return carro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL!\n" + e.getMessage());
            return null;
        }
    }
}
