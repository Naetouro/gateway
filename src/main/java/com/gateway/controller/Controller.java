package com.gateway.controller;

import com.gateway.business.PokemonBO;
import com.gateway.business.PokemonUserBO;
import com.gateway.business.TypeBO;
import com.gateway.business.UserBO;
import com.gateway.data.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pokedex")
public class Controller {

    @Autowired
    private PokemonUserBO pokemonUserBO;

    @Autowired
    private PokemonBO pokemonBO;

    @Autowired
    private UserBO userBO;

    @Autowired
    private TypeBO typeBO;

    @GetMapping(value = "/pokemon/filter")
    public PokedexDTO findWithFilter(@RequestParam final String typeIds, @RequestParam final int idUser, @RequestParam final int page, @RequestParam final int number) {
        final PokedexDTO pokedexDTO = new PokedexDTO();
        pokedexDTO.setPokemons(pokemonBO.findAllByTypesIn(typeIds, idUser, page, number));
        pokedexDTO.setCount(pokemonBO.countAllByTypesIn(typeIds));
        return pokedexDTO;
    }

    @GetMapping(value = "/pokemon/name")
    public PokedexDTO findAllByNameLike(@RequestParam final String name, @RequestParam final int idUser, @RequestParam final int page, @RequestParam final int number) {
        final PokedexDTO pokedexDTO = new PokedexDTO();
        pokedexDTO.setPokemons(pokemonBO.findAllByNameLike(name, idUser, page, number));
        pokedexDTO.setCount(pokemonBO.countAllByNameLike(name));
        return pokedexDTO;
    }

    @GetMapping(value = "/pokemon")
    public PokedexDTO findAll(@RequestParam final int idUser, @RequestParam final int page, @RequestParam final int number) {
        final PokedexDTO pokedexDTO = new PokedexDTO();
        pokedexDTO.setPokemons(pokemonBO.findAll(idUser, page, number));
        pokedexDTO.setCount(pokemonBO.count());
        return pokedexDTO;
    }

    @GetMapping(value = "/pokemon/{idPokemon}")
    public PokemonDO findById(@PathVariable final int idPokemon, @RequestParam final int idUser) {
        return pokemonBO.findByPokemonIdAndUserId(idPokemon, idUser);
    }

    @PostMapping(value = "/login")
    public UserDO login(@RequestBody UserDO user) {
        return userBO.findByNameAndPassword(user);
    }

    @PostMapping(value = "/signup")
    public void register(@RequestBody UserDO user) {
        userBO.save(user);
    }

    @PostMapping(value = "/pokemonUser")
    public void save(@RequestBody PokemonUserDO pokemonUserDO) {
        pokemonUserBO.save(pokemonUserDO);
    }

    @DeleteMapping(value = "/pokemonUser")
    public void delete(@RequestBody PokemonUserDO pokemonUserDO) {
        pokemonUserBO.delete(pokemonUserDO);
    }

    @GetMapping(value = "/type")
    public List<TypeDO> findAllTypes() {
        return typeBO.findAll();
    }

    @GetMapping(value = "/pokemon/names")
    public List<String> findAllName() {
        return pokemonBO.findAllName();
    }
}
