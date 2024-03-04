package ScreenMusic.models;

public enum GeneroEnum {
    Pop("Pop"),
    HIPHOP("Hip hop"),
    ROCK("Rock"),
    ELETRONICA("Eletronica"),
    BOSSA_NOVA("Bossa Nova"),
    RAP("Rap"),
    INSTRUMENTAL("Instrumental");

    private final String generoMusica;

    GeneroEnum(String generoMusica){
        this.generoMusica = generoMusica;
    }

    public static GeneroEnum fromString(String texto) {
        for (GeneroEnum genero : GeneroEnum.values()) {
            if (genero.generoMusica.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhum gênero encontrada para a string fornecida: " + texto);
    }
}
