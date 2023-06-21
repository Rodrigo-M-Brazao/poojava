package org.example.view;

import org.example.controller.GerenciaCrud;
import org.example.model.Funcionario;


import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    // Obter a data e hora atual
    private static LocalDateTime agora = LocalDateTime.now();

    // Definir o formato do timestamp
    private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    // Gerar o timestamp em formato de string
    private static String timestamp = agora.format(formato);

    private static String tabela = "user";

    public Relatorio() throws Exception {
    }

    public static void imprimir() throws SQLException, IOException {

        ArrayList<Funcionario> resultado;

        resultado = GerenciaCrud.select(tabela, "");
        convertToCSV(resultado, "C:\\Users\\rodri\\OneDrive\\Área de Trabalho\\Relatorio-"+timestamp+".csv");
    }


    public static void imprimir(String valorFiltrado,LocalDate dataInicio, LocalDate dataFim) throws SQLException, IOException {

        List<Funcionario> resultado;
        resultado = GerenciaCrud.select(tabela, valorFiltrado, dataInicio, dataFim);
        convertToCSV(resultado, "C:\\Users\\rodri\\OneDrive\\Área de Trabalho\\Relatorio-"+timestamp+".csv");
    }
    public static void imprimir(String valorFiltrado, int inicio, int fim) throws SQLException, IOException {

        List<Funcionario> resultado;
        resultado = GerenciaCrud.select(tabela, valorFiltrado, inicio, fim);
        convertToCSV(resultado, "C:\\Users\\rodri\\OneDrive\\Área de Trabalho\\Relatorio-"+timestamp+".csv");
    }
    public static void imprimir(String valorFiltrado,double inicio, double fim) throws SQLException, IOException {

        List<Funcionario> resultado;
        resultado = GerenciaCrud.select(tabela, valorFiltrado, inicio, fim);
        convertToCSV(resultado, "C:\\Users\\rodri\\OneDrive\\Área de Trabalho\\Relatorio-"+timestamp+".csv");
    }


    public static void convertToCSV(List<Funcionario> funcionarios, String csvFilePath) throws SQLException, IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath), "UTF-8")))) {


            String headers = "id;"+ "Nome;"+"Idade;"+ "Usuario;"+ "Cargo;"+ "Salario;"+ "Data de inicio;"+ "Tipo de conta;";
            writer.println(headers);


            for (Funcionario f: funcionarios) {
                String linha = f.getId() + ";"+  f.getNome()+ ";"+ f.getIdade()+ ";"+f.getUsername()+ ";"+f.getFuncao()+ ";" +f.getSalario()+";"+f.getData()+ ";"+f.getTipoConta()+ ";" ;

                writer.println(linha);
            }


        }
    }

}
