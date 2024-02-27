package ScreenMusic.repository;

import ScreenMusic.models.Artista;
import ScreenMusic.models.Genero;
import ScreenMusic.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenMusicRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

    Optional<Musica> findByTipo(Genero genero);

}
