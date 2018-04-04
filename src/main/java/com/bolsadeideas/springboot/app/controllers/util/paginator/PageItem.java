package com.bolsadeideas.springboot.app.controllers.util.paginator;

/**
 * Representa una página
 */
public class PageItem {

    private int numero;
    private boolean actual;

    /**
     * Constructor parametrizado de PageItem
     * @param numero número de página. Indentifica a la página
     * @param actual indica si se trata de la página actual
     */
    public PageItem(int numero, boolean actual) {
        this.numero = numero;
        this.actual = actual;
    }

    /**
     * Obtiene el numero de la página
     * @return el número de la página
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Indica si se trata de la página actual
     * @return TRUE si se trata de la página actual, FALSE en caso contrario
     */
    public boolean isActual() {
        return actual;
    }

}
