package br.com.codenation;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    private Jogador capitaoTime;

    private List<Jogador> jogadores = new ArrayList();

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public Time(){

    }

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public void adicionaJogador(Jogador jogador) throws IdentificadorUtilizadoException {
        if(!existeJogador(jogador)){
            this.getJogadores().add(jogador);
        } else
            throw new IdentificadorUtilizadoException("Jogador " + jogador.getId() + " já incluso no time " + this.getId() + ".");
    }

    public Jogador buscarJogadorPorId(long idJogador) throws  JogadorNaoEncontradoException {
        for (Jogador j: this.getJogadores()) {
            if(j.getId().equals(idJogador))
                return j;
        }

        throw new JogadorNaoEncontradoException("Jogador Id " + idJogador + " não encontrado.");
    }

    public boolean existeJogador(Jogador jogador){
        for (Jogador j: this.getJogadores()) {
            if(j.getId().equals(jogador.getId()))
                return true;
        }

        return false;
    }

    public boolean existeJogador(long idJogador){
        for (Jogador j: this.getJogadores()) {
            if(j.getId().equals(idJogador))
                return true;
        }

        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Jogador getCapitaoTime() {
        return capitaoTime;
    }

    public void setCapitaoTime(Jogador novoCapitaoTime) {
        if(this.capitaoTime != null)
            this.capitaoTime.setEhCapitao(false);

        novoCapitaoTime.setEhCapitao(true);
        this.capitaoTime = novoCapitaoTime;
    }

}
