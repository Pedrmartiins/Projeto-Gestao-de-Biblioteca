package Avaliacao;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String numeroRegistro;
    private ArrayList<Livro> livrosEmprestados;



    public Usuario(String nome, String numeroRegistro) {
        this.nome = nome;
        this.numeroRegistro = numeroRegistro;
        this.livrosEmprestados = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void adicionarLivroEmprestado(Livro livro) {
        livrosEmprestados.add(livro);
    }

    public void removerLivroEmprestado(Livro livro) {
        livrosEmprestados.remove(livro);
    }

    public boolean verificaUsuario(String numeroRegistro) {
        if (this.numeroRegistro != null && this.numeroRegistro.equals(numeroRegistro)) {
            return true;
        }
        return false;
    }
}