package com.bolsadeideas.springboot.app.controllers.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Calcula algunos parámetros importantes para la paginación de resultados
 *
 * @param <T>
 */
public class PageRender<T> {
    private String url;
    private Page<T> page;

    private int totalPaginas;
    private int numElementosPorPagina;

    private int paginaActual;

    private List<PageItem> paginas;

    /**
     * Constructor por defecto de PageRender
     *
     * @param url  direccion de la vista donde se muestra la paginación
     * @param page listado paginable de los clientes
     */
    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<>();

        numElementosPorPagina = page.getSize(); // Se almacena el número de elementos por páginas
        totalPaginas = page.getTotalPages(); // Se almacena el total de páginas
        paginaActual = page.getNumber() + 1; // Se almacena la página actual

        int desde, hasta;
        if (totalPaginas <= numElementosPorPagina) { // Muestra desde la primera página hasta la última de una sola vez
            desde = 1;
            hasta = totalPaginas;
        } else { // Paginación por rango. Si existen demasiadas páginas
            if (paginaActual <= numElementosPorPagina / 2) {
                desde = 1;
                hasta = numElementosPorPagina;
            } else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
                desde = totalPaginas - numElementosPorPagina + 1;
                hasta = numElementosPorPagina;
            } else {
                desde = paginaActual - numElementosPorPagina / 2;
                hasta = numElementosPorPagina;
            }

        }

        // Montaje del listado de páginas
        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }

    /**
     * Obtiene la url de la vista donde se muestra la paginación
     *
     * @return la url de la vista donde se muestra la paginación
     */
    public String getUrl() {
        return url;
    }

    /**
     * Obtiene el número total de páginas
     *
     * @return el número total de páginas
     */
    public int getTotalPaginas() {
        return totalPaginas;
    }

    /**
     * Obtiene la página actual
     *
     * @return la página actual
     */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
     * Obtiene las páginas
     *
     * @return listado de páginas
     */
    public List<PageItem> getPaginas() {
        return paginas;
    }

    /**
     * Indica si se trata de la primera página
     *
     * @return TRUE si es la primera página, FALSE en caso contrario
     */
    public boolean isFirst() {
        return page.isFirst();
    }

    /**
     * Indica si se trata de la última página
     *
     * @return TRUE si es la última página, FALSE en caso contrario
     */
    public boolean isLast() {
        return page.isLast();
    }

    /**
     * Indica si la página tiene una página sucesora
     *
     * @return TRUE si existe una página sucesora, FALSE en caso contrario
     */
    public boolean isHasNext() {
        return page.hasNext();
    }

    /**
     * Indica si la página tiene una página predecesora
     *
     * @return TRUE si existe una página predecesora, FALSE en caso contrario
     */
    public boolean isHasPrevious() {
        return page.hasPrevious();
    }

}
