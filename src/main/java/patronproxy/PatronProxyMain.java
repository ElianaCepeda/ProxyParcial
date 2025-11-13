package patronproxy;

import implementacion.InterfaceProcesos;
import implementacion.FabricaServicios;
import implementacion.FabricaServicios.TipoProxy;


public class PatronProxyMain {

    public static void main(String[] args) {

        int proceso = 1;

        // --- PRUEBA 1: El Proxy Original (Auditable y con contador) ---
        System.out.println("=================================================");
        System.out.println("PRUEBA 1: Proxy AUDITABLE (con contador/reto)");
        System.out.println("=================================================");

        String usuarioLocal = "proxy@gmail.com";
        String passLocal = "Patrones2025*";

        // Pedimos el proxy AUDITABLE a la fábrica
        InterfaceProcesos procesoAuditable = FabricaServicios.CrearEjecucionProceso(TipoProxy.AUDITABLE);

        try {
            System.out.println("\n--- Intento #1 ---");
            procesoAuditable.EjecutarProcesos(proceso, usuarioLocal, passLocal);
            System.out.println("\n--- Intento #2 ---");
            procesoAuditable.EjecutarProcesos(proceso, usuarioLocal, passLocal);
            System.out.println("\n--- Intento #3 ---");
            procesoAuditable.EjecutarProcesos(proceso, usuarioLocal, passLocal);
            System.out.println("\n--- Intento #4 ---");
            procesoAuditable.EjecutarProcesos(proceso, usuarioLocal, passLocal);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }


        // --- PRUEBA 2: El Nuevo Proxy (No Auditable, con Supabase) ---
        System.out.println("\n\n=================================================");
        System.out.println("PRUEBA 2: Proxy NO AUDITABLE (con Supabase)");
        System.out.println("=================================================");

        String usuarioSupabase = "usuario@gmail.com";
        String passSupabase = "Patrones2025*";

        // Pedimos el proxy NO_AUDITABLE a la fábrica
        InterfaceProcesos procesoNoAuditable = FabricaServicios.CrearEjecucionProceso(TipoProxy.NO_AUDITABLE);

        try {
            System.out.println("\n--- Intento #1 ---");
            procesoNoAuditable.EjecutarProcesos(proceso, usuarioSupabase, passSupabase);
            System.out.println("\n--- Intento #2 ---");
            procesoNoAuditable.EjecutarProcesos(proceso, usuarioSupabase, passSupabase);
            System.out.println("\n--- Intento #3 ---");
            procesoNoAuditable.EjecutarProcesos(proceso, usuarioSupabase, passSupabase);
            System.out.println("\n--- Intento #4 ---");
            procesoNoAuditable.EjecutarProcesos(proceso, usuarioSupabase, passSupabase);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}