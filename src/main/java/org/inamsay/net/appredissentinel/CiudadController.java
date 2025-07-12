package org.inamsay.net.appredissentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

  private final CiudadService ciudadService;

  public CiudadController(CiudadService ciudadService) {
    this.ciudadService = ciudadService;
  }

  @GetMapping("/{codigo}")
  public Ciudad getCiudad(@PathVariable String codigo) {
    return ciudadService.obtenerCiudad(codigo);
  }

  @GetMapping("/limpiar-cache")
  public String limpiarCache() {
    ciudadService.limpiarCache();
    return "Cache de ciudades limpiada exitosamente.";
  }
}
