package br.edu.ifrn.motrix.modelo;

public class Veiculo {
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private Long clienteId; // Chave estrangeira ligando ao Cliente

    public Veiculo() {}

    public Veiculo(String placa, String modelo, String marca, int ano, Long clienteId) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.clienteId = clienteId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    @Override
    public String toString() {
        return "Veiculo{id=" + id + ", placa='" + placa + "', modelo='" + modelo + 
               "', marca='" + marca + "', ano=" + ano + ", clienteId=" + clienteId + "}";
    }
}