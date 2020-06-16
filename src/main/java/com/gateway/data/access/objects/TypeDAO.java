package com.gateway.data.access.objects;

import com.gateway.data.objects.TypeDO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.gateway.data.global.Global.*;

@Service
public class TypeDAO {

    private RestTemplate rest;
    private UriComponentsBuilder uriComponentsBuilder;

    public TypeDAO() {
        rest = new RestTemplate();
        uriComponentsBuilder = UriComponentsBuilder.fromUri(URI.create(URI_POKEMON_MICROSERVICE));
    }

    public List<TypeDO> findAll(){
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();

        builder.path(URI_TYPE);

        final TypeDO[] typeDOS = rest.getForObject(builder.toUriString(), TypeDO[].class);
        return Arrays.asList(typeDOS);
    }
}
