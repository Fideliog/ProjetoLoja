package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    // URL de conexão com o banco de dados
    private static final String URL = 
        "jdbc:mysql://localhost:3306/Lojban?useSSL=false&serverTimezone=UTC";
    // Usuário do banco
    private static final String USER = "root";
    // Senha do banco
    private static final String PASSWORD = "";

    // Objeto de conexão 
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        // Cria nova conexão se não existir ou estiver fechada
        if (connection == null || connection.isClosed()) {
            try {
                // Carrega o driver JDBC do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Conecta ao banco
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                // Exibe mensagem de erro caso ocorra problema
                System.out.println("Driver do MySQL não encontrado.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            // Lança erro se falhar ao fechar a conexão
            throw new RuntimeException("Erro ao desconectar do banco", e);
        }
    }
}
