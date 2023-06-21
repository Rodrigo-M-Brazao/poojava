package org.example.model;

import java.text.DecimalFormat;

public class Funcionario {
    private int id;
    private String nome;
    private String funcao;
    private String username;
    private String password;
    private int tipoConta;
    private String data;
    private int idade;
    private double salario;

    public Funcionario(String nome, String funcao, String username,
                       String password, int tipoConta, String data,
                       int idade,double salario) {
        this.nome = nome;
        this.funcao = funcao;
        this.username = username;
        this.password = password;
        this.tipoConta = tipoConta;
        this.data = data;
        this.idade = idade;
        this.salario = salario;
    }

    public Funcionario(int id, String nome, String funcao, int tipoConta, String data, int idade, double salario) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.tipoConta = tipoConta;
        this.data = data;
        this.idade = idade;
        this.salario = salario;
    }

    public Funcionario(String nome, String username, int tipoConta) {
        this.nome = nome;
        this.username = username;
        this.tipoConta = tipoConta;
    }

    public Funcionario() {
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSalario() {
        return salario;
    }

    public String getSalarioFormatado() {
        DecimalFormat formato = new DecimalFormat("#.00");
        String numeroFormatado = formato.format(salario);

        return numeroFormatado;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


    public String getData() {
        return data;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setData(String data) {
        this.data = data;
    }
}
