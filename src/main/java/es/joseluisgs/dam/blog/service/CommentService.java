package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.CommentDTO;
import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.mapper.CommentMapper;
import es.joseluisgs.dam.blog.mapper.PostMapper;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.model.Comment;
import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.repository.CommentRepository;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommentService extends BaseService<Comment, Long, CommentRepository> {
    CommentMapper mapper = new CommentMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CommentService(CommentRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<CommentDTO> getAllComments() {
        // Obtenemos la lista
        List<Comment> posts = this.findAll();
        List<CommentDTO> result = new ArrayList<>();

        // Ahora debemos añadir al DTO el Usuario como objeto y el Post,
        // no como ID que es lo que nos viene de la BD
        posts.forEach(comment -> {
            CommentDTO commentDTO = mapper.toDTO(comment);
            commentDTO.setUser(this.getUserById(comment.getUser_id()));
            commentDTO.setPost(this.getPostById(comment.getPost_id()));
            result.add(commentDTO);
        });
        return result;
    }

    public CommentDTO getCommentById(Long id) {
        Comment comment = this.getById(id);
        CommentDTO commentDTO = mapper.toDTO(comment);
        commentDTO.setUser(this.getUserById(comment.getUser_id()));
        commentDTO.setPost(this.getPostById(comment.getPost_id()));
        return commentDTO;
    }

    public CommentDTO postComment(CommentDTO commentDTO) {
        Comment comment = this.save(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        res.setUser(this.getUserById(comment.getUser_id()));
        res.setPost(this.getPostById(comment.getPost_id()));
        return res;
    }

    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment comment = this.update(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        res.setUser(this.getUserById(comment.getUser_id()));
        res.setPost(this.getPostById(comment.getPost_id()));
        return res;
    }

    public CommentDTO deleteComment(CommentDTO commentDTO) {
        Comment comment = this.delete(mapper.fromDTO(commentDTO));
        CommentDTO res = mapper.toDTO(comment);
        res.setUser(this.getUserById(comment.getUser_id()));
        res.setPost(this.getPostById(comment.getPost_id()));
        return res;
    }

    private User getUserById(Long id) {
        UserService service = new UserService(new UserRepository());
        return service.getById(id);
    }

    private Post getPostById(Long id) {
        PostService service = new PostService(new PostRepository());
        return service.getById(id);
    }
}
