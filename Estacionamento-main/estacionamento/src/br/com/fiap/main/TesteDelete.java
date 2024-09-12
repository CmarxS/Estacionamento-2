package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.carroDAO;
import br.com.fiap.dto.Carro;

import java.sql.Connection;

public class TesteDelete {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();
        carro.setPlaca("FKK1900");

        carroDAO DAO = new carroDAO(con);
        System.out.println(DAO.excluir(carro));

        ConnectionFactory.fecharConexao(con);
    }
}
