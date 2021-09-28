package es.joseluisgs.dam.blog.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryCRUD<T, ID> {
    // Obtiene todos
    public List<T> findAll();
    // Obtiene por ID
    public Optional<T> getById(ID id);
    // Salva
    public T save(T t);
    // Actualiza
    public T update(T t);
    // Elimina
    public void delete(T t);


}
