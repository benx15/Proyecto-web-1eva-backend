package seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// Representa la respuesta del login
@Data
@AllArgsConstructor
public class LoginResponse {
    private Usuario user;
    private String token;
}
