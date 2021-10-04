package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.utils.ApplicationProperties;

public class App {
    public static void main(String[] args) {
        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " "
                + properties.readProperty("app.version") + " de " +
                properties.readProperty("app.curso"));

        Blog blog = Blog.getInstance();
        // Chequeamos el sistema
        blog.checkService();

        // Iniciamos la base de datos al estado original en cada prueba
        if (properties.readProperty("database.init").equals("true"))
            blog.initDataBase();

        // Categorías
        // blog.Categories();

        // Usuarios
        // blog.Users();

        // Post
        // blog.Posts();

        // Comentarios
        // blog.Comments();

        // Login
        blog.Login();


    }
}
