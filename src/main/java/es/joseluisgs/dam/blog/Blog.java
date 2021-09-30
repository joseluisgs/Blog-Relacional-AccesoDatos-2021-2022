package es.joseluisgs.dam.blog;

import es.joseluisgs.dam.blog.controller.CategoryController;
import es.joseluisgs.dam.blog.controller.UserController;
import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;

public class Blog {
    private static Blog instance;

    private Blog() {
    }

    public static Blog getInstance() {
        if (instance == null) {
            instance = new Blog();
        }
        return instance;
    }

    public void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        controller.open();
        ResultSet rs = controller.query("SELECT * from test");
        try {
            rs.first();
            controller.close();
        } catch (SQLException e) {
            System.err.println("Error al arrancar Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }

    public void Categories() {
        CategoryController categoryController = CategoryController.getInstance();
        // Obtenemos todas las categorías
        // List<CategoryDTO> categories = categoryController.getAllCategories();
        // categories.forEach(c-> System.out.println(c.toJSON()));

        System.out.println("GET Todas las categorías");
        System.out.println(categoryController.getAllCategoriesJSON());

        System.out.println("GET Categoría con ID = 2");
        System.out.println(categoryController.getCategoryByIdJSON(2L));

        System.out.println("POST Insertando Categoría");
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .texto("Prueba " + Instant.now().toString())
                .build();
        System.out.println(categoryController.postCategoryJSON(categoryDTO));

        System.out.println("UPDATE Categoría con ID = 4");
        categoryDTO = CategoryDTO.builder()
                .id(4L)
                .texto("Prueba Update")
                .build();
        System.out.println(categoryController.updateCategoryJSON(categoryDTO));

        System.out.println("DELETE Categoría con ID = 4");
        categoryDTO = CategoryDTO.builder()
                .id(4L)
                .build();
        System.out.println(categoryController.deleteCategoryJSON(categoryDTO));
    }

    public void Users() {
        UserController userController = UserController.getInstance();
        // Obtenemos todas las categorías
        // List<CategoryDTO> categories = categoryController.getAllCategories();
        // categories.forEach(c-> System.out.println(c.toJSON()));

        System.out.println("GET Todos los usuarios");
        System.out.println(userController.getAllUsersJSON());

        System.out.println("GET Ussuario con ID = 2");
        System.out.println(userController.getUserByIdJSON(2L));

        System.out.println("POST Insertando Usuario");
        UserDTO userDTO = UserDTO.builder()
                .nombre("Nombre " + Instant.now().toString())
                .email("user"+Math.random()+"@mail.com")
                .password("1234")
                .fechaRegistro(LocalDate.now())
                .build();
        System.out.println(userController.postUserJSON(userDTO));

        System.out.println("UPDATE Usuario con ID = 5");
        userDTO = UserDTO.builder()
                .id(5L)
                .nombre("Prueba Update")
                .email("prueba@update.com")
                .build();
        System.out.println(userController.updateUserJSON(userDTO));

        System.out.println("DELETE User con ID = 5");
        userDTO = UserDTO.builder()
                .id(5L)
                .build();
        System.out.println(userController.deleteUserJSON(userDTO));

    }
}
