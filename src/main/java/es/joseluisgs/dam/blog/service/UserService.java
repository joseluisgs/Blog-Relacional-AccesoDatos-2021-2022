package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.UserRepository;

import java.util.List;

public class UserService extends BaseService<User, Long, UserRepository> {
    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<User> getAllUsers() {
        return this.findAll();
    }

    public User createUser(User user) {
        return this.save(user);
    }

    // ETC
}
