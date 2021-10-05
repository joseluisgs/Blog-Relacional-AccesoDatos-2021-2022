package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LoginRepository implements CrudRespository<Login, Long> {
    @Override
    public List<Login> findAll() throws SQLException {
        throw new SQLException("Error: Método no implementado");
    }

    @Override
    public Login getById(Long id) throws SQLException {
        throw new SQLException("Error: Método no implementado");
    }

    @Override
    public Login save(Login login) throws SQLException {
        try {
            UUID uuid = UUID.randomUUID();
            String query = "INSERT INTO login VALUES (?, ?, ?)";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet res = db.insert(query, login.getUser_id(), login.getUltimoAcceso(), uuid.toString());
            // una vez insertado comprobamos que esta correcto para devolverlo
            login.setToken(uuid.toString());
            db.close();
            return login;
        } catch (Exception e) {
            throw new SQLException("Error Repositorio save: " + e.getMessage());
        }
    }

    @Override
    public Login update(Login login) {
        return null;
    }

    @Override
    public Login delete(Login login) {
        return null;
    }

    public Optional<Long> deleteById(Long id) {
        String query = "DELETE FROM login WHERE user_id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, id);
        db.close();
        if (res != 0)
            return Optional.of(id);
        return Optional.empty();
    }
}
