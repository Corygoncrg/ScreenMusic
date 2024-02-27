package ScreenMusic.models;

public enum Genero {
    Pop("Pop"),
    HIPHOP("Hip hop"),
    ROCK("Rock"),
    ELETRONICA("Eletronica"),
    BOSSANOVA("Bossa Nova"),
    RAP("Rap");

    private String generoMusica;


    Genero(String generoMusica){
        this.generoMusica = generoMusica;
    }

    public static Genero fromString(String texto) {
        for (Genero genero : Genero.values()) {
            if (genero.generoMusica.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhum gênero encontrada para a string fornecida: " + texto);
    }
}
