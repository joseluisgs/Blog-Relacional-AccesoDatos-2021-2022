package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.PostDTO;
import es.joseluisgs.dam.blog.mapper.PostMapper;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.CategoryRepository;
import es.joseluisgs.dam.blog.repository.PostRepository;
import es.joseluisgs.dam.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;

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
    public List<PostDTO> getAllPosts() {
        // Obtenemos la lista
        List<Post> posts = this.findAll();
        List<PostDTO> result = new ArrayList<>();
        
        // Ahora debemos añadir al DTO el Usuario como objeto y la Categoria, 
        // no como ID que es lo que nos viene de la BD
        posts.forEach(post-> {
            PostDTO postDTO = mapper.toDTO(post);
            postDTO.setUser(this.getUserById(post.getUser_id()));
            postDTO.setCategory(this.getCategoryById(post.getCategory_id()));
            result.add(postDTO);
        });
        return result;
    }

    public PostDTO getPostById(Long id) {
        Post post = this.getById(id);
        PostDTO postDTO = mapper.toDTO(post);
        postDTO.setUser(this.getUserById(post.getUser_id()));
        postDTO.setCategory(this.getCategoryById(post.getCategory_id()));
        return postDTO;
    }

    public PostDTO postPost(PostDTO postDTO) {
        Post post = this.save(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        return res;
    }

    public PostDTO updatePost(PostDTO postDTO) {
        Post post = this.update(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        return res;
    }

    public PostDTO deletePost(PostDTO postDTO) {
        Post post = this.delete(mapper.fromDTO(postDTO));
        PostDTO res = mapper.toDTO(post);
        res.setUser(this.getUserById(post.getUser_id()));
        res.setCategory(this.getCategoryById(post.getCategory_id()));
        return res;
    }
    
    private User getUserById(Long id) {
        UserService service = new UserService(new UserRepository());
        return service.getById(id);
    }

    private Category getCategoryById(Long id) {
        CategoryService service = new CategoryService(new CategoryRepository());
        return service.getById(id);
    }
}
