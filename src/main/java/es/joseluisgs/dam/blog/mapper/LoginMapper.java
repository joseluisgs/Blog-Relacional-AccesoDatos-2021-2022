package es.joseluisgs.dam.blog.mapper;

import es.joseluisgs.dam.blog.dto.LoginDTO;
import es.joseluisgs.dam.blog.model.Login;

public class LoginMapper extends BaseMapper<Login, LoginDTO> {
    @Override
    public Login fromDTO(LoginDTO item) {
        return Login.builder()
                .user_id(item.getUser_id())
                .ultimoAcceso(item.getUltimoAcceso())
                .token(item.getToken())
                .build();
    }

    @Override
    public LoginDTO toDTO(Login item) {
        return LoginDTO.builder()
                .user_id(item.getUser_id())
                .ultimoAcceso(item.getUltimoAcceso())
                .token(item.getToken())
                .build();
    }
}
