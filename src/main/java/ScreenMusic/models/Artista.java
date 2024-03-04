package ScreenMusic.models;

import jakarta.persistence.*;

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
    private TipoArtistaEnum tipo;

    @OneToMany (mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> listaMusicas = new ArrayList<>();

    public Artista(String nomeArtista, TipoArtistaEnum tipoArtista) {
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

    public TipoArtistaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtistaEnum tipo) {
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
        return
                "Nome: " + nome +
                ", Tipo: " + tipo +
                ", Musicas: \n" + listaMusicas;
    }
}


