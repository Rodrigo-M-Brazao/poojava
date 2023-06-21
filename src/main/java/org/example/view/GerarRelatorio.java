package org.example.view;

import org.example.controller.GerenciaCrud;
import org.example.model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GerarRelatorio {
    private static final JFrame frame = new JFrame("Gerar relatório");
    private static JLabel filtroLabel;
    private static JTextField filtroField;
    private static JLabel inicioLabel;
    private static JTextField inicioField;
    private static JLabel fimLabel;
    private static JTextField fimField;
    private static JButton botaoGerar;
    public static void displayWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2));

        filtroLabel = new JLabel("Valor a ser filtrado:");
        filtroField = new JTextField();
        inicioLabel = new JLabel("Valor inicial:");
        inicioField = new JTextField();
        fimLabel = new JLabel("Valor final:");
        fimField = new JTextField();
        botaoGerar = new JButton("Gerar relatório");
        botaoGerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorFiltro = filtroField.getText().toLowerCase();
                try{
                    if(valorFiltro.equals("idade")){
                        int inicio = Integer.parseInt(inicioField.getText());
                        int fim = Integer.parseInt(fimField.getText());
                        Relatorio.imprimir(valorFiltro, inicio, fim);
                        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso");
                        frame.dispose();
                    }else if(valorFiltro.equals("data")){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate inicio = LocalDate.parse(inicioField.getText(), formatter);
                        LocalDate fim = LocalDate.parse(fimField.getText(), formatter);
                        Relatorio.imprimir(valorFiltro, inicio, fim);
                        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso");
                        frame.dispose();
                    }else if(valorFiltro.equals("salario")){
                        double inicio = Double.parseDouble(inicioField.getText());
                        double fim = Double.parseDouble(fimField.getText());
                        Relatorio.imprimir(valorFiltro, inicio, fim);
                        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso");
                        frame.dispose();
                    }else if(valorFiltro.isEmpty()){
                        Relatorio.imprimir();
                        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso");
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Valor inválido no relatório");
                    }

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir usuário");
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.add(filtroLabel);
        frame.add(filtroField);
        frame.add(inicioLabel);
        frame.add(inicioField);
        frame.add(fimLabel);
        frame.add(fimField);
        frame.add(new JLabel());
        frame.add(botaoGerar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void run(){
        displayWindow();
    }
}