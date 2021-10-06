package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.service.UserService;

import java.sql.SQLException;

public class UserController {
    private static UserController controller = null;

    // Mi Servicio unido al repositorio
    private final UserService userService;
    // Con esto evitamos que se imprima el campo password si no queremos
    // https://www.baeldung.com/gson-exclude-fields-serialization
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password");
        }
    };

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
    // Usamos DTO para implementar este patrón en represantación y trasporte de la información
//    public List<UserDTO> getAllUsers() {
//        return userService.getAllUsers();
//    }

    public String getAllUsersJSON() {
        // Vamos a devolver el JSON de las categorías
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(userService.getAllUsers());
        } catch (SQLException e) {
            System.err.println("Error UserController en getAllUser: " + e.getMessage());
            return "Error UserController en getAllUser: " + e.getMessage();
        }
    }

    public String getUserByIdJSON(Long id) {
        // Vamos a devolver el JSON de las categorías
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(userService.getUserById(id));
        } catch (SQLException e) {
            System.err.println("Error UserController en getUserById " + e.getMessage());
            return "Error UserController en getUserById: " + e.getMessage();
        }
    }

    public String postUserJSON(UserDTO userDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(userService.postUser(userDTO));
        } catch (SQLException e) {
            System.err.println("Error UserController en postUser " + e.getMessage());
            return "Error UserController en postUser: " + e.getMessage();
        }
    }

    public String updateUserJSON(UserDTO userDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(userService.updateUser(userDTO));
        } catch (SQLException e) {
            System.err.println("Error UserController en updateUser " + e.getMessage());
            return "Error UserController en updateUser: " + e.getMessage();
        }
    }

    public String deleteUserJSON(UserDTO userDTO) {
        try {
            final Gson prettyGson = new GsonBuilder()
                    .addSerializationExclusionStrategy(strategy)
                    .setPrettyPrinting()
                    .create();
            return prettyGson.toJson(userService.deleteUser(userDTO));
        } catch (SQLException e) {
            System.err.println("Error UserController en deleteUser " + e.getMessage());
            return "Error UserController en deleteUser: " + e.getMessage();
        }
    }

}

