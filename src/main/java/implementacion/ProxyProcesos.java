/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Java
 */
package implementacion;
import servicios.Auditoria;
import servicios.Seguridad;
import servicios.ServicioAutenticacionSupabase;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Fabrizio Bolaño
 */
public class ProxyProcesos implements InterfaceProcesos {

    // --- INICIO RETO: Almacenamiento estático para el conteo ---
    // Usamos un Mapa estático para simular una base de datos o caché de conteos.
    // La clave será el 'Usuario', y el valor será un objeto que tiene el conteo y la fecha.
    private static Map<String, RegistroConteo> conteoDiario = new HashMap<>();

    // Clase interna simple para guardar el conteo y la fecha
    private static class RegistroConteo {
        int count;
        LocalDate date;

        RegistroConteo(int count, LocalDate date) {
            this.count = count;
            this.date = date;
        }
    }
    // --- FIN RETO ---

    private ServicioAutenticacionSupabase authService = new ServicioAutenticacionSupabase();

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad securityService = new Seguridad();
        // 1. PRIMER PASO: Validación de seguridad
        if (!authService.autenticar(Usuario, Password)) {
            throw new Exception("El usuario '" + Usuario + "' no fue autorizado por SupABASE.");
        }

        // --- INICIO RETO: Lógica del conteo ---
        // 2. SEGUNDO PASO: Validar el límite de ejecuciones
        LocalDate hoy = LocalDate.now();
        RegistroConteo registro = conteoDiario.get(Usuario);

        if (registro == null || !registro.date.isEqual(hoy)) {
            // Es el primer proceso del día para este usuario o un usuario nuevo
            // Creamos un nuevo registro con conteo 1
            conteoDiario.put(Usuario, new RegistroConteo(1, hoy));
            System.out.println("LOG RETO: Usuario " + Usuario + " - Ejecución 1 del día.");
        } else {
            // El usuario ya ha ejecutado procesos hoy, revisamos el contador
            if (registro.count >= 3) {
                // El reto dice "más de tres veces", por lo que la 4ta falla.
                throw new Exception("El usuario '" + Usuario + "' ha excedido el límite de 3 ejecuciones por día.");
            } else {
                // Aún tiene intentos, incrementamos el contador
                registro.count++;
                System.out.println("LOG RETO: Usuario " + Usuario + " - Ejecución " + registro.count + " del día.");
            }
        }
        // --- FIN RETO ---

        // 3. TERCER PASO: Ejecutar el proceso real
        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);

        // 4. CUARTO PASO: Auditoría
        Auditoria audit = new Auditoria();
        audit.AuditoriaServicioUsado(Usuario, ProcesoDefecto.class.getName());
    }

}
