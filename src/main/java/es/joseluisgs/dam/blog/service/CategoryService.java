package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.mapper.CategoryMapper;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.repository.CategoryRepository;

import java.sql.SQLException;
import java.util.List;

public class CategoryService extends BaseService<Category, Long, CategoryRepository> {
    CategoryMapper mapper = new CategoryMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    // Utilizamos los DTO para par datos del servico al controlador que los presenta
    public List<CategoryDTO> getAllCategories() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public CategoryDTO getCategoryById(Long id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public CategoryDTO postCategory(CategoryDTO categoryDTO) throws SQLException {
        Category res = this.save(mapper.fromDTO(categoryDTO));
        return mapper.toDTO(res);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category res = this.update(mapper.fromDTO(categoryDTO));
        return mapper.toDTO(res);
    }

    public CategoryDTO deleteCategory(CategoryDTO categoryDTO) {
        Category res = this.delete(mapper.fromDTO(categoryDTO));
        return mapper.toDTO(res);
    }

}
