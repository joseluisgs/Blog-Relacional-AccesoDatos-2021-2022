package es.joseluisgs.dam.blog.repository;

import es.joseluisgs.dam.blog.database.DataBaseController;
import es.joseluisgs.dam.blog.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements CrudRespository<Category, Long> {
    @Override
    public List<Category> findAll() throws SQLException {
        String query = "SELECT * FROM category";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error CategoryRepository al consultar registros de Categorías"));
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
    }

    @Override
    public Category getById(Long ID) throws SQLException {
        String query = "SELECT * FROM category WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error CategoryRepository al consultar categoría con ID " + ID));
        if (result.first()) {
            Category category = new Category(
                    result.getLong("id"),
                    result.getString("texto")
            );
            db.close();
            return category;
        } else
            throw new SQLException("Error CategoryRepository no existe categoría con ID: " + ID);
    }

    @Override
    public Category save(Category category) throws SQLException {
        // si ponemos como primer parametro  null como primero, y pasamos en la llamada
        // al tener configurado el servidor con Prepared generated keys, obtenemos el ID generado autoincremntal de MariaDB
        String query = "INSERT INTO category VALUES (null, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, category.getTexto()).orElseThrow(() -> new SQLException("Error CategoryRepository al insertar categoría"));
        // Para obtener su ID
        if (res.first()) {
            category.setId(res.getLong(1));
            db.close();
            return category;
        } else
            throw new SQLException("Error CategoryRepository al insertar cantegoria en BD");
    }

    @Override
    public Category update(Category category) throws SQLException {
        String query = "UPDATE category SET texto = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, category.getTexto(), category.getId());
        db.close();
        if (res > 0)
            return category;
        else
            throw new SQLException("Error CategoryRepository al actualizar categoria con id: " + category.getId());
    }

    @Override
    public Category delete(Category category) throws SQLException {
        String query = "DELETE FROM category WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, category.getId());
        db.close();
        if (res > 0)
            return category;
        else
            throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + category.getId());

    }
}
