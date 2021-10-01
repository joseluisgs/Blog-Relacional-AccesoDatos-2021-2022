package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Login;
import es.joseluisgs.dam.blog.repository.LoginRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginService extends BaseService<Login, Long, LoginRepository> {

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public LoginService(LoginRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    public List<Login> getAllLogins() throws SQLException {
        return this.findAll();
    }

    public Login createLogin(Login login) {
        return this.save(login);
    }
}
