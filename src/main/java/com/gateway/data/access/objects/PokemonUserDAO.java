package com.gateway.data.access.objects;

import com.gateway.data.objects.PokemonUserDO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.gateway.data.global.Global.*;

@Service
public class PokemonUserDAO {

    private RestTemplate rest;
    private UriComponentsBuilder uriComponentsBuilder;

    public PokemonUserDAO() {
        rest = new RestTemplate();
        uriComponentsBuilder = UriComponentsBuilder.fromUri(URI.create(URI_POKEMONS_USERS_MICROSERVICE));
    }

    public void save(final int idUser, final int idPokemon) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        final PokemonUserDO pokemonUserDO = new PokemonUserDO(idUser, idPokemon);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<PokemonUserDO> requestEntity = new HttpEntity<>(pokemonUserDO, headers);
        rest.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, PokemonUserDO.class);
    }

    public void delete(final int idUser, final int idPokemon) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        final PokemonUserDO pokemonUserDO = new PokemonUserDO(idUser, idPokemon);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<PokemonUserDO> requestEntity = new HttpEntity<>(pokemonUserDO, headers);
        rest.exchange(builder.toUriString(), HttpMethod.DELETE, requestEntity, PokemonUserDO.class);
    }

    public List<PokemonUserDO> findPokemonByUserId(int id) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_USER)
                .path(SLASH).path(String.valueOf(id));

        final PokemonUserDO[] result = rest.getForObject(builder.toUriString(), PokemonUserDO[].class);
        assert result != null;
        return Arrays.asList(result);
    }

    public boolean existsByIdPokemonAndIdUser(int idPokemon, int idUser) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_POKEMON)
                .path(SLASH).path(String.valueOf(idPokemon))
                .path(SLASH).path(URI_USER)
                .path(SLASH).path(String.valueOf(idUser));

        return rest.getForObject(builder.toUriString(), Boolean.class);
    }
}
