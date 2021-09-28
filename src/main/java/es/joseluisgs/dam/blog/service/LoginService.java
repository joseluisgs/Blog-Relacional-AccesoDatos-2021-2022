package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Login;
import es.joseluisgs.dam.blog.model.Post;
import es.joseluisgs.dam.blog.repository.LoginRepository;
import es.joseluisgs.dam.blog.repository.PostRepository;

import java.util.List;

public class LoginService extends BaseService<Login, Long, LoginRepository> {
    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Login> getAllLogins() {
        return this.findAll();
    }

    public Login createLogin(Login login) {
        return this.save(login);
    }
}
