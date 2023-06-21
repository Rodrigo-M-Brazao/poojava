package org.example.view;

import org.example.controller.GerenciaCrud;
import org.example.model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuario {
    private final JFrame frame = new JFrame("Cadastro de funcionário");
    private static JLabel usernameLabel;
    private static JTextField usernameField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JLabel nomeLabel;
    private static JTextField nomeField;
    private static JLabel  idadeLabel;
    private static JTextField idadeField;
    private static JLabel cargoLabel;
    private static JTextField cargoField;
    private static JLabel dataLabel;
    private static JTextField dataField;
    private static JLabel tipoContaLabel;
    private static JTextField tipoContaField;
    private static JLabel salarioLabel;
    private static JTextField salarioField;
    private static JButton botaoCadastrar;
    public void displayWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        Dimension tamanho = new Dimension(500, 600); // Define o tamanho desejado

        frame.setPreferredSize(tamanho); // Define o tamanho preferido da janela
        usernameLabel = new JLabel("Nome de usuário:");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();
        cargoLabel = new JLabel("Cargo:");
        cargoField = new JTextField();
        salarioLabel = new JLabel("Salario:");
        salarioField = new JTextField();
        dataLabel = new JLabel("Data de início:");
        dataField = new JTextField();
        tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaField = new JTextField();
        botaoCadastrar = new JButton("Cadastrar");

        usernameLabel.setBounds(70, 30, 200,25);
        usernameField.setBounds(180,30,200,25);

        passwordLabel.setBounds(70, 80, 200, 25);
        passwordField.setBounds(180, 80, 200, 25);

        nomeLabel.setBounds(70, 130, 80, 25);
        nomeField.setBounds(180, 130, 200, 25);

        idadeLabel.setBounds(70, 180, 80, 25);
        idadeField.setBounds(180, 180, 200, 25);

        cargoLabel.setBounds(70, 230, 80, 25);
        cargoField.setBounds(180, 230, 200, 25);

        dataLabel.setBounds(70, 280, 80, 25);
        dataField.setBounds(180, 280, 200, 25);

        tipoContaLabel.setBounds(70, 330, 80, 25);
        tipoContaField.setBounds(180, 330, 200, 25);

        salarioLabel.setBounds(70, 380, 80, 25);
        salarioField.setBounds(180, 380, 200, 25);

        botaoCadastrar.setBounds(180, 450, 100, 30);
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getText());
                String cargo = cargoField.getText();
                String data = dataField.getText();
                int tipoConta = Integer.parseInt(tipoContaField.getText());
                int idade = Integer.parseInt(idadeField.getText());
                double salario = Double.parseDouble(salarioField.getText());
                Funcionario f = new Funcionario(nome, cargo, username, password, tipoConta, data,idade,salario);
                try{
                    GerenciaCrud.inserirRegistro(f);
                    JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso");

                    TelaPrincipal telaPrincipal = new TelaPrincipal(1, GerenciaCrud.select("user", ""));
                    telaPrincipal.run();
                    frame.dispose();
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir usuário");
                    throw new RuntimeException(ex);
                }


            }
        });


        frame.add(nomeLabel);
        frame.add(nomeField);
        frame.add(idadeLabel);
        frame.add(idadeField);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(cargoLabel);
        frame.add(cargoField);
        frame.add(dataLabel);
        frame.add(dataField);
        frame.add(tipoContaLabel);
        frame.add(tipoContaField);
        frame.add(salarioLabel);
        frame.add(salarioField);

        frame.add(new JLabel());
        frame.add(botaoCadastrar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public void run(){
        displayWindow();
    }

}
