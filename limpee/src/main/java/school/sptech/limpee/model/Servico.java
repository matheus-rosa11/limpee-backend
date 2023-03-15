package school.sptech.limpee.model;

public class Servico {
    private String descricao;
    private String dtHora;
    private boolean finalizado;
    private int nota;
    private String comentario;

    public Servico(String descricao, String dtHora, boolean finalizado, int nota, String comentario) {
        this.descricao = descricao;
        this.dtHora = dtHora;
        this.finalizado = finalizado;
        this.nota = nota;
        this.comentario = comentario;
    }
}
