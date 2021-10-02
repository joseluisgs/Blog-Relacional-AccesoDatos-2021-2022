package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.model.Post;

public class CommetMapper extends BaseMapper<Comment, CommentDTO>{
    @Override
    public Comment fromDTO(CommentDTO item) {
        return Comment.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .fechaPublicacion(item.getFechaPublicacion())
                .user_id(item.getUser().getId())
                .post_id(item.getPost().getId())
                .build();
    }

    @Override
    public CommentDTO toDTO(Comment item) {
        return CommentDTO.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .fechaPublicacion(item.getFechaPublicacion())
                .build();
    }
}
