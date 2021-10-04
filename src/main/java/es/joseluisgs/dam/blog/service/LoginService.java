package es.joseluisgs.dam.blog.service;

import es.joseluisgs.dam.blog.model.Login;
import es.joseluisgs.dam.blog.model.User;
import es.joseluisgs.dam.blog.repository.LoginRepository;
import es.joseluisgs.dam.blog.repository.UserRepository;
import es.joseluisgs.dam.blog.utils.Cifrador;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
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

    public Login login(String userMail, String userPassword) {
        User user = getUserByMail(userMail);
        Cifrador cif = Cifrador.getInstance();
        if ((user != null) && user.getPassword().equals(cif.SHA256(userPassword))) {
            // System.out.println("SI");
            Login login = repository.save(new Login(user.getId(), LocalDateTime.now()));
            // System.out.println(login);
            return login;
        } else {
            // System.out.println("NO");
            return null;
        }
    }

    private User getUserByMail(String userMail) {
        UserService service = new UserService(new UserRepository());
        return service.getUserByMail(userMail);
    }

    public boolean logout(Long id) {
        if (repository.deleteById(id) != null)
            return true;
        else
            return false;
    }
}
