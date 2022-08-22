package dto;

public class UsuarioDTO {
    private int id_usuario;
    private String nome_usuario, cpf_usuario, senha_usuario;
    private float saldo_usuario;

    public float getSaldo_usuario() {
        return saldo_usuario;
    }

    public void setSaldo_usuario(float saldo_usuario) {
        this.saldo_usuario = saldo_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
