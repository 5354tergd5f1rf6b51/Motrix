package br.edu.ifrn.motrix.servico;

import java.util.List;
import br.edu.ifrn.motrix.modelo.Veiculo;
import br.edu.ifrn.motrix.repositorio.VeiculoRepositorio;

public class VeiculoService {
    private final VeiculoRepositorio repositorio = new VeiculoRepositorio();

    public void vincularVeiculo(Veiculo veiculo) {
        // Valida se a placa respeita os padrões (Ex: Antigo "AAA-1234" ou Mercosul "AAA1A23")
        String regexPlaca = "^[A-Z]{3}-?[0-9][A-Z0-9][0-9]{2}$";
        if (veiculo.getPlaca() == null || !veiculo.getPlaca().toUpperCase().matches(regexPlaca)) {
            throw new IllegalArgumentException("Erro: Placa inválida! Use o formato ABC1D23 ou ABC-1234.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty() ||
            veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty() ||
            veiculo.getAno() <= 0 || veiculo.getClienteId() == null) {
            throw new IllegalArgumentException("Erro: Dados do veículo ou vínculo com o cliente incompletos.");
        }

        // CORRIGIDO: de getPlaka() para getPlaca()
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase()); 
        repositorio.inserir(veiculo);
    }

    public List<Veiculo> listarVeiculosDoCliente(Long clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("Erro: ID do cliente inválido.");
        }
        return repositorio.buscarPorCliente(clienteId);
    }
}