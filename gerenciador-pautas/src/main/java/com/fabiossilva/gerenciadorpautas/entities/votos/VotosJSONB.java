package com.fabiossilva.gerenciadorpautas.entities.votos;

import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import jakarta.validation.constraints.NotNull;

public class VotosJSONB {
    private int SIM = 0;
    private int NAO = 0;

    public VotosJSONB() {
    }

    public VotosJSONB(int SIM, int NAO) {
        this.SIM = SIM;
        this.NAO = NAO;
    }

    public int getSIM() {
        return SIM;
    }

    public void setSIM(int SIM) {
        this.SIM = SIM;
    }

    public int getNAO() {
        return NAO;
    }

    public void setNAO(int NAO) {
        this.NAO = NAO;
    }

    public void votar(@NotNull TipoVoto voto) {
        if (voto.equals(TipoVoto.SIM)) {
            SIM += 1;
        } else {
            NAO += 1;
        }
    }

    @Override
    public String toString() {
        return "Votos: {" +
                "SIM='" + SIM + '\'' +
                ", NAO='" + NAO + '\'' +
                '}';
    }
}
