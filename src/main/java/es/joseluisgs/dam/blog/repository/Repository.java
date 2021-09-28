package es.joseluisgs.dam.blog.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    public List<T> findAll();
    public Optional<T> getById(ID id);
    public T save(T t);
    public T edit(T t);
    public void delete(T t);


}
