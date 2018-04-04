package com.bolsadeideas.springboot.app.models.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Interfaz que intermedia en la subida de imagenes
 */
public interface IUploadFileService {

    /**
     * Obtiene la imagen cargada
     *
     * @param filename el nombre de la imagen a obtener
     * @return la imagen (recurso) a obtener
     */
    Resource load(String filename) throws MalformedURLException;

    /**
     * Copia la imagen seleccionada al directorio de imagenes
     *
     * @param file imagen a copiar en el directorio de imagenes
     * @return el nuevo nombre de la imagen tras renombrarla en el proceso de subida
     */
    String copy(MultipartFile file) throws IOException;

    /**
     * Elimina una imagen del del directorio de imagenes
     *
     * @param filename el nombre del archivo a eliminar
     * @return TRUE si la imagen ha sido eliminada, FALSE en caso contrario
     */
    boolean delete(String filename);

    /**
     * Elimina el directorio de imagenes y todos sus elementos de forma recursiva
     */
    void deleteAll();

    /**
     * Crea el directorio de imagenes al iniciar la aplicación
     *
     * @throws IOException
     */
    void init() throws IOException;
}
