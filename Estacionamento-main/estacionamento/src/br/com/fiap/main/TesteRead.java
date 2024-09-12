package br.com.fiap.main;


import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.carroDAO;
import br.com.fiap.dto.Carro;

import java.sql.Connection;
import java.util.ArrayList;

public class TesteRead {
    public static void main(String[] args) {
        // Código para testar a leitura de um carro no banco de dados
        Connection con = ConnectionFactory.abrirConexao();

        carroDAO dao = new carroDAO(con);

        ArrayList<Carro> resultado = dao.listarTodos();
        if (resultado != null) {
            for (Carro carro : resultado){
                System.out.println("Placa: " + carro.getPlaca());
                System.out.println("Cor: " + carro.getCor());
                System.out.println("Descrição: " + carro.getDescricao());

            }
        } else {
            System.out.println("Erro ao listar os carros!");
        }

        ConnectionFactory.fecharConexao(con);
    }
}