package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String descripcion;
    private String observacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // No se indica el mappedBy por ser relación de una sola dirección
    @JoinColumn(name = "factura_id") // Como la relación se da en una sola dirección se añade la etiqueta JoinColumn
    private List<ItemFactura> items;

    /**
     * Constructor por defecto de Factura
     */
    public Factura() {
        items = new ArrayList<>();
    }

    /**
     * Asigna la fecha de creación al ser creado el cliente por el entityManager
     */
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /**
     * Obtiene el identificador de factura
     *
     * @return el identificador de factura
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de factura
     *
     * @param id el identificador de factura
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el titulo o descripción de la factura
     *
     * @return la descripción de la factura
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el título o descripción de la factura
     *
     * @param descripcion la descripción de la factura
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene las observaciones de la factura
     *
     * @return las observaciones de la factura
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Establece las observaciones de la factura
     *
     * @param observacion las observaciones de la factura
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Obtiene la fecha de la factura
     *
     * @return la fecha de la factura
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * Establece la fecha de la factura
     *
     * @param createAt la fecha de la factura
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * Obtiene el cliente al que se destina la factura
     *
     * @return el cliente al que se destina la factura
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente al que se destina la factura
     *
     * @param cliente el cliente al que se destina la factura
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Ontiene la lista de detalles de la factura
     *
     * @return la lista de detalle de la factura
     */
    public List<ItemFactura> getItems() {
        return items;
    }

    /**
     * Establece la lista de detalle de la factura
     *
     * @param items la lista de detalles de la factura
     */
    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    /**
     * Añade un nuevo detalle a la lista de detalles de la factura
     *
     * @param item el detalle de factura a añadir
     */
    public void addItem(ItemFactura item) {
        items.add(item);
    }

    /**
     * Obtiene el importe total de la factura
     *
     * @return el importe total de la factura
     */
    public Double getTotal() {
        Double total = 0.0;

        for (ItemFactura item : items) {
            total += item.calcularImporte();
        }
        return total;
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
