package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private static UserController controller = null;

    // Mi Servicio unido al repositorio
    private final UserService userService;

    // Implementamos nuestro Singleton para el controlador
    private UserController(UserService userService) {
        this.userService = userService;
    }

    public static UserController getInstance() {
        if (controller == null) {
            controller = new UserController(new UserService(new UserRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }
}
