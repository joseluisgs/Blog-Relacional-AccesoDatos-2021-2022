package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.utils.ApplicationProperties;
import es.joseluisgs.dam.blog.utils.DataBaseController;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationProperties properties = new ApplicationProperties();
        System.out.println("Bienvenid@s a " +
                properties.readProperty("app.title") + " "
                + properties.readProperty("app.version") +" de " +
                properties.readProperty("app.curso"));

        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("DATABASE_NAME"));

        DataBaseController controller = DataBaseController.getInstance();
        controller.open();
        ResultSet rs = controller.query("SELECT * from test");
        try {
            rs.first();
            System.out.println(rs.getString(1));
            controller.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
