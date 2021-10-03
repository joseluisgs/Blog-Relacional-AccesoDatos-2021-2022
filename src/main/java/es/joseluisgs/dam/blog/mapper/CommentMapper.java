package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.model.Comment;

public class CommentMapper extends BaseMapper<Comment, CommentDTO> {
    @Override
    public Comment fromDTO(CommentDTO item) {
        return Comment.builder()
                .id(item.getId())
                .texto(item.getTexto())
                .fechaPublicacion(item.getFechaPublicacion())
                // .user_id(item.getUser().getId())
                .user_id(item.getUser_id())
                // .post_id(item.getPost().getId())
                .post_id(item.getPost_id())
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
