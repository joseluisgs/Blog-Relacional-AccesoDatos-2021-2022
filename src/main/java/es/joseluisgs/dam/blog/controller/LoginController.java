package es.joseluisgs.dam.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.model.Login;
import es.joseluisgs.dam.blog.repository.LoginRepository;
import es.joseluisgs.dam.blog.service.LoginService;

import java.sql.SQLException;

public class LoginController {
    private static LoginController controller = null;

    // Mi Servicio unido al repositorio
    private final LoginService loginService;

    // Implementamos nuestro Singleton para el controlador
    private LoginController(LoginService userService) {
        this.loginService = userService;
    }

    public static LoginController getInstance() {
        if (controller == null) {
            controller = new LoginController(new LoginService(new LoginRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public void login(String userMail, String userPassword) {
        try {
            Login login = loginService.login(userMail, userPassword);
            if (login != null) {
                final Gson prettyGson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                System.out.println(prettyGson.toJson(login));
            } else
                System.err.println("Error Login: Usuario/a no existe o los datos son incorrectos");
        } catch (SQLException e) {
            System.err.println("Error Login: Usuario/a no existe o los datos son incorrectos");
        }

    }

    public void logout(Long ID) {
        try {
            if (loginService.logout(ID)) {
                System.out.println("Logout OK");
            } else {
                System.err.println("Error Logout: Usuario/a no existe o los datos son incorrectos");
            }
        } catch (SQLException e) {
            System.err.println("Error Logout: Usuario/a no existe o los datos son incorrectos");
        }
    }
}