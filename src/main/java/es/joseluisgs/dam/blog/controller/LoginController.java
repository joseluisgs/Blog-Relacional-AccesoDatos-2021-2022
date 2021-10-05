package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.LoginDTO;
import es.joseluisgs.dam.blog.repository.LoginRepository;
import es.joseluisgs.dam.blog.service.LoginService;

import java.sql.SQLException;

public class LoginController {
    private static LoginController controller = null;

    // Mi Servicio unido al repositorio
    private final LoginService loginService;

    // Para evitar sacar el password del usuario y el user_id
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password") || field.getName().startsWith("user_id");
        }
    };

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
    public String login(String userMail, String userPassword) {
        try {
            LoginDTO login = loginService.login(userMail, userPassword);
            if (login != null) {
                final Gson prettyGson = new GsonBuilder()
                        .addSerializationExclusionStrategy(strategy)
                        .setPrettyPrinting()
                        .create();
                return prettyGson.toJson(login);
            } else
                return "Error Login: Usuario/a no existe o los datos son incorrectos";
        } catch (SQLException e) {
            return "Error Login: Usuario/a no existe o los datos son incorrectos";
        }

    }

    public String logout(Long ID) {
        try {
            if (loginService.logout(ID)) {
                return "Logout OK";
            } else {
                return "Error Logout: Usuario/a no existe o los datos son incorrectos";
            }
        } catch (SQLException e) {
            return "Error Logout: Usuario/a no existe o los datos son incorrectos";
        }
    }
}