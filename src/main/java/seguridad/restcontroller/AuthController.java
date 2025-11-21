package seguridad.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import seguridad.model.LoginResponse;
import seguridad.model.Usuario;
import seguridad.service.UsuarioService;

import java.util.HashMap;
import java.util.Map;

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
    
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        // Limpiar el contexto de seguridad de Spring
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            SecurityContextHolder.clearContext();
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout exitoso");
        response.put("status", "success");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        
        if (auth != null && auth.isAuthenticated() && 
            !auth.getPrincipal().equals("anonymousUser")) {
            response.put("authenticated", true);
            response.put("username", auth.getName());
            response.put("authorities", auth.getAuthorities());
        } else {
            response.put("authenticated", false);
        }
        
        return ResponseEntity.ok(response);
    }
}
