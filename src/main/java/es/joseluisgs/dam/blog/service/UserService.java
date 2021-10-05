package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.mapper.UserMapper;
import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.utils.Cifrador;

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
    public List<UserDTO> getAllUsers() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public UserDTO getUserById(Long id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public UserDTO postUser(UserDTO userDTO) throws SQLException {
        // Ciframos antes el password
        userDTO.setPassword(Cifrador.getInstance().SHA256(userDTO.getPassword()));
        User res = this.save(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }

    public UserDTO updateUser(UserDTO userDTO) throws SQLException {
        User res = this.update(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }

    public UserDTO deleteUser(UserDTO userDTO) throws SQLException {
        User res = this.delete(mapper.fromDTO(userDTO));
        return mapper.toDTO(res);
    }

    public User getUserByMail(String userMail) throws SQLException {
        return repository.getByMail(userMail);
    }
}
