package seguridad.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="PERFILES")
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_PERFIL")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int idPerfil;
	
	private String nombre;
}