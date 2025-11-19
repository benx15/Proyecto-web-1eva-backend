package seguridad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seguridad.model.Perfil;
import seguridad.repository.PerfilRepository;

@Service
public class PerfilServiceImpl implements PerfilService {
	
	@Autowired
	private PerfilRepository prepo;

	@Override
	public Perfil buscarId(int idPerfil) {
		return prepo.findById(idPerfil).orElse(null);
	}

	@Override
	public Perfil buscarNombre(String nombre) {
		return prepo.findByNombre(nombre);
	}

}
