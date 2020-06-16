package com.gateway.data.objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PokemonUserDO {
    int idUser;
    int idPokemon;

    public PokemonUserDO() {
    }

    public PokemonUserDO(int idUser, int idPokemon) {
        this.idUser = idUser;
        this.idPokemon = idPokemon;
    }
}
