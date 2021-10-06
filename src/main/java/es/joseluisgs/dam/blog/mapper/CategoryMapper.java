package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.model.Category;

public class CategoryMapper extends BaseMapper<Category, CategoryDTO> {
    @Override
    public Category fromDTO(CategoryDTO item) {
        return Category.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .build();
    }

    @Override
    public CategoryDTO toDTO(Category item) {
        return CategoryDTO.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .build();
    }
}
