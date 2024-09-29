package Avaliacao;

public class Livro {



    // variaveis criadas como private //para manter enapsulada dentro da classe,
    // apos isso sera necessario os getter e setter para usar e setar os valores.

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponibilidade; // true se disponível, false se emprestado
    // metodo construtor com parametros//this. tras dentro do construtor a
    // possibilidade,
    // de enxergar a variavel instanciada no escopo global e os parametros são
    // tratados omo variaveis do metodo

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
this. disponibilidade = true;
    }

    public Livro() {
    }

    // Getters e Setters das variaveis,
    // instanciadas no escopo global.

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    // Método para exibir informações do livro
 //   @Override
//    public String toString() {
//        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Disponível: "
//                + (disponibilidade ? "Sim" : "Não");
//    }

    public boolean verificaIsbn(String isbn) {
        if (this.isbn != null && this.isbn.equals(isbn)) {
            return true;
        }
        return false;
    }
}
