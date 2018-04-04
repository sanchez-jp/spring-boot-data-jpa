package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ItemFactura define una línea o detalle de la factura
 */
@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;


    /**
     * Obtiene el identificador del detalle de factura
     *
     * @return el identificador del detalle de factura
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del detalle de factura
     *
     * @param id el identificador del detalle de factura
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número de unidades de producto del detalle de factura
     *
     * @return el numero de unidades de producto del detalle de factura
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece el número de unidades de producto del detalle de factura
     *
     * @param cantidad el número de unidades del detalle de factura
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto de la línea de factura
     *
     * @return el producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto de la línea de factura
     *
     * @param producto el producto de la línea de factura
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Calcula el importe o subtotal de la línea de factura
     *
     * @return el subtotal de la línea de factura
     */
    public Double calcularImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
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
