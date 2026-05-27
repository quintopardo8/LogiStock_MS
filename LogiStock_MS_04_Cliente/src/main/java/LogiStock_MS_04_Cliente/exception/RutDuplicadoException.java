package LogiStock_MS_04_Cliente.exception;

public class RutDuplicadoException extends RuntimeException {
    public RutDuplicadoException(String mensaje) {
        super(mensaje);
    }
}