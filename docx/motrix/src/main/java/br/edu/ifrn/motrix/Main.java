package br.edu.ifrn.motrix;

import br.edu.ifrn.motrix.modelo.Cliente;
import br.edu.ifrn.motrix.modelo.Veiculo;
import br.edu.ifrn.motrix.servico.ClienteService;
import br.edu.ifrn.motrix.servico.VeiculoService;

public class Main {
    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        VeiculoService veiculoService = new VeiculoService();

        try {
            System.out.println("\n--- [C] - CADASTRANDO Clientes no MySQL (Motrix) ---");
            Cliente cliente1 = new Cliente("João Silva", "(84) 99999-1111", "111.222.333-44");
            Cliente cliente2 = new Cliente("Maria Oliveira", "(84) 98888-2222", "555.666.777-88");

            clienteService.cadastrarCliente(cliente1);
            clienteService.cadastrarCliente(cliente2);
            System.out.println("Clientes salvos com sucesso!");

            System.out.println("\n--- [C] - VINCULANDO Veículos aos Clientes (RF.002) ---");
            // Vinculando um Uno ao João (cliente1)
            Veiculo veiculo1 = new Veiculo("ABC1D23", "Uno Mille", "Fiat", 2012, cliente1.getId());
            // Vinculando um Corolla à Maria (cliente2)
            Veiculo veiculo2 = new Veiculo("MXT-4567", "Corolla", "Toyota", 2020, cliente2.getId());

            veiculoService.vincularVeiculo(veiculo1);
            veiculoService.vincularVeiculo(veiculo2);
            System.out.println("Veículos vinculados com sucesso!");

            System.out.println("\n--- [R] - LISTANDO Clientes Cadastrados ---");
            clienteService.listarClientes().forEach(System.out::println);

            System.out.println("\n--- [R] - CONSULTANDO Veículos do Cliente 'João Silva' ---");
            veiculoService.listarVeiculosDoCliente(cliente1.getId()).forEach(System.out::println);

            System.out.println("\n--- [U] - ATUALIZANDO Dados de um Cliente ---");
            cliente2.setTelefone("(84) 91111-2222"); // Mudou o número de telefone
            clienteService.atualizarCliente(cliente2);
            System.out.println("Dados atualizados de: " + cliente2.getNome());

            System.out.println("\n--- [D] - EXCLUINDO um Cliente (Cascata apagará o veículo dele) ---");
            clienteService.excluirCliente(cliente1.getId());
            System.out.println("Cliente João Silva removido do sistema.");

            System.out.println("\n--- Estado Final dos Clientes no Banco ---");
            clienteService.listarClientes().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro no fluxo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}