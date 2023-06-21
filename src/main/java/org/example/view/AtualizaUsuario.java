package org.example.view;

import org.example.controller.GerenciaCrud;
import org.example.model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AtualizaUsuario {
    private final JFrame frame = new JFrame("Atualização de funcionário");
    private static JLabel usernameLabel;
    private static JTextField usernameField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JLabel nomeLabel;
    private static JTextField nomeField;
    private static JLabel idadeLabel;
    private static JTextField idadeField;
    private static JLabel cargoLabel;
    private static JTextField cargoField;
    private static JLabel dataLabel;
    private static JTextField dataField;
    private static JLabel tipoContaLabel;
    private static JTextField tipoContaField;
    private static JLabel salarioLabel;
    private static JTextField salarioField;
    private static JButton botaoAtualizar;
    private static int id;
    public AtualizaUsuario(int id) {
        this.id = id;
    }

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
        botaoAtualizar = new JButton("Atualizar");

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

        botaoAtualizar.setBounds(180, 450, 100, 30);
        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getText());
                String cargo = cargoField.getText();
                String data = dataField.getText();
                int idadeFormat =!(idadeField.getText().isEmpty()) ? Integer.parseInt(idadeField.getText()) : 0;
                int idade =  idadeFormat < 0 ? idadeFormat : 0;
                int tipoContaFormat = !(tipoContaField.getText().isEmpty()) ? Integer.parseInt(tipoContaField.getText()): 0 ;
                int tipoConta =  tipoContaFormat < 0 ? tipoContaFormat : 0;
                double salarioFormat = !(salarioField.getText().isEmpty()) ? Double.parseDouble(salarioField.getText()) : 0;
                double salario = salarioFormat < 0 ? salarioFormat : 0;
                Funcionario f = new Funcionario(nome, cargo, username, password, tipoConta, data, idade,salario);
                String updateChange = updateSet(f);
                try{
                    GerenciaCrud.update(updateChange,"id="+id);
                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso");

                    TelaPrincipal telaPrincipal = new TelaPrincipal(1, GerenciaCrud.select("user", ""));
                    telaPrincipal.run();
                    frame.dispose();
                }catch (Exception ex) {
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
        frame.add(botaoAtualizar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public void run(){
        displayWindow();
    }

    public String updateSet(Funcionario f){
        String query = "";
        if(!f.getNome().isEmpty()){
            query += "nome = '" +f.getNome()+ "' ";
        }
        if(f.getIdade() != 0){
            if(query.isEmpty()){
                query += "idade = " +f.getIdade();
            }else{
                query += ",idade = " +f.getIdade();
            }

        }
        if(f.getSalario() != 0){
            if(query.isEmpty()){
                query += "salario = " +f.getSalario();
            }else{
                query += ",salario = " +f.getSalario();
            }

        }
        if(!f.getUsername().isEmpty()){
            if(query.isEmpty()){
                query += "user = '" +f.getUsername()+ "' ";
            }else{
                query += ",user = '" +f.getUsername()+ "' ";
            }

        }
        if(!f.getPassword().isEmpty()){
            if(query.isEmpty()){
                query += "password = '" +f.getPassword()+ "' ";
            }else{
                query += ",password = '" +f.getPassword()+ "' ";
            }

        }
        if(!f.getFuncao().isEmpty()){
            if(query.isEmpty()){
                query += "funcao = '" +f.getFuncao()+ "' ";
            }else{
                query += ",funcao = '" +f.getFuncao()+ "' ";
            }

        }
        if(!f.getData().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataInicio = LocalDate.parse(f.getData(), formatter);
            if(query.isEmpty()){
                query += "user_data = '" + dataInicio+ "' ";
            }else{
                query += ",user_data = '" + dataInicio+ "' ";
            }

        }
        if(!(f.getTipoConta() == 0) && f.getTipoConta() > 0 && f.getTipoConta() <= 2){
            if(query.isEmpty()){
                query+= "tipo_conta = " + f.getTipoConta();
            }else{
                query+= ",tipo_conta = " + f.getTipoConta();
            }
        }
        return query;
    }
}
