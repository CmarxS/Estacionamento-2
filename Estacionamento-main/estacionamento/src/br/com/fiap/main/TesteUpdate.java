package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.carroDAO;
import br.com.fiap.dto.Carro;

import java.sql.Connection;

public class TesteUpdate {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();

        Carro carro = new Carro();
        carro.setPlaca("FKK1900");
        carro.setCor("Carro");
        carro.setDescricao("Nissan March");

        carroDAO DAO = new carroDAO(con);
        System.out.println(DAO.alterar(carro));


        ConnectionFactory.fecharConexao(con);
    }

}
