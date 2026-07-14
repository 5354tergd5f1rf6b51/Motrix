package br.edu.ifrn.motrix.servico;
import java.util.List;
import br.edu.ifrn.motrix.modelo.Cliente;
import br.edu.ifrn.motrix.repositorio.ClienteRepositorio;

public class ClienteService {
    private final ClienteRepositorio repositorio = new ClienteRepositorio();

    public void cadastrarCliente(Cliente cliente) {
        // Valida campos obrigatórios
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty() ||
            cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty() ||
            cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Todos os campos (Nome, Telefone, CPF) são obrigatórios.");
        }

        // Evita cadastro duplicado pelo CPF
        if (repositorio.buscarPorCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("Erro: Já existe um cliente cadastrado com este CPF.");
        }

        repositorio.inserir(cliente);
    }

    public List<Cliente> listarClientes() {
        return repositorio.buscarTodos();
    }

    public void atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("Erro: ID do cliente inválido para atualização.");
        }
        repositorio.atualizar(cliente);
    }

    public void excluirCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro: ID inválido.");
        }
        repositorio.excluir(id);
    }
}