package com.gateway.data.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PokedexDTO {
    private List<PokemonDO> pokemons;
    private int count;
}
