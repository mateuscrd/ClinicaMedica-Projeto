/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Adauto
 */
public class Usuario {

    private int id;
    private String login;
    private String senha;
    private Funcionario funcionario; // Chave Estrangeira

    // Permissões
    private boolean permissaoCadFuncionario;
    private boolean permissaoCadUsuario;
    private boolean permissaoCadEspecialidade;
    private boolean permissaoCadMedico;
    private boolean permissaoCadConvenio;
    private boolean permissaoAgendamento;
    private boolean permissaoCancelamento;
    private boolean acessoModuloAdmin;
    private boolean acessoModuloAgendamento;
    private boolean acessoModuloAtendimento;
    
    // Construtor Padrão
    public Usuario() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isPermissaoCadFuncionario() {
        return permissaoCadFuncionario;
    }

    public void setPermissaoCadFuncionario(boolean permissaoCadFuncionario) {
        this.permissaoCadFuncionario = permissaoCadFuncionario;
    }

    public boolean isPermissaoCadUsuario() {
        return permissaoCadUsuario;
    }

    public void setPermissaoCadUsuario(boolean permissaoCadUsuario) {
        this.permissaoCadUsuario = permissaoCadUsuario;
    }

    public boolean isPermissaoCadEspecialidade() {
        return permissaoCadEspecialidade;
    }

    public void setPermissaoCadEspecialidade(boolean permissaoCadEspecialidade) {
        this.permissaoCadEspecialidade = permissaoCadEspecialidade;
    }

    public boolean isPermissaoCadMedico() {
        return permissaoCadMedico;
    }

    public void setPermissaoCadMedico(boolean permissaoCadMedico) {
        this.permissaoCadMedico = permissaoCadMedico;
    }

    public boolean isPermissaoCadConvenio() {
        return permissaoCadConvenio;
    }

    public void setPermissaoCadConvenio(boolean permissaoCadConvenio) {
        this.permissaoCadConvenio = permissaoCadConvenio;
    }

    public boolean isPermissaoAgendamento() {
        return permissaoAgendamento;
    }

    public void setPermissaoAgendamento(boolean permissaoAgendamento) {
        this.permissaoAgendamento = permissaoAgendamento;
    }

    public boolean isPermissaoCancelamento() {
        return permissaoCancelamento;
    }

    public void setPermissaoCancelamento(boolean permissaoCancelamento) {
        this.permissaoCancelamento = permissaoCancelamento;
    }

    public boolean isAcessoModuloAdmin() {
        return acessoModuloAdmin;
    }

    public void setAcessoModuloAdmin(boolean acessoModuloAdmin) {
        this.acessoModuloAdmin = acessoModuloAdmin;
    }

    public boolean isAcessoModuloAgendamento() {
        return acessoModuloAgendamento;
    }

    public void setAcessoModuloAgendamento(boolean acessoModuloAgendamento) {
        this.acessoModuloAgendamento = acessoModuloAgendamento;
    }

    public boolean isAcessoModuloAtendimento() {
        return acessoModuloAtendimento;
    }

    public void setAcessoModuloAtendimento(boolean acessoModuloAtendimento) {
        this.acessoModuloAtendimento = acessoModuloAtendimento;
    }
}