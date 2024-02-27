package ScreenMusic.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Artistas")
public class Artista {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nome;
    @Enumerated (EnumType.STRING)
    private TipoArtista tipo;
    private LocalDate idade;
    private int albuns;


    @OneToMany (mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> listaMusicas = new ArrayList<>();

    public Artista(String nomeArtista, TipoArtista tipoArtista) {
        this.nome = nomeArtista;
        this.tipo = tipoArtista;
    }

    public String getNome() {
        return nome;
    }

    public Artista(){}


    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getIdade() {
        return idade;
    }

    public void setIdade(LocalDate idade) {
        this.idade = idade;
    }

    public int getAlbuns() {
        return albuns;
    }

    public void setAlbuns(int albuns) {
        this.albuns = albuns;
    }



    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getListaMusicas() {
        return listaMusicas;
    }

    public void setListaMusicas(List<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", idade=" + idade +
                ", albuns=" + albuns +
                ", listaMusicas=" + listaMusicas +
                '}';
    }
}


