package org.example.controller;

import org.example.model.Funcionario;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenciaCrud {


    //Inserir no Banco
    public static void inserirRegistro(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        String dataTexto = funcionario.getData(); // Data no formato "dd/MM/yyyy"

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio = LocalDate.parse(dataTexto, formatter);

        try {
            conexao = Conexao.obterConexao();

            String sql = "INSERT INTO  user (nome, user, password, funcao, user_data, tipo_conta, idade, salario) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsername());
            stmt.setString(3, funcionario.getPassword());
            stmt.setString(4, funcionario.getFuncao());
            stmt.setObject(5,dataInicio);
            stmt.setInt(6,funcionario.getTipoConta());
            stmt.setInt(7,funcionario.getIdade());
            stmt.setDouble(8,funcionario.getSalario());

            stmt.executeUpdate();

            System.out.println("Registro inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir registro: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o statement: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
    public static ArrayList<Funcionario> select(String tabela, String query) {
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Funcionario funcionario;
        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a consulta
            String consultaSQL = "SELECT * FROM " + tabela + " " + query;
            statement = conexao.prepareStatement(consultaSQL);

            // Executa a consulta e obtém o ResultSet
            resultSet = statement.executeQuery();
            try {
                while (resultSet.next()) {
                    // Acesse os valores das colunas pelos seus nomes ou índices
                    String nome = resultSet.getString("nome");
                    String cargo = resultSet.getString("funcao");
                    int id = resultSet.getInt("id");
                    int tipoConta = resultSet.getInt("tipo_conta");
                    int idade = resultSet.getInt("idade");
                    double salario = resultSet.getDouble("salario");
                    Date dataInicio = resultSet.getDate("user_data");
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String dataFormatada = formato.format(dataInicio);

                    funcionario = new Funcionario(id,nome,cargo,tipoConta,dataFormatada,idade,salario);
                    funcionarios.add(funcionario);
                    // Exiba os valores na tela
                    /*System.out.println("NOME: " + nome);
                    System.out.println("FUNÇÃO: " + cargo);
                    System.out.println("ID: " + id);
                    System.out.println("-----------------------");*/
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return funcionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return funcionarios;
    }
    public static List<Funcionario> select(String tabela, LocalDate dataInicio, LocalDate dataFim) {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " WHERE user_data BETWEEN ? AND ?";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a consulta
            String consultaSQL = "SELECT * FROM " + tabela + query;
            statement = conexao.prepareStatement(consultaSQL);

            // Define os valores das datas como parâmetros
            statement.setObject(1, dataInicio);
            statement.setObject(2, dataFim);

            // Executa a consulta e obtém o ResultSet
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Funcionario f = new Funcionario();
                f.setId(resultSet.getInt("id"));
                f.setNome(resultSet.getString("nome"));
                f.setData(resultSet.getString("user_data"));
                f.setFuncao(resultSet.getString("funcao"));
                f.setTipoConta(resultSet.getInt("tipo_conta"));
                f.setUsername(resultSet.getString("user"));
                f.setSalario(resultSet.getDouble("salario"));
                f.setIdade(resultSet.getInt("idade"));
                funcionarios.add(f);
            }
            return funcionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static List<Funcionario> select(String tabela, String valor, LocalDate dataInicio, LocalDate dataFim) {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " WHERE "+ valor +" BETWEEN ? AND ?";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a consulta
            String consultaSQL = "SELECT * FROM " + tabela + query;
            statement = conexao.prepareStatement(consultaSQL);

            // Define os valores das datas como parâmetros
            statement.setObject(1, dataInicio);
            statement.setObject(2, dataFim);

            // Executa a consulta e obtém o ResultSet
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Funcionario f = new Funcionario();
                f.setId(resultSet.getInt("id"));
                f.setNome(resultSet.getString("nome"));
                f.setData(resultSet.getString("user_data"));
                f.setFuncao(resultSet.getString("funcao"));
                f.setTipoConta(resultSet.getInt("tipo_conta"));
                f.setUsername(resultSet.getString("user"));
                f.setSalario(resultSet.getDouble("salario"));
                f.setIdade(resultSet.getInt("idade"));
                funcionarios.add(f);
            }
            return funcionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static List<Funcionario> select(String tabela, String valor, int inicio, int fim) {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " WHERE " + valor +" BETWEEN ? AND ?";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a consulta
            String consultaSQL = "SELECT * FROM " + tabela+ query;
            statement = conexao.prepareStatement(consultaSQL);

            // Define os valores das datas como parâmetros
            statement.setInt(1, inicio);
            statement.setInt(2, fim);

            // Executa a consulta e obtém o ResultSet
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Funcionario f = new Funcionario();
                f.setId(resultSet.getInt("id"));
                f.setNome(resultSet.getString("nome"));
                f.setData(resultSet.getString("user_data"));
                f.setFuncao(resultSet.getString("funcao"));
                f.setTipoConta(resultSet.getInt("tipo_conta"));
                f.setUsername(resultSet.getString("user"));
                f.setSalario(resultSet.getDouble("salario"));
                f.setIdade(resultSet.getInt("idade"));
                funcionarios.add(f);
            }
            return funcionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static List<Funcionario> select(String tabela, String valor, double inicio, double fim) {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = " WHERE " + valor +" BETWEEN ? AND ?";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a consulta
            String consultaSQL = "SELECT * FROM " + tabela+ query;
            statement = conexao.prepareStatement(consultaSQL);

            // Define os valores das datas como parâmetros
            statement.setDouble(1, inicio);
            statement.setDouble(2, fim);

            // Executa a consulta e obtém o ResultSet
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Funcionario f = new Funcionario();
                f.setId(resultSet.getInt("id"));
                f.setNome(resultSet.getString("nome"));
                f.setData(resultSet.getString("user_data"));
                f.setFuncao(resultSet.getString("funcao"));
                f.setTipoConta(resultSet.getInt("tipo_conta"));
                f.setUsername(resultSet.getString("user"));
                f.setSalario(resultSet.getDouble("salario"));
                f.setIdade(resultSet.getInt("idade"));
                funcionarios.add(f);
            }
            return funcionarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static boolean update(String updateChange, String whereClause) {
        Connection conexao = null;
        PreparedStatement statement = null;
        String tabela = "user";
        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a atualização
            String atualizacaoSQL = "UPDATE " + tabela + " SET " + updateChange + " WHERE " + whereClause;
            statement = conexao.prepareStatement(atualizacaoSQL);

            // Executa a atualização
            int linhasAfetadas = statement.executeUpdate();

            // Verifica se a atualização foi bem-sucedida
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fecha o PreparedStatement e a conexão
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public static boolean delete(int id) {
        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = Conexao.obterConexao(); // Obtém a conexão usando a classe Conexao

            // Cria o PreparedStatement com a exclusão
            String exclusaoSQL = "DELETE FROM user WHERE id=" + id;
            statement = conexao.prepareStatement(exclusaoSQL);

            // Executa a exclusão
            int linhasAfetadas = statement.executeUpdate();

            // Verifica se a exclusão foi bem-sucedida
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fecha o PreparedStatement e a conexão
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


}
