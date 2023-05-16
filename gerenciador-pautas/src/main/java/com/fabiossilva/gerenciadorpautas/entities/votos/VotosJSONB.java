package com.fabiossilva.gerenciadorpautas.entities.votos;

public class VotosJSONB {
    private int SIM;
    private int NAO;

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

    @Override
    public String toString() {
        return "Votos: {" +
                "SIM='" + SIM + '\'' +
                ", NAO='" + NAO + '\'' +
                '}';
    }
}
