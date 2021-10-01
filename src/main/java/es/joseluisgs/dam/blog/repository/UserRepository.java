package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CrudRespository<User, Long> {
    @Override
    public List<User> findAll() {
        try {
            String query = "SELECT * FROM user";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            ArrayList<User> list = new ArrayList<User>();
            while (true) {
                if (!result.next()) break;
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
        } catch (SQLException e) {
            System.err.println("Error findAll: " + e.getMessage());
            return null;
        }
    }

    @Override
    public User getById(Long ID) {
        try {
            String query = "SELECT * FROM user WHERE id = " + ID;
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            result.absolute(1);
            User user = new User(
                    result.getLong("id"),
                    result.getString("nombre"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("fecha_registro").toLocalDate()
            );
            db.close();
            return user;
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
            return null;
        }
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO user (nombre, email, password, fecha_registro) VALUES (" +
                "'" + user.getNombre() + "', " +
                "'" + user.getEmail() + "', " +
                "'" + user.getPassword() + "', " +
                "'" + user.getFechaRegistro() + "'" +
                ")";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        if (res != 0)
            // Para obtener su ID
            user = this.getItem(user);
        // una vez insertado comprobamos que esta correcto para devolverlo
        db.close();
        return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE user SET " +
                "nombre = '" + user.getNombre() + "', " +
                "email = '" + user.getEmail() + "'" +//, " +
                // "password = '"+user.getPassword()+"', " +
                // "fecha_registro = '2021-09-30' " +
                " WHERE id = " + user.getId();

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return user;
        return null;
    }

    @Override
    public User delete(User user) {
        String query = "DELETE FROM user " +
                "WHERE id = " + user.getId();

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return user;
        return null;
    }

    private User getItem(User user) {
        try {
            String query = "SELECT * FROM user WHERE email = '" + user.getEmail() + "'";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            result.absolute(1);
            user = new User(
                    result.getLong("id"),
                    result.getString("nombre"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("fecha_registro").toLocalDate()
            );
            db.close();
            return user;
        } catch (SQLException e) {
            System.err.println("Error getItem: " + e.getMessage());
            return null;
        }
    }
}
