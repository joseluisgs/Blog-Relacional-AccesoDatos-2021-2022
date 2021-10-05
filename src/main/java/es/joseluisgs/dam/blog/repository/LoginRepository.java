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
            UUID uuid = UUID.randomUUID();
            String query = "INSERT INTO login VALUES (?, ?, ?)";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            db.insert(query, login.getUser_id(), login.getUltimoAcceso(),
                    uuid.toString()).orElseThrow(() -> new SQLException("Error LoginRepository al insertar Login"));
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

    public Long deleteById(Long id) throws SQLException {
        String query = "DELETE FROM login WHERE user_id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, id);
        db.close();
        if (res > 0)
            return id;
        else
            throw new SQLException("Error LoginRepository al eliminar login con User ID: " + id);
    }
}
