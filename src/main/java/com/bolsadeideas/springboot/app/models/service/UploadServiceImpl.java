package com.bolsadeideas.springboot.app.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
// Esta etiqueta registra la clase como componente  en el contenedor del framework Spring. Posibilita su inyección
public class UploadServiceImpl implements IUploadFileService {

    private final Logger log = LoggerFactory.getLogger(getClass()); // Loger para depuración por consola

    private final String UPLOADS_FOLDER = "uploads";

    /**
     * Obtiene la imagen cargada
     *
     * @param filename el nombre de la imagen a obtener
     * @return la imagen (recurso) a obtener
     */
    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        log.info("pathFoto: " + pathFoto);

        Resource recurso = new UrlResource(pathFoto.toUri()); // Se obtiene el recurso a partir de la url de la imagen
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: No se puede cargar la imagen: " + pathFoto.toString());
        }

        return recurso;
    }

    /**
     * Copia la imagen seleccionada al directorio de imagenes
     *
     * @param file imagen a copiar en el directorio de imagenes
     * @return el nuevo nombre de la imagen tras renombrarla en el proceso de subida
     */
    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = "image_" + UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1]; // Asigna nombre único a la imagen
        Path rootPath = getPath(uniqueFilename); // Agrega la ruta absoluta

        log.info("rootPath: " + rootPath); // Muestra por consola rootPath
        log.info("absoluteRootPath: " + rootPath); // Muestra por consola absoluteRootPath

        // En caso de que no exista el directorio raíz este es creado
        if (!Files.exists(rootPath.getParent()))
            Files.createDirectories(rootPath.getParent());

        Files.copy(file.getInputStream(), rootPath); // Copia la imagen en la carpeta externa del proyecto

        return uniqueFilename;
    }

    /**
     * Elimina una imagen del del directorio de imagenes
     *
     * @return TRUE si la imagen ha sido eliminada, FALSE en caso contrario
     */
    @Override
    public boolean delete(String filename) {

        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile(); // Se obtiene el archivo a partir de la ruta

        if (archivo.exists() && archivo.canRead()) {

            if (archivo.delete()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Elimina el directorio de imagenes y todos sus elementos de forma recursiva
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    /**
     * Crea el directorio de imagenes al iniciar la aplicación
     *
     * @throws IOException
     */
    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    /**
     * Obtiene la ruta absoluta de la imagen
     *
     * @param filename el nombre de la imagen de la que se quiere crear la ruta absoluta
     * @return la ruta absoluta de la imagen
     */
    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath(); // Se define la ruta absoluta de la imagen
    }
}
