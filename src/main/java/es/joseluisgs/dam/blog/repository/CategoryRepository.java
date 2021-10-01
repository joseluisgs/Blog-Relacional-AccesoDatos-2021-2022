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
            ResultSet result = db.query(query);
            ArrayList<Category> list = new ArrayList<Category>();
            while (true) {
                if (!result.next()) break;
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
            String query = "SELECT * FROM category WHERE id = " + ID;
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
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
        String query = "INSERT INTO category (texto) " +
                "VALUES ('" + category.getTexto() + "')";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        if (res != 0)
            // Para obtener su ID
            category = this.getItem(category);
        // una vez insertado comprobamos que esta correcto para devolverlo
        db.close();
        return category;
    }

    @Override
    public Category update(Category category) {
        String query = "UPDATE category SET " +
                "texto = '" + category.getTexto() + "'" +
                " WHERE id = " + category.getId();
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return category;
        return null;
    }

    @Override
    public Category delete(Category category) {
        String query = "DELETE FROM category " +
                "WHERE id = " + category.getId();
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query);
        db.close();
        if (res != 0)
            return category;
        return null;

    }

    private Category getItem(Category category) {
        try {
            String query = "SELECT * FROM category WHERE texto = '" + category.getTexto() + "'";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            ResultSet result = db.query(query);
            result.absolute(1);
            category = new Category(
                    result.getLong("id"),
                    result.getString("texto")
            );
            db.close();
            return category;
        } catch (SQLException e) {
            System.err.println("Error getItem: " + e.getMessage());
            return null;
        }
    }

}
