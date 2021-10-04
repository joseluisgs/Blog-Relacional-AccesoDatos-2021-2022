package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class LoginRepository implements CrudRespository<Login, Long> {
    @Override
    public List<Login> findAll() {
        return null;
    }

    @Override
    public Login getById(Long id) {
        return null;
    }

    @Override
    public Login save(Login login) {
        UUID uuid = UUID.randomUUID();
        String query = "INSERT INTO login VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, login.getUser_id(), login.getUltimoAcceso(), uuid.toString());
        // una vez insertado comprobamos que esta correcto para devolverlo
        login.setToken(uuid.toString());
        db.close();
        return login;
    }

    @Override
    public Login update(Login login) {
        return null;
    }

    @Override
    public Login delete(Login login) {
        return null;
    }

    public Long deleteById(Long id) {
        String query = "DELETE FROM login WHERE user_id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, id);
        db.close();
        if (res != 0)
            return id;
        return null;
    }
}
