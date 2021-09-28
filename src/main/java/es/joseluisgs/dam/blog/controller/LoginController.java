package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.model.Login;
import es.joseluisgs.dam.blog.repository.LoginRepository;
import es.joseluisgs.dam.blog.service.LoginService;

import java.util.List;

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
    public List<Login> getAllLogins() {
        return loginService.getAllLogins();
    }
}