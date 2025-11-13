/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Interface
 */
package implementacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public interface InterfaceProcesos {
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password)
            throws Exception;
}
