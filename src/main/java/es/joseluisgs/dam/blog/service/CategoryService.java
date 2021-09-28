package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class CategoryService extends BaseService<Category, Long, CategoryRepository> {

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

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
