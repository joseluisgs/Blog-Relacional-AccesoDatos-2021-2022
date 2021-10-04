package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements CrudRespository<Category, Long> {
    @Override
    public List<Category> findAll() {
        try {
            String query = "SELECT * FROM category";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query);
            ArrayList<Category> list = new ArrayList<Category>();
            while (result.next()) {
                list.add(
                        new Category(
                                result.getLong("id"),
                                result.getString("texto")
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
    public Category getById(Long ID) {
        try {
            String query = "SELECT * FROM category WHERE id = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.select(query, ID);
            result.absolute(1);
            Category category = new Category(
                    result.getLong("id"),
                    result.getString("texto")
            );
            db.close();
            return category;
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Category save(Category category) {
        try {
            // si ponemos como primer parametro  null como primero, y pasamos en la llamada
            // al tener configurado el servidor con Prepared generated keys, obtenemos el ID generado autoincremntal de MariaDB
            String query = "INSERT INTO category VALUES (null, ?)";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet res = db.insert(query, category.getTexto());
            if (res != null) {
                // Para obtener su ID
                res.absolute(1);
                category.setId(res.getLong(1));
            }
            db.close();
        } catch (SQLException e) {
            System.err.println("Error getById: " + e.getMessage());
        } finally {
            return category;
        }
    }

    @Override
    public Category update(Category category) {
        String query = "UPDATE category SET texto = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, category.getTexto(), category.getId());
        db.close();
        if (res != 0)
            return category;
        return null;
    }

    @Override
    public Category delete(Category category) {
        String query = "DELETE FROM category WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, category.getId());
        db.close();
        if (res != 0)
            return category;
        return null;

    }
}
