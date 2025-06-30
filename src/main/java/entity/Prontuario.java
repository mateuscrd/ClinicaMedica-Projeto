/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Adauto
 */
import java.time.LocalDateTime;

/**
 * Representa a entidade Prontuário do Paciente.
 * Contém os dados de um atendimento médico.
 */
public class Prontuario {

    private int id;
    private Paciente paciente; // Objeto para representar a chave estrangeira (FK) para o paciente
    private String historico;
    private String receituario;
    private String exames;
    private LocalDateTime dataRegistro;

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }
    
    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}