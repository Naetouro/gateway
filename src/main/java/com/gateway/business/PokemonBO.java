package com.gateway.business;

import com.gateway.data.access.objects.PokemonDAO;
import com.gateway.data.access.objects.PokemonUserDAO;
import com.gateway.data.access.objects.UserDAO;
import com.gateway.data.objects.PokemonDO;
import com.gateway.data.objects.PokemonUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonBO {

    @Autowired
    private PokemonDAO pokemonDAO;

    @Autowired
    private PokemonUserDAO pokemonUserDAO;

    public List<PokemonDO> findAll(final int idUser, final int page, final int number) {
        final List<PokemonDO> pokemonDOS = pokemonDAO.findAll(page, number);

        setCaughtPokemon(pokemonDOS, idUser);

        return pokemonDOS;
    }

    public List<PokemonDO> findAllByTypesIn(final String typeIds, final int idUser, final int page, final int number) {
        final List<PokemonDO> pokemonDOS = pokemonDAO.findAllByTypesIn(typeIds, page, number);

        setCaughtPokemon(pokemonDOS, idUser);

        return pokemonDOS;
    }

    public List<PokemonDO> findAllByNameLike(final String name, final int idUser, final int page, final int number) {
        final List<PokemonDO> pokemonDOS = pokemonDAO.findAllByNameLike(name, page, number);

        setCaughtPokemon(pokemonDOS, idUser);

        return pokemonDOS;
    }

    public PokemonDO findByPokemonIdAndUserId(final int pokemonId, final int userId) {
        final PokemonDO pokemonDO = pokemonDAO.findById(pokemonId);
        final boolean exists = pokemonUserDAO.existsByIdPokemonAndIdUser(pokemonId, userId);

        if (exists) {
            pokemonDO.setCaught(true);
        }

        return pokemonDO;
    }

    public int count() {
        return pokemonDAO.count();
    }

    public int countAllByTypesIn(String typeIds) {
        return pokemonDAO.countDistinctByTypesIn(typeIds);
    }

    public int countAllByNameLike(String name) {
        return pokemonDAO.countAllByNameLike(name);
    }

    public List<String> findAllName() {
        return pokemonDAO.findAllName();
    }

    private void setCaughtPokemon(final List<PokemonDO> pokemonDOS, final int idUser){
        final List<PokemonUserDO> pokemonUserDOS = pokemonUserDAO.findPokemonByUserId(idUser);

        for (PokemonDO pokemonDO : pokemonDOS) {
            for (PokemonUserDO pokemonUserDO : pokemonUserDOS) {
                if (pokemonDO.getId() == pokemonUserDO.getIdPokemon()) {
                    pokemonDO.setCaught(true);
                    break;
                }
            }
        }
    }
}
