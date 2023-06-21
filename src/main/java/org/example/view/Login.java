package org.example.view;

import org.example.controller.Conexao;
import org.example.controller.GerenciaCrud;
import org.example.model.Funcionario;

import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {

    private static boolean loggedIn;//Varíavel para checar se o login foi feito

    private static Connection connection;//Variável que guarda a conexão com o banco

    private static int loggedUserColumnValue; // Valor da coluna do usuário logado

    private static JFrame loginFrame;
    private static JLabel usernameLabel;
    private static JTextField usernameField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static ResultSet resultado;

    //Tela do login, futuramente terá a implementação da interface gráfica aqui
    public static void displayLoginScreen() {
        loginFrame = new JFrame("Tela de Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        Dimension tamanho = new Dimension(500, 400); // Define o tamanho desejado

        loginFrame.setPreferredSize(tamanho); // Define o tamanho preferido da janela

        usernameLabel = new JLabel("Nome de usuário:");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        usernameLabel.setBounds(110, 70, 200, 25);
        usernameField.setBounds(140, 100, 200, 25);

        passwordLabel.setBounds(110, 130, 80, 25);
        passwordField.setBounds(140, 160, 200, 25);


        loginButton.setBounds(180, 200, 100, 30);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                authenticate(username, password);

                if (loggedIn) {
                    int tipoConta = getTipoConta();
                    JOptionPane.showMessageDialog(null, "Bem-vindo, " + username + "! Acesso concedido.");

                    TelaPrincipal telaPrincipal = new TelaPrincipal(tipoConta, GerenciaCrud.select("user",""));
                    loginFrame.dispose();
                    telaPrincipal.run();
                } else {
                    JOptionPane.showMessageDialog(null, "Desculpe, você não tem acesso.");
                }
            }
        });

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel());
        loginFrame.add(loginButton);

        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }

    //Autenticação do Login
    public static void authenticate(String username, String password) {
        try
        {
            connection = Conexao.obterConexao();
            String tabela = "user";//Tabelas do banco

            String query = "SELECT * FROM "+ tabela +" WHERE user = ? AND password = ?";//Querys que serão feitas
            PreparedStatement statement = connection.prepareStatement(query);//usa a conexão e prepara a Query
            statement.setString(1, username);//Substitui o '?' pelos parametros da função
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();//executa a query e salva no resultSet
            if (resultSet.next()) {//Checa se a query retornou algum valor
                loggedIn = true;
                System.out.println("Login bem-sucedido!");
                loggedUserColumnValue = resultSet.getInt("tipo_conta"); // pega o tipo de conta

            }

            if (!loggedIn) {
                JOptionPane.showMessageDialog(null, "Usuário e senha não existem");
            }
        } catch (SQLException e) {
            e.printStackTrace();//Exceção caso algum erro SQL ocorra
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Obtém o valor de uma coluna do usuário logado
    public static int getTipoConta() {
        return loggedUserColumnValue;
    }

    //Roda os códigos de login
    public static void run() {
        displayLoginScreen();

    }



}