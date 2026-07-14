package br.edu.ifrn.motrix.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifrn.motrix.modelo.Veiculo;

public class VeiculoRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    public void inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, modelo, marca, ano, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getMarca());
            stmt.setInt(4, veiculo.getAno());
            stmt.setLong(5, veiculo.getClienteId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculo.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar veículo", e);
        }
    }

    public List<Veiculo> buscarPorCliente(Long clienteId) {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE cliente_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo v = new Veiculo();
                    v.setId(rs.getLong("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setModelo(rs.getString("modelo"));
                    v.setMarca(rs.getString("marca"));
                    v.setAno(rs.getInt("ano"));
                    v.setClienteId(rs.getLong("cliente_id"));
                    veiculos.add(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículos do cliente", e);
        }
        return veiculos;
    }
}