package org.example.view;

import org.example.controller.GerenciaCrud;
import org.example.model.Administrador;
import org.example.model.Atendente;
import org.example.model.Funcionario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {

    private Funcionario funcionario;
    private ResultSet resultado;
    private boolean dataOrderAscending = true;
    private boolean nomeOrderAscending = true;
    private boolean tipoContaFiltro = true;
    private ArrayList<Funcionario> funcionarios;
    private String[] columnNames = {"ID", "Nome","Idade", "Cargo","Salario", "Data de início"};
    private DefaultTableModel model = new DefaultTableModel(columnNames, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Todas as células são somente leitura
        }
    };

    private Object selectedValue;

    public TelaPrincipal(int tipoConta, ArrayList<Funcionario> funcionarios) {
        if (tipoConta == 1) {
            funcionario = new Administrador();
        } else {
            funcionario = new Atendente();
        }
        this.funcionarios = funcionarios;


    }

    public void displayMainScreen() {

        try {
            for(Funcionario f : funcionarios){
                model.addRow(new Object[]{f.getId(), f.getNome(), f.getIdade(), f.getFuncao(),"R$ " + f.getSalarioFormatado(), f.getData()});
            }
            JFrame frame = new JFrame("Tela Principal");


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension tamanho = new Dimension(700, 600); // Define o tamanho desejado
            frame.setPreferredSize(tamanho);  // Maximiza a janela

            frame.setLayout(new FlowLayout());



            JTable table = new JTable(model);

            JButton btnOrdenarNome = new JButton("Ordenar por Nome");
            JButton btnOrdenarData = new JButton("Ordenar por data");
            JButton botaoInserir = new JButton("Adicionar funcionário");
            JButton botaoAtualizar = new JButton("Atualizar funcionário");
            JButton botaoExcluir = new JButton("Excluir funcionário");
            JButton btnRelatorio = new JButton("Gerar relatório");
            JButton btnFiltroTipoConta = new JButton("Filtrar contas por tipo");

            btnOrdenarNome.setVisible(true);
            btnOrdenarData.setVisible(true);
            btnFiltroTipoConta.setVisible(true);
            if(funcionario instanceof Atendente){

                botaoInserir.setVisible(false);
                botaoExcluir.setVisible(false);
                botaoAtualizar.setVisible(false);
                btnRelatorio.setVisible(false);
            }


            JScrollPane scrollPane = new JScrollPane(table);

            // Adiciona o painel de rolagem ao painel principal
            JPanel panel = new JPanel();
            panel.add(scrollPane);
            frame.add(panel);

            frame.add(botaoInserir);
            frame.add(botaoAtualizar);
            frame.add(botaoExcluir);
            frame.add(btnRelatorio);
            frame.add(btnOrdenarNome);
            frame.add(btnOrdenarData);
            frame.add(btnFiltroTipoConta);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) { // Verifica se a seleção foi finalizada
                        int selectedRow = table.getSelectedRow(); // Índice da linha selecionada
                        // Valor da primeira coluna na linha selecionada
                        selectedValue = table.getValueAt(selectedRow, 0);



                    }
                }
            });
            botaoExcluir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selectedValue != null) {
                        int id = (int) selectedValue;
                        GerenciaCrud.delete(id);
                        frame.dispose();
                        String msg = "Funcionário deletado ID: " + id;
                        JOptionPane.showMessageDialog(null, msg);
                        TelaPrincipal t = new TelaPrincipal(1,GerenciaCrud.select("user",""));
                        t.run();

                    }else{
                        JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado.");

                    }


                }
            });

            botaoInserir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    CadastroUsuario c = new CadastroUsuario();
                    c.run();

                }
            });
            botaoAtualizar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selectedValue != null) {
                        int id = (int) selectedValue;
                        frame.dispose();
                        AtualizaUsuario att = new AtualizaUsuario(id);
                        att.run();
                    }else{
                        JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado.");

                    }

                }
            });
            btnRelatorio.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        GerarRelatorio.run();
                    }catch (RuntimeException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });
            btnOrdenarData.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Altera a ordem de ordenação por data
                    dataOrderAscending = !dataOrderAscending;

                    // Obtém a ordem atual como string ("ASC" para ascendente, "DESC" para descendente)
                    String order = dataOrderAscending ? "ASC" : "DESC";

                    // Realiza a ordenação por nome no banco de dados (ou na fonte de dados atual)
                    ArrayList<Funcionario> funcionariosOrdenados = GerenciaCrud.select("user", order.isEmpty() ? "" : "ORDER BY user_data " + order);


                    // Limpa o modelo da tabela
                    model.setRowCount(0);

                    // Preenche o modelo da tabela com os dados ordenados
                    for (Funcionario f : funcionariosOrdenados) {
                        model.addRow(new Object[]{f.getId(), f.getNome(), f.getIdade(), f.getFuncao(),"R$ " + f.getSalarioFormatado(), f.getData()});
                    }

                }
            });
            btnOrdenarNome.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Altera a ordem de ordenação por nome
                    nomeOrderAscending = !nomeOrderAscending;

                    // Obtém a ordem atual como string ("ASC" para ascendente, "DESC" para descendente)
                    String order = nomeOrderAscending ? "ASC" : "DESC";

                    // Realiza a ordenação por nome no banco de dados (ou na fonte de dados atual)
                    ArrayList<Funcionario> funcionariosOrdenados = GerenciaCrud.select("user", order.isEmpty() ? "" : "ORDER BY nome " + order);


                    // Limpa o modelo da tabela
                    model.setRowCount(0);

                    // Preenche o modelo da tabela com os dados ordenados
                    for (Funcionario f : funcionariosOrdenados) {
                        model.addRow(new Object[]{f.getId(), f.getNome(), f.getIdade(), f.getFuncao(),"R$ " + f.getSalarioFormatado(), f.getData()});
                    }




                }
            });
            btnFiltroTipoConta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Altera a ordem de ordenação por nome
                    tipoContaFiltro = !tipoContaFiltro;

                    // Filtra por tipo de conta
                    String order = tipoContaFiltro ? "tipo_conta = 1" : "tipo_conta = 2";

                    // Realiza a ordenação por nome no banco de dados (ou na fonte de dados atual)
                    ArrayList<Funcionario> funcionariosOrdenados = GerenciaCrud.select("user", order.isEmpty() ? "" : "WHERE " + order);


                    // Limpa o modelo da tabela
                    model.setRowCount(0);

                    // Preenche o modelo da tabela com os dados ordenados
                    for (Funcionario f : funcionariosOrdenados) {
                        model.addRow(new Object[]{f.getId(), f.getNome(), f.getIdade(), f.getFuncao(),"R$ " + f.getSalarioFormatado(), f.getData()});
                    }




                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        displayMainScreen();


    }


}

