package seguridad.service;

import java.util.List;

import seguridad.model.Perfil;
import seguridad.model.Usuario;

public interface UsuarioService {
	
	Usuario findById(String username);
	Usuario findByUsernamePassword(String username, String password);
	List<Usuario> buscarTodos();
	Usuario registrar(Usuario cliente);
	List<Usuario> findByPerfil(Perfil perfil);
	Usuario adminCrearUsu(Usuario usu);

}
