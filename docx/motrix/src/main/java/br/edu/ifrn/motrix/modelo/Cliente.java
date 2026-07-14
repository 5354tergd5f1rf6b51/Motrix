package br.edu.ifrn.motrix.modelo;

public class Cliente {
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;

    public Cliente() {}

    public Cliente(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + telefone + "', cpf='" + cpf + "'}";
    }
}