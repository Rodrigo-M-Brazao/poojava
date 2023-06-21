package org.example.controller;

import java.sql.*;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/banco_java";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    public static Connection obterConexao() throws Exception {
        try {

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new Exception("Driver do MySQL não encontrado");
        }
    }

}
