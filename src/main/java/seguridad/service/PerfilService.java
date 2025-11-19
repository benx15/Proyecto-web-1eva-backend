package seguridad.service;

import seguridad.model.Perfil;

public interface PerfilService {
	Perfil buscarId(int idPerfil);
	Perfil buscarNombre (String nombre);

}
