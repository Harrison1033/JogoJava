package jplay;

public class URL {
    private static final String DIR = "recursos/";
    private static final String SEPARADOR = "/";

    public static String tile(String arquivo) {
        return DIR + "tiles" + SEPARADOR + arquivo;
    }

    public static String sprite(String arquivo) {
        return DIR + "sprites" + SEPARADOR + arquivo;
    }

    public static String audio(String arquivo) {
        return DIR + "audio" + SEPARADOR + arquivo;
    }

    public static String scenario(String arquivo) {
        return DIR + "scn" + SEPARADOR + arquivo;
    }
}
