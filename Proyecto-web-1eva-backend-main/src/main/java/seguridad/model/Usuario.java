package seguridad.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Builder
@Entity
@Table(name="USUARIOS")
public class Usuario implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String password;
    private String nombre;
    private String apellidos;
    private int enabled;

    @Column(name="FECHA_REGISTRO")
    private LocalDate fechaRegistro;

    @Column(name="fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String direccion;

    @ManyToOne
    @JoinColumn(name="id_perfil")
    private Perfil perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (perfil == null || perfil.getNombre() == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(perfil.getNombre()));
    }

    @Override
    	public boolean isAccountNonExpired() { return true; }

    @Override
    	public boolean isAccountNonLocked() { return true; }

    @Override
    	public boolean isCredentialsNonExpired() { return true; }

    @Override
    	public boolean isEnabled() { return enabled == 1; }
}
