package es.joseluisgs.dam.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.service.CategoryService;

import java.sql.SQLException;

public class CategoryController {
    private static CategoryController controller = null;

    // Mi Servicio unido al repositorio
    private final CategoryService categoryService;

    // Implementamos nuestro Singleton para el controlador
    private CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public static CategoryController getInstance() {
        if (controller == null) {
            controller = new CategoryController(new CategoryService(new CategoryRepository()));
        }
        return controller;
    }

    // Ejemplo de operaciones
    // Usamos DTO para implementar este patrón en represantación y trasporte de la información
//    public List<CategoryDTO> getAllCategories() {
//        return categoryService.getAllCategories();
//    }

    public String getAllCategoriesJSON() {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(categoryService.getAllCategories());
        } catch (SQLException e) {
            System.err.println("Error CategoryController en getAllCategories: " + e.getMessage());
            return "Error CategoryController en getAllCategories: " + e.getMessage();
        }
    }

    public String getCategoryByIdJSON(Long id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(categoryService.getCategoryById(id));
        } catch (SQLException e) {
            System.err.println("Error CategoryController en getCategoryById: " + e.getMessage());
            return "Error CategoryController en getCategoryById: " + e.getMessage();
        }
    }

    public String postCategoryJSON(CategoryDTO categoryDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(categoryService.postCategory(categoryDTO));
        } catch (SQLException e) {
            System.err.println("Error CategoryController en postCategory: " + e.getMessage());
            return "Error CategoryController en postCategory: " + e.getMessage();
        }
    }

    public String updateCategoryJSON(CategoryDTO categoryDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(categoryService.updateCategory(categoryDTO));
        } catch (SQLException e) {
            System.err.println("Error CategoryController en updateCategory: " + e.getMessage());
            return "Error CategoryController en updateCategory: " + e.getMessage();
        }
    }

    public String deleteCategoryJSON(CategoryDTO categoryDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(categoryService.deleteCategory(categoryDTO));
        } catch (SQLException e) {
            System.err.println("Error CategoryController en deleteCategory: " + e.getMessage());
            return "Error CategoryController en deleteCategory: " + e.getMessage();
        }
    }
}
