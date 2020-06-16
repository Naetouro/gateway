package com.gateway.data.access.objects;

import com.gateway.data.objects.UserDO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.gateway.data.global.Global.*;

@Service
public class UserDAO {

    private RestTemplate rest;
    private UriComponentsBuilder uriComponentsBuilder;

    public UserDAO() {
        rest = new RestTemplate();
        uriComponentsBuilder = UriComponentsBuilder.fromUri(URI.create(URI_USER_MICROSERVICE));
    }

    public UserDO findByNameAndPassword(final String name, final String password) {
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();
        builder.path(URI_LOGIN);

        final UserDO userDO = new UserDO(name, password);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<UserDO> requestEntity = new HttpEntity<>(userDO, headers);
        final ResponseEntity<UserDO> result = rest.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, UserDO.class);

        return result.getBody();
    }

    public void save(final String name, final String password) {
        UriComponentsBuilder builder = uriComponentsBuilder.cloneBuilder();
        builder.path(URI_REGISTER);

        UserDO userDO = new UserDO(name, password);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<UserDO> requestEntity = new HttpEntity<>(userDO, headers);
        rest.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, UserDO.class);
    }
}
