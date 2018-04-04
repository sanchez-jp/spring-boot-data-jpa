package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    /**
     * Asigna la fecha de creación al ser creado el cliente por el entityManager
     */
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /**
     * Obtiene el identificador de producto
     *
     * @return el identificadir de producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de producto
     *
     * @param id el identificador de producto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto
     *
     * @return el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto
     *
     * @param nombre el nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto
     *
     * @return el precio del producto
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto
     *
     * @param precio el precio del producto
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la fecha de creación del producto
     *
     * @return la fecha de creación del producto
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de creación del producto
     *
     * @param createAt la fecha de creación del producto
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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
     */
    private static final long serialversionUID = 1L;
}
