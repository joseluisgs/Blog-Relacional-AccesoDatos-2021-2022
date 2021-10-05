package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRespository<User, Long> {
    @Override
    public List<User> findAll() throws SQLException {
            String query = "SELECT * FROM user";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
                ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de Usuarios"));
                ArrayList<User> list = new ArrayList<User>();
                while (result.next()) {
                    list.add(
                            new User(
                                    result.getLong("id"),
                                    result.getString("nombre"),
                                    result.getString("email"),
                                    result.getString("password"),
                                    result.getDate("fecha_registro").toLocalDate()
                            )
                    );
                }
                db.close();
                return list;
    }

    @Override
    public User getById(Long ID) throws SQLException {
            String query = "SELECT * FROM user WHERE id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error no existe usuario con ID " + ID));
                result.first();
                User user = new User(
                        result.getLong("id"),
                        result.getString("nombre"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getDate("fecha_registro").toLocalDate()
                );
                db.close();
                return user;
    }

    @Override
    public User save(User user) throws SQLException{
            String query = "INSERT INTO user VALUES (null, ?, ?, ?, ?)";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet res = db.insert(query, user.getNombre(), user.getEmail(),
                    user.getPassword(), user.getFechaRegistro()).orElseThrow(() -> new SQLException("Error al insertar Usuario"));
                // Para obtener su ID
                res.first();
                user.setId(res.getLong(1));
            // una vez insertado comprobamos que esta correcto para devolverlo
            db.close();
            return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE user SET nombre = ?, email = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, user.getNombre(), user.getEmail(), user.getId());
        db.close();
        if (res != 0)
            return user;
        return null;
    }

    @Override
    public User delete(User user) {
        String query = "DELETE FROM user WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, user.getId());
        db.close();
        if (res != 0)
            return user;
        return null;
    }

    public User getByMail(String userMail) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, userMail).orElseThrow(() -> new SQLException("Error no existe usuario con email " + userMail));
            result.first();
            User user = new User(
                    result.getLong("id"),
                    result.getString("nombre"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("fecha_registro").toLocalDate()
            );
            db.close();
            return user;
    }
}
