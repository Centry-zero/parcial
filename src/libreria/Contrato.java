package libreria;

public class Contrato {
    private String tipo;
    private String inmuebleId;
    private String clienteId;
    private String propietarioId;
    private String fechaInicio;
    private String fechaFin;

    public Contrato(String tipo, String inmuebleId, String clienteId, String propietarioId, String fechaInicio, String fechaFin) {
        this.tipo = tipo;
        this.inmuebleId = inmuebleId;
        this.clienteId = clienteId;
        this.propietarioId = propietarioId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
}
