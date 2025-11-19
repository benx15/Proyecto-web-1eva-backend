package seguridad.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import seguridad.model.Rol;
import seguridad.model.Usuario;
import seguridad.service.PerfilService;
import seguridad.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService pserv;
	
	@PostMapping("/api/usuarios/login")
	 public ResponseEntity<?> login(@AuthenticationPrincipal Usuario usuario) {
	        // Si llega aquí, ya está autenticado por Spring Security (HTTP Basic)
	        

	        return ResponseEntity.ok().body(usuario);
	    }
	
	@PostMapping("/registro")
	 public ResponseEntity<?> registro(@RequestBody Usuario usuario) {
	        // Si llega aquí, ya está autenticado por Spring Security (HTTP Basic)
	        usuarioService.registrar(usuario);

	        return ResponseEntity.ok().body(usuario);
	    }
	@GetMapping("admin/listausu")
	@PreAuthorize("hasRole('ADMON')")
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		List<Usuario> todosUsuarios = usuarioService.buscarTodos();
		return ResponseEntity.ok(todosUsuarios);
	}
	
	@PostMapping("admin/crearusu")
	@PreAuthorize("hasRole('ADMON')")
	public ResponseEntity<?> adminCreaUsuario(@RequestBody Usuario usu){
		usu.setPassword(new BCryptPasswordEncoder().encode(usu.getPassword()));
		usu.setEnabled(1);
		usu.setFechaRegistro(LocalDate.now());
		
		if(usu.getPerfil() != null && usu.getPerfil().getNombre() != null) {
			usu.setPerfil(pserv.buscarNombre(usu.getPerfil().getNombre()));
		} 
		
		Usuario nuevoUsu = usuarioService.adminCrearUsu(usu);
		return ResponseEntity.ok(nuevoUsu);
		
	}
	
	/*@GetMapping("/rol/{perfil}")
	
	public ResponseEntity<List<Usuario>> porperfil(@PathVariable String perfil){
		
		
		return ResponseEntity.ok().body(usuarioService.findByPerfil(Rol.valueOf(perfil)));
		
	}
	*/
	

}
