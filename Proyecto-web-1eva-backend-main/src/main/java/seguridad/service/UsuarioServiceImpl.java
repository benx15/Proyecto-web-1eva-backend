package seguridad.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import seguridad.model.Perfil;
import seguridad.model.Usuario;
import seguridad.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usrepo;

    UsuarioServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usrepo.findById(username).orElse(null);
	}

	@Override
	public Usuario findById(String username) {
		// TODO Auto-generated method stub
		return usrepo.findById(username).orElse(null);
	}

	@Override
	public Usuario findByUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		return usrepo.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usrepo.findAll();
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		usuario.setEnabled(1);
		usuario.setPerfil(new Perfil());
		usuario.getPerfil().setIdPerfil(2);
		usuario.setFechaRegistro(LocalDate.now());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usrepo.save(usuario);
	}

	@Override
	public List<Usuario> findByPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		return usrepo.findByPerfil(perfil);
	}

}
