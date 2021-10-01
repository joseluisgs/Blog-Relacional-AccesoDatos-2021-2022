package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.model.User;

public class PostMapper extends BaseMapper<Post, PostDTO>{
    @Override
    public Post fromDTO(PostDTO item) {
        return Post.builder()
                .id(item.getId())
                .titulo(item.getTitulo())
                .url(item.getUrl())
                .contenido(item.getContenido())
                .fechaPublicacion(item.getFechaPublicacion())
                .user_id(item.getUser().getId())
                .category_id(item.getCategory().getId())
                .build();
    }

    @Override
    public PostDTO toDTO(Post item) {
        return PostDTO.builder()
                .id(item.getId())
                .titulo(item.getTitulo())
                .url(item.getUrl())
                .contenido(item.getContenido())
                .fechaPublicacion(item.getFechaPublicacion())
                .build();
    }
}
