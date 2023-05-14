package com.fabiossilva.gerenciadorpautas.entities;

import com.fabiossilva.gerenciadorpautas.models.TipoVoto;
import jakarta.persistence.*;

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
