package implementacion;

import servicios.ServicioAutenticacionSupabase;


/**
 * PROXY NO AUDITABLE
 * 1. Autentifica contra Supabase.
 * 2. Ejecuta el proceso.
 * 3. NO Audita.
 */
public class ProxyProcesosNoAuditable implements InterfaceProcesos {

    // Instanciamos el nuevo servicio de autenticación
    private ServicioAutenticacionSupabase authService = new ServicioAutenticacionSupabase();

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {

        // 1. PASO DE SEGURIDAD (Ahora usa el servicio centralizado)
        if (!authService.autenticar(Usuario, Password)) {
            throw new Exception("El usuario '" + Usuario + "' no fue autorizado por Supabase.");
        }

        // 2. EJECUCIÓN DEL PROCESO REAL
        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);

        // 3. PASO DE AUDITORÍA (Omitido)
    }
}