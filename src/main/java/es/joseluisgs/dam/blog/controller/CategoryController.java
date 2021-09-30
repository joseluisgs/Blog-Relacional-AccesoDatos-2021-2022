package es.joseluisgs.dam.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

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
    public List<CategoryDTO> getAllCategories()  {
        return categoryService.getAllCategories();
    }

    public String getAllCategoriesJSON()  {
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(categoryService.getAllCategories());
    }

    public String getCategoryByIdJSON(Long id){
        // Vamos a devolver el JSON de las categorías
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(categoryService.getById(id));
    }

    public String postCategoryJSON(CategoryDTO categoryDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(categoryService.postCategory(categoryDTO));
    }

    public String updateCategoryJSON(CategoryDTO categoryDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(categoryService.updateCategory(categoryDTO));
    }

    public String deleteCategoryJSON(CategoryDTO categoryDTO) {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(categoryService.deleteCategory(categoryDTO));
    }


}
