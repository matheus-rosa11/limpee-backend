package school.sptech.limpee.domain;
public class LoginResponse {
    private long idUsuario;
    private String usuario;
    private String message;
    private String token;

    public LoginResponse() {}

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public LoginResponse(long idUsuario, String usuario, String message, String token) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.message = message;
        this.token = token;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
