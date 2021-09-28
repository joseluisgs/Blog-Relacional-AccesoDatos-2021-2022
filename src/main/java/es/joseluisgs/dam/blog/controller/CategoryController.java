package es.joseluisgs.dam.blog.controller;

import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.service.CategoryService;

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
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
