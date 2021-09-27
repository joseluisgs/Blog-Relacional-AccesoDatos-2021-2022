package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.utils.ApplicationProperties;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " de " +
                properties.readProperty("app.curso"));

        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("MY_ENV_VAR1"));

    }
}
