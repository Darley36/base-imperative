package co.com.connection.security.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final String nombreDelRecurso;
    private final String nombreDelCampo;
    private final long valorDelCampo;

    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
        super(String.format("%s no encontrada con : %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
}
