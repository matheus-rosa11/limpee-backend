package school.sptech.limpee.service.endereco.dto;

public class EnderecoTxtDTO {
    private int id;
    private String CEP;
    private String complemento;
    private String logradouro;
    private String bairro;
    private int numero;
    private String cidade;
    private String estado;

    public EnderecoTxtDTO() {
    }

    public EnderecoTxtDTO(int id, String CEP, String complemento,
                          String logradouro, String bairro, int numero,
                          String cidade, String estado) {
        this.id = id;
        this.CEP = CEP;
        this.complemento = complemento;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EnderecoTxtDTO{" +
                "id=" + id +
                ", CEP='" + CEP + '\'' +
                ", complemento='" + complemento + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero=" + numero +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
