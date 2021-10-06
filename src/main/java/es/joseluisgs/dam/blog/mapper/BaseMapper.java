package es.joseluisgs.dam.blog.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper<T, DTO> {
    public List<T> fromDTO(List<DTO> items) {
        return items.stream().map(this::fromDTO).collect(Collectors.toList());
    }

    public abstract T fromDTO(DTO item);

    public List<DTO> toDTO(List<T> items) {
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public abstract DTO toDTO(T item);
}
