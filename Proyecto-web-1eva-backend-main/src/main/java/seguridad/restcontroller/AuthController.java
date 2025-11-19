package seguridad.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import seguridad.model.LoginResponse;
import seguridad.model.Usuario;
import seguridad.service.UsuarioService;

@RestController
@RequestMapping("/api") // Ruta base de la API
@CrossOrigin(origins = "*") // Para llamar desde Angular
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody Usuario loginRequest) {
        // Buscar usuario por username y password 
        Usuario user = usuarioService.findByUsernamePassword(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        		);

        if (user == null) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        	}
        // Esto nos genera un token simple
        String token = "token_" + user.getUsername() + "_" + System.currentTimeMillis();
        return new LoginResponse(user, token);
    }
}
