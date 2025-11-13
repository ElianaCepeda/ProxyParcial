package servicios;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * NUEVO SERVICIO
 * Responsabilidad Única: Autenticar usuarios contra Supabase.
 * Lee las credenciales del archivo .env
 */
public class ServicioAutenticacionSupabase {

    // Carga las variables de entorno una sola vez
    private static final Dotenv dotenv = Dotenv.load();
    private static final String SUPABASE_URL = dotenv.get("SUPABASE_URL");
    private static final String SUPABASE_ANON_KEY = dotenv.get("SUPABASE_ANON_KEY");

    public boolean autenticar(String email, String password) throws IOException, InterruptedException {

        // Validación de variables de entorno
        if (SUPABASE_URL == null || SUPABASE_ANON_KEY == null) {
            System.err.println("ERROR: No se encontraron las variables SUPABASE_URL o SUPABASE_ANON_KEY.");
            System.err.println("Asegúrese de tener un archivo .env en la raíz del proyecto.");
            throw new IOException("Faltan variables de entorno de Supabase.");
        }

        // 1. Crear el JSON para enviar
        String jsonBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        // 2. Crear el cliente y la petición
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SUPABASE_URL + "/auth/v1/token?grant_type=password"))
                .header("apikey", SUPABASE_ANON_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // 3. Enviar la petición
        System.out.println("... [AuthService] Contactando a Supabase para autenticar...");
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 4. Verificar la respuesta
        if (response.statusCode() == 200) {
            System.out.println("... [AuthService] Supabase: Usuario " + email + " autenticado exitosamente.");
            return true;
        } else {
            System.out.println("... [AuthService] Supabase: Error de autenticación. Código: " + response.statusCode());
            return false;
        }
    }
}