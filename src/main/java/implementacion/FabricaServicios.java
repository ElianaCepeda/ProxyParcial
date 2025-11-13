/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Java
 */
package implementacion;

public class FabricaServicios {

    // 1. Creamos un enumerador para definir los tipos de proxy
    public enum TipoProxy {
        AUDITABLE,
        NO_AUDITABLE
    }

    // 2. Modificamos el metodo para que acepte el parámetro de TipoProxy
    public static InterfaceProcesos CrearEjecucionProceso(TipoProxy tipo) {

        // 3. Un switch decide qué proxy instanciar
        switch (tipo) {
            case AUDITABLE:
                // Devuelve el proxy original (con el reto del contador y la auditoría local)
                return new ProxyProcesos();

            case NO_AUDITABLE:
                // Devuelve el nuevo proxy (con Supabase y sin auditoría)
                return new ProxyProcesosNoAuditable();

            default:
                // Opción por defecto
                return new ProxyProcesos();
        }
    }
}