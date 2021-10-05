package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.PostDTO;
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

public class PostService extends BaseService<Post, Long, PostRepository> {
    PostMapper mapper = new PostMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public PostService(PostRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<PostDTO> getAllPosts() throws SQLException {
        // Obtenemos la lista
        List<Post> posts = this.findAll();
        List<PostDTO> result = new ArrayList<>();

        // Ahora debemos añadir al DTO el Usuario como objeto y la Categoria, 
        // no como ID que es lo que nos viene de la BD
        for (Post post : posts) {
            PostDTO postDTO = mapper.toDTO(post);
            postDTO.setUser(this.getUserById(post.getUser_id()));
            postDTO.setCategory(this.getCategoryById(post.getCategory_id()));
            // Tenemos que cargar los comentarios que tenga
            postDTO.setComments(getPostComments(postDTO.getId()));
            result.add(postDTO);
        }
        return result;
    }

    public PostDTO getPostById(Long id) throws SQLException {
        Post post = this.getById(id);
        PostDTO postDTO = mapper.toDTO(post);
        postDTO.setUser(this.getUserById(post.getUser_id()));
        postDTO.setCategory(this.getCategoryById(post.getCategory_id()));
        // Tenemos que cargar los comentarios que tenga
        postDTO.setComments(getPostComments(postDTO.getId()));
        return postDTO;
    }

    public PostDTO postPost(PostDTO postDTO) throws SQLException {
        Post post = this.save(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        return res;
    }

    public PostDTO updatePost(PostDTO postDTO) throws SQLException {
        Post post = this.update(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        // Tenemos que cargar los comentarios que tenga
        postDTO.setComments(getPostComments(res.getId()));
        return res;
    }

    public PostDTO deletePost(PostDTO postDTO) throws SQLException {
        // Debemos borrar los comentarios antes
        getPostComments(postDTO.getId()).forEach(this::deleteComment);
        // Ahora borramos el post
        Post post = this.delete(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        return res;
    }

    private User getUserById(Long id) throws SQLException {
        UserService service = new UserService(new UserRepository());
        return service.getById(id);
    }

    private Category getCategoryById(Long id) throws SQLException {
        CategoryService service = new CategoryService(new CategoryRepository());
        return service.getById(id);
    }

    private List<Comment> getPostComments(Long id) throws SQLException {
        CommentService service = new CommentService(new CommentRepository());
        return service.getCommentsByPost(id);
    }

    private Comment deleteComment(Comment comment) {
        CommentService service = new CommentService(new CommentRepository());
        return service.repository.delete(comment);
    }

}
