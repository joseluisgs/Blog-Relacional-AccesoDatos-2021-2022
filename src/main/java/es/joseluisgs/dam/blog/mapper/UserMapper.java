package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.UserDTO;
import es.joseluisgs.dam.blog.model.User;

public class UserMapper extends BaseMapper<User, UserDTO>{
    @Override
    public User fromDTO(UserDTO item) {
        return User.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .email(item.getEmail())
                .password(item.getPassword())
                .fechaRegistro(item.getFechaRegistro())
                .build();
    }

    @Override
    public UserDTO toDTO(User item) {
        return UserDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .email(item.getEmail())
                .password(item.getPassword())
                .fechaRegistro(item.getFechaRegistro())
                .build();
    }
}
