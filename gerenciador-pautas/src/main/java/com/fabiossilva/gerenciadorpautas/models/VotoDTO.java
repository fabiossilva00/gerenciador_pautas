package com.fabiossilva.gerenciadorpautas.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

import static com.fabiossilva.gerenciadorpautas.constants.ApplicationConsts.MESSAGEM_ERRO_NULOVAZIO;

public class VotoDTO {

    @NotNull(message = MESSAGEM_ERRO_NULOVAZIO)
    private Long idSessao;

    @NotNull(message = MESSAGEM_ERRO_NULOVAZIO)
    private TipoVoto voto;

    @NotNull(message = MESSAGEM_ERRO_NULOVAZIO)
    @NotBlank(message = MESSAGEM_ERRO_NULOVAZIO)
    @Length(min = 11, max = 11, message = "CPF deve conter 11 caractéres numéricos")
    @Pattern(regexp = "[0-9]+", message = "CPF deve conter 11 caractéres numéricos")
    @CPF(message = "Deve ser um CPF válido")
    private String cpf;

    public VotoDTO(Long idSessao, TipoVoto voto, String cpf) {
        this.idSessao = idSessao;
        this.voto = voto;
        this.cpf = cpf;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public TipoVoto getVoto() {
        return voto;
    }

    public void setVoto(TipoVoto voto) {
        this.voto = voto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotoDTO votoDTO = (VotoDTO) o;

        if (!Objects.equals(idSessao, votoDTO.idSessao)) return false;
        if (voto != votoDTO.voto) return false;
        return Objects.equals(cpf, votoDTO.cpf);
    }

    @Override
    public int hashCode() {
        int result = idSessao != null ? idSessao.hashCode() : 0;
        result = 31 * result + (voto != null ? voto.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VotoDTO{" +
                "idSessao=" + idSessao +
                ", voto=" + voto +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
