package LogiStock_MS_04_Cliente.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@Entity
@Table(name = "CLIENTES", schema = "LOGISTOCK_MS4")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("id")
    private Long id;

    @Column(name = "NOMBRE")
    @JsonProperty("nombre")
    private String nombre;

    @Column(name = "APELLIDO")
    @JsonProperty("apellido")
    private String apellido;

    @Column(name = "EMAIL")
    @JsonProperty("email")
    private String email;

    @Column(name = "TELEFONO")
    @JsonProperty("telefono")
    private String telefono;

    public Cliente() {}

    public Cliente(Long id, String nombre, String apellido, String email, String telefono) {
        this.id = id; 
        this.nombre = nombre; 
        this.apellido = apellido; 
        this.email = email; 
        this.telefono = telefono;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @JsonProperty("nombreCompleto")
    public String getNombreCompleto() {
        return (nombre != null ? nombre : "") + " " + (apellido != null ? apellido : "");
    }
}