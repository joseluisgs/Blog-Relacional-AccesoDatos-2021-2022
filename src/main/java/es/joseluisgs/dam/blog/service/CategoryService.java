package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.repository.CategoryRepository;

import java.util.List;

public class CategoryService extends BaseService<Category, Long, CategoryRepository> {
    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Category> getAllCategories() {
        return this.findAll();
    }

    public Category createCategory(Category category) {
        return this.save(category);
    }
}
