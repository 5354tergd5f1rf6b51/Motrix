package br.edu.ifrn.motrix.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifrn.motrix.modelo.Cliente;

public class ClienteRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, telefone, cpf) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setCpf(rs.getString("cpf"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
        return clientes;
    }

    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setCpf(rs.getString("cpf"));
                    return c;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF", e);
        }
        return null;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, cpf = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setLong(4, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente", e);
        }
    }
}