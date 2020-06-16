package com.gateway.business;

import com.gateway.data.access.objects.PokemonUserDAO;
import com.gateway.data.objects.PokemonUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonUserBO {

    @Autowired
    private PokemonUserDAO pokemonUserDAO;

    public void save(final PokemonUserDO pokemonUserDO) {
        pokemonUserDAO.save(pokemonUserDO.getIdUser(), pokemonUserDO.getIdPokemon());
    }

    public void delete(final PokemonUserDO pokemonUserDO) {
        pokemonUserDAO.delete(pokemonUserDO.getIdUser(), pokemonUserDO.getIdPokemon());
    }
}
