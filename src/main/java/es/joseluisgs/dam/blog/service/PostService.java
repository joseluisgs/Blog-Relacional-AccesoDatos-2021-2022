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
        posts.forEach(p-> {
            PostDTO postDTO = mapper.toDTO(p);
            postDTO.setUser(this.getUserById(p.getUser_id()));
            postDTO.setCategory(this.getCategoryById(p.getCategory_id()));
            result.add(postDTO);
        });
        return result;
    }

    public PostDTO getPostById(Long id) {
        return mapper.toDTO(this.getById(id));
    }

    public PostDTO postPost(PostDTO postDTO) {
        Post res = this.save(mapper.fromDTO(postDTO));
        return mapper.toDTO(res);
    }

    public PostDTO updatePost(PostDTO postDTO) {
        Post res = this.update(mapper.fromDTO(postDTO));
        return mapper.toDTO(res);
    }

    public PostDTO deletePost(PostDTO postDTO) {
        Post res = this.delete(mapper.fromDTO(postDTO));
        return mapper.toDTO(res);
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
