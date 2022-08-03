package br.univille.dacs2022.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


// POJO - Plain Old Java Object
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500)
    private String nome;
    private String sexo;
    @Temporal(value = TemporalType.DATE)
    private Date dataNascimento;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Cidade cidade;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<PlanoDeSaude> listaPlanos = new ArrayList<>();


    public long getId() {
        return id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<PlanoDeSaude> getListaPlanos() {
        return listaPlanos;
    }

    public void setListaPlanos(List<PlanoDeSaude> listaPlanos) {
        this.listaPlanos = listaPlanos;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
