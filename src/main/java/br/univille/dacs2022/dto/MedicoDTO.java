package br.univille.dacs2022.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MedicoDTO {
    private long id;
    @NotBlank(message = "O campo não pode ficar vazio")
    @NotNull
    private String nome;
    @NotBlank(message = "O campo não pode ficar vazio")
    @NotNull
    private String CRM;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String cRM) {
        CRM = cRM;
    }


}
