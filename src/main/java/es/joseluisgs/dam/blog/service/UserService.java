package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.CategoryDTO;
import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.mapper.CategoryMapper;
import es.joseluisgs.dam.blog.mapper.UserMapper;
import es.joseluisgs.dam.blog.model.Category;
import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.utils.Cifrador;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;

public class UserService extends BaseService<User, Long, UserRepository> {
    UserMapper mapper = new UserMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public UserService(UserRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<UserDTO> getAllUsers() {
        return mapper.toDTO(this.findAll());
    }

    public UserDTO getUserById(Long id) {
        return mapper.toDTO(this.getById(id));
    }

    public UserDTO postUser(UserDTO userDTO) {
        // Ciframos antes el password
        userDTO.setPassword(Cifrador.getInstance().SHA512(userDTO.getPassword()));
        User res = this.save(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User res = this.update(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }

    public UserDTO deleteUser(UserDTO userDTO) {
        User res = this.delete(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }
}
