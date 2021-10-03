package es.joseluisgs.dam.blog.controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.service.UserService;

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
    // Usamos DTO para implementar este patrón en represantación y trasporte de la información
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    public String getAllUsersJSON() {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(userService.getAllUsers());
    }

    public String getUserByIdJSON(Long id) {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(userService.getUserById(id));
    }

    public String postUserJSON(UserDTO userDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(userService.postUser(userDTO));
    }

    public String updateUserJSON(UserDTO userDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(userService.updateUser(userDTO));
    }

    public String deleteUserJSON(UserDTO userDTO) {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(userService.deleteUser(userDTO));
    }

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

}

