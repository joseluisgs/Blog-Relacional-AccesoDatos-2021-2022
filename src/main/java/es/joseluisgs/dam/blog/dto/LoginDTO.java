package es.joseluisgs.dam.blog.dto;

import es.joseluisgs.dam.blog.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginDTO {
    private Long user_id;
    private User user;
    private LocalDateTime ultimoAcceso;
    private String token;

}
