package ScreenMusic.models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "musicas")
public class Musica {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private LocalDate dataLancamento;
    @Enumerated (EnumType.STRING)
    private Genero genero;


    @ManyToOne
    @JoinColumn (name = "artista_id")
    private Artista artista;

    public Musica() {}

    public Musica(String nomeMusica){
        this.titulo = nomeMusica;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return
                "Titulo: " + titulo +
                "\nData De Lancamento: " + dataLancamento +
                "\nGênero: \n" + genero;
    }
}
