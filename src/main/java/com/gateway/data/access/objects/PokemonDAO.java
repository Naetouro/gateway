package com.gateway.data.access.objects;

import com.gateway.data.objects.PokemonDO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.gateway.data.global.Global.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class PokemonDAO {

    private RestTemplate rest;
    private UriComponentsBuilder uriComponentsBuilder;

    public PokemonDAO() {
        rest = new RestTemplate();
        uriComponentsBuilder = UriComponentsBuilder.fromUri(URI.create(URI_POKEMON_MICROSERVICE + URI_POKEMON));
    }

    public List<PokemonDO> findAll(final int page, final int number) {
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.queryParam(PAGE, page)
                .queryParam(NUMBER, number);

        final PokemonDO[] pokemonDOS = rest.getForObject(builder.toUriString(), PokemonDO[].class);
        assert pokemonDOS != null;
        return Arrays.asList(pokemonDOS);
    }

    public List<PokemonDO> findAllByTypesIn(String typeIds, final int page, final int number) {
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_FILTER).queryParam(PAGE, page)
                .queryParam(NUMBER, number)
                .queryParam(TYPEIDS, typeIds);

        final PokemonDO[] pokemonDOS = rest.getForObject(builder.toUriString(), PokemonDO[].class);
        assert pokemonDOS != null;
        return Arrays.asList(pokemonDOS);
    }

    public List<PokemonDO> findAllByNameLike(String name, final int page, final int number) {
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_NAME).queryParam(PAGE, page)
                .queryParam(NUMBER, number)
                .queryParam(NAME, name);

        final PokemonDO[] pokemonDOS = rest.getForObject(builder.toUriString(), PokemonDO[].class);
        assert pokemonDOS != null;
        return Arrays.asList(pokemonDOS);
    }

    public PokemonDO findById(final int id) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(SLASH).path(String.valueOf(id));

        final PokemonDO pokemonDO = rest.getForObject(builder.toUriString(), PokemonDO.class);
        assert pokemonDO != null;
        return pokemonDO;
    }

    public int count() {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_COUNT);

        return rest.getForObject(builder.toUriString(), Integer.class);
    }

    public int countDistinctByTypesIn(String typeIds) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_FILTER).path(URI_COUNT)
                .queryParam("typeIds", typeIds);

        return rest.getForObject(builder.toUriString(), Integer.class);
    }

    public int countAllByNameLike(String name) {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_NAME).path(URI_COUNT);
        builder.queryParam("name", name);

        return rest.getForObject(builder.toUriString(), Integer.class);
    }

    public List<String> findAllName() {
        final UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();
        builder.path(URI_NAMES);

        String[] list = rest.getForObject(builder.toUriString(), String[].class);
        return Arrays.asList(list);
    }
}
