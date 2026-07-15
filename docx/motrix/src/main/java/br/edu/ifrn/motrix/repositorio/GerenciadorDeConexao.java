package br.edu.ifrn.motrix.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de Infraestrutura responsável pela gerência da conexão com o serviço MySQL.
 */
public class GerenciadorDeConexao {

    // URL apontando diretamente para o banco de dados da sua oficina chamado motrix_db
    private static final String URL = "jdbc:mysql://localhost:3306/motrix_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";  
    private static final String PASSWORD = "Juan2008"; 

    /**
     * Abre e retorna uma conexão ativa com o banco de dados.
     * @return java.sql.Connection
     * @throws SQLException Caso o serviço do MySQL esteja desligado ou as credenciais estejam erradas
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}