package br.com.fiap.main;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.carroDAO;
import br.com.fiap.dao.clienteDAO;
import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteEstacionamento {
    public static void main(String[] args) {
        int opcao;
        String aux;
        try {
            JOptionPane.showMessageDialog(null, "Bem vindo ao estacionamento");
            do {
                aux = JOptionPane.showInputDialog(null, "Digite o que deseja manipular \n1- Carro " +
                        "\n2- Cliente" + "\n3- Sair");
                opcao = Integer.parseInt(aux);
                switch (opcao) {
                    case 1:
                        manipulaCarro();
                        break;
                    case 2:
                        manipulaCliente();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Saindo do estacionamento");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }
            } while (opcao != 3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular estacionamento");
        }
    }

    public static void manipulaCarro() {
        int opcao;
        String aux;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();
        carroDAO dao = new carroDAO(con);

        try {
                JOptionPane.showMessageDialog(null, "Bem vindo ao Manipulador de Carros");
            do {
                aux = JOptionPane.showInputDialog(null, "Digite o que deseja manipular \n1- Cadastrar carro \n2- Alterar carro \n3- Excluir carro \n4- Listar carros \n5- Sair do manipulador de carros");
                opcao = Integer.parseInt(aux);
                switch (opcao) {
                    case 1:
                        carro.setPlaca(JOptionPane.showInputDialog("Insira a placa"));
                        carro.setCor(JOptionPane.showInputDialog("Insira a cor"));
                        carro.setDescricao(JOptionPane.showInputDialog("Insira a descrição"));
                        JOptionPane.showMessageDialog(null, dao.inserir(carro));
                        break;
                    case 2:
                        carro.setPlaca(JOptionPane.showInputDialog("Insira a placa que deseja alterar"));
                        carro.setCor(JOptionPane.showInputDialog("Insira a nova cor"));
                        carro.setDescricao(JOptionPane.showInputDialog("Insira a nova descrição"));
                        JOptionPane.showMessageDialog(null, dao.alterar(carro));
                        break;
                    case 3:
                        carro.setPlaca(JOptionPane.showInputDialog("Insira a placa que deseja excluir"));
                        JOptionPane.showMessageDialog(null, dao.excluir(carro));
                        break;
                    case 4:
                        ArrayList<Carro> resultado = dao.listarTodos();
                        if (resultado != null) {
                            String carrosInfo = "";
                            for (Carro carros : resultado) {
                                carrosInfo +=
                                        "Placa: " + carros.getPlaca() +
                                                " Cor: " + carros.getCor() +
                                                " Descrição: " + carros.getDescricao() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, carrosInfo);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao listar os carros!");
                        }
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Saindo do manipulador de carros");
                        break;
                }
            } while (opcao != 5);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular carro");
        }

        ConnectionFactory.fecharConexao(con);
    }

    public static void manipulaCliente() {
        int opcao;
        String aux;
        Connection con = ConnectionFactory.abrirConexao();
        Cliente cliente = new Cliente();
        clienteDAO dao = new clienteDAO(con);
        try {
            JOptionPane.showMessageDialog(null, "Bem vindo ao Manipulador de Clientes");
            do {
                aux = JOptionPane.showInputDialog(null, "Digite o que deseja manipular \n1- Cadastrar cliente \n2- Alterar cliente \n3- Excluir cliente \n4- Listar clientes \n5- Sair do manipulador de clientes");
                opcao = Integer.parseInt(aux);
                switch (opcao) {
                    case 1:
                        cliente.setIdCliente(Integer.parseInt(JOptionPane.showInputDialog("Insira o id")));
                        cliente.setNomeCliente(JOptionPane.showInputDialog("Insira o nome"));
                        cliente.setPlaca(JOptionPane.showInputDialog("Insira a placa"));
                        JOptionPane.showMessageDialog(null, dao.inserir(cliente));
                        break;
                    case 2:
                        cliente.setIdCliente(Integer.parseInt(JOptionPane.showInputDialog("Insira o id do cliente que deseja alterar")));
                        cliente.setNomeCliente(JOptionPane.showInputDialog("Insira o novo nome"));
                        cliente.setPlaca(JOptionPane.showInputDialog("Insira a nova placa"));
                        JOptionPane.showMessageDialog(null, dao.alterar(cliente));
                        break;
                    case 3:
                        cliente.setIdCliente(Integer.parseInt(JOptionPane.showInputDialog("Insira o id do cliente que deseja excluir")));
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
                        case 5:
                            JOptionPane.showMessageDialog(null, "Saindo do manipulador de clientes");
                            break;
                }
            } while (opcao != 5);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao manipular cliente");
        }
        ConnectionFactory.fecharConexao(con);
    }
}
