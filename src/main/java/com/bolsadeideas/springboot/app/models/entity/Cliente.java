package com.bolsadeideas.springboot.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nota: No es necesario que implemente Serializable pero es una buena práctica
 */

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty // Regla para no permitir que el campos sea vacío
    @Column(name = "nombre", unique = false, nullable = false)
    private String nombre;

    @NotEmpty
    @Column(name = "apellido", unique = false, nullable = false)
    private String apellido;

    @NotEmpty
    @Email // Restringe el formato del campo a email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(name = "create_at", unique = false, nullable = false)
    @Temporal(TemporalType.DATE) // Anotacion de JPA que permite convertir y formatear la fecha de la BD
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Establece el formado de fecha
    private Date createAt;

    @Column(name = "foto", unique = false, nullable = true)
    private String foto;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Factura> facturas;

    /**
     * Constructor por defecto de Factura
     */
    public Cliente() {
        facturas = new ArrayList<>();
    }

    /**
     * Obtiene el ID del Cliente
     *
     * @return el identificador del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece la id del cliente
     *
     * @param id el identificador del cliente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente
     *
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente
     *
     * @param nombre el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del cliente
     *
     * @return el apellido del cliente
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del cliente
     *
     * @param apellido el apellido del cliente
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el mail del cliente
     *
     * @return el mail del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el mail del cliente
     *
     * @param email el mail del cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de creación del cliente
     *
     * @return la fecha de creación
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de creación del cliente
     *
     * @param createAt la fecha de creación
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * Obtiene la ruta donde se encuentra la foto de perfil del cliente
     *
     * @return la url de la foto de perfil del cliente
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece la ruta de la foto de perfil del cliente
     *
     * @param foto la rura de la foto de perfil del cliente
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Obtiene las facturas del cliente
     *
     * @return la lista de facturas del cliente
     */
    public List<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Establece las facturas del cliente
     *
     * @param facturas la lista de facturas del cliente
     */
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * Añade una factura a la lista de facturas del cliente
     *
     * @param factura la factura a añadir a la lista de facturas del cliente
     */
    public void addFactura(Factura factura) {
        facturas.add(factura);
    }

    /**
     * Redefinición toString para cliente
     *
     * @return nombre y apellido del cliente
     */
    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * El tiempo de ejecución de serialización asocia a cada clase serializable un número de versión, llamado
     * serialVersionUID, que se utiliza durante la deserialización para verificar que el emisor y el receptor de un
     * objeto serializado tengan clases cargadas para ese objeto que sean compatibles con la serialización. Si el
     * receptor ha cargado una clase para el objeto que tiene un serialVersionUID diferente al de la clase del
     * remitente correspondiente, la deserialización dará como resultado una InvalidClassException. Una clase
     * serializable puede declarar su propio serialVersionUID explícitamente al declarar un campo llamado
     * "serialVersionUID" que debe ser estático, final y de tipo long.
     * <p>
     * SE RECOMIENDA encarecidamente que todas las clases serializables declaren EXPLÍCITAMENTE los valores de
     * serialVersionUID, ya que el cómputo serialVersionUID predeterminado es muy sensible a los detalles de clase que
     * pueden variar dependiendo de las implementaciones del compilador, y puede dar como resultado inesperadas
     * InvalidClassExceptions durante la deserialización.
     * <p>
     * El seriaVersion lo maneja la máquina virtual de forma interna
     */
    private static final long serialVersionUID = 1L;
}
