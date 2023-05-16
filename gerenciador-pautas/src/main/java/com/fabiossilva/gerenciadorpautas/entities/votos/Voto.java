package com.fabiossilva.gerenciadorpautas.entities.votos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "voto")
public class Voto {
    @EmbeddedId
    private VotoPK id;

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                '}';
    }
}
