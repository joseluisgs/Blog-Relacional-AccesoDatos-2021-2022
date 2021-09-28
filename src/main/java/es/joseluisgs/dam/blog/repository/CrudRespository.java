package es.joseluisgs.dam.blog.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRespository<T, ID> {

    // Operaciones CRUD

    // Obtiene todos
    List<T> findAll();

    // Obtiene por ID
    Optional<T> getById(ID id);

    // Salva
    T save(T t);

    // Actualiza
    T update(T t);

    // Elimina
    void delete(T t);


}
