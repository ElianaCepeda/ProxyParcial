/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Java
 */
package implementacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ProcesoDefecto implements InterfaceProcesos {

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        System.out.println("proceso " + IdProceso + " en acciòn");
        System.out.println("proceso " + IdProceso + " finalizado");
    }

}

