package org.inamsay.net.appredissentinel;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CiudadService {
  Logger logger = LoggerFactory.getLogger(CiudadService.class);

  CacheManager cacheManager;

  private static final Map<String, Ciudad> BASE_DATOS = Map.of(
          "PE", new Ciudad("PE", "Lima"),
          "AR", new Ciudad("AR", "Buenos Aires"),
          "CL", new Ciudad("CL", "Santiago")
  );

  public CiudadService(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }
  @PostConstruct
  public void init() {
    logger.debug(">>> Inicializando servicio de ciudades...");

    logger.debug(">>> Cargando datos iniciales en la cache... getName {}",cacheManager.getClass().getName());
    logger.debug(">>> Cargando datos iniciales en la cache... getNestHost {}",cacheManager.getClass().getNestHost());
    // Aquí podrías cargar datos iniciales o realizar alguna configuración
  }

  @Cacheable("ciudades")
  public Ciudad obtenerCiudad(String codigo) {
    logger.debug(">>> Consultando BD simulada...");
    // Simular carga lenta
    try { Thread.sleep(3000); } catch (InterruptedException e) {}
    logger.debug(">>> Ciudad obtenida: {}", BASE_DATOS.get(codigo));
    return BASE_DATOS.get(codigo);
  }

  @CacheEvict(value = "ciudades", allEntries = true)
  public void limpiarCache() {
    logger.debug(">>> Limpiando cache de ciudades...");
    // Aquí podrías implementar la lógica para limpiar el cache si es necesario

  }
}

