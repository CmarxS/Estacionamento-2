package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.clienteDAO;
import br.com.fiap.dto.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteClienteCRUD {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionFactory.abrirConexao();
            int opcao, id;
            String aux, placa, nome;
            Cliente cliente = new Cliente();
            clienteDAO dao = new clienteDAO(con);
            do {
                aux = JOptionPane.showInputDialog("Digite o que deseja fazer \n1- Add cliente \n2- Alterar cliente \n3- Deletar cliente \n4- Listar clientes");
                opcao = Integer.parseInt(aux);
                switch (opcao) {
                    case 1:
                        aux = JOptionPane.showInputDialog("Insira o id");
                        id = Integer.parseInt(aux);
                        cliente.setIdCliente(id);
                        nome = JOptionPane.showInputDialog("Insira o nome");
                        cliente.setNomeCliente(nome);
                        placa = JOptionPane.showInputDialog("Insira a placa");
                        cliente.setPlaca(placa);
                        String resultadoInsercao = dao.inserir(cliente);
                        System.out.println(resultadoInsercao);
                        JOptionPane.showMessageDialog(null, resultadoInsercao);
                        break;
                    case 2:
                        aux = JOptionPane.showInputDialog("Insira o id do cliente que quer alterar");
                        id = Integer.parseInt(aux);
                        cliente.setIdCliente(id);
                        nome = JOptionPane.showInputDialog("Insira o novo nome");
                        cliente.setNomeCliente(nome);
                        placa = JOptionPane.showInputDialog("Insira a nova placa");
                        cliente.setPlaca(placa);

                        JOptionPane.showMessageDialog(null, dao.alterar(cliente));
                        break;
                    case 3:
                        aux = JOptionPane.showInputDialog("Insira o id");
                        id = Integer.parseInt(aux);
                        cliente.setIdCliente(id);
                        JOptionPane.showMessageDialog(null, dao.excluir(cliente));
                        break;
                    case 4:
                        ArrayList<Cliente> resultado = dao.listarTodos();
                        if (resultado != null && !resultado.isEmpty()) {
                            String clientesInfo = "";
                            for (Cliente clientes : resultado) {
                                clientesInfo +=
                                        "ID: " + clientes.getIdCliente() +
                                        " Nome: " + clientes.getNomeCliente() +
                                        " Placa: " + clientes.getPlaca() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, clientesInfo);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado.");
                        }
                        break;
                }

            } while (opcao != 5);
            JOptionPane.showMessageDialog(null, "Fim do programa");

            ConnectionFactory.fecharConexao(con);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage());
        }
    }
}