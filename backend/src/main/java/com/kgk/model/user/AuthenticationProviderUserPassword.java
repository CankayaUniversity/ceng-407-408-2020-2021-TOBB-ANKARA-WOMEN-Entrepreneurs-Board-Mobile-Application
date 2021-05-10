package com.kgk.model.user;

import com.kgk.repository.user.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final UserRepository userRepository;

    public AuthenticationProviderUserPassword(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter -> {
            userRepository.findUserByEmail((String) authenticationRequest.getIdentity())
                    .map(user -> authenticationRequest.getSecret().equals(user.getPassword()) ? user : null)
                    .ifPresentOrElse(
                            user -> {
                                emitter.onNext(new UserDetails(
                                        user.getUserId(),
                                        Collections.singletonList(user.getRoleId())
                                        // user.toAttributeMap()
                                ));
                                emitter.onComplete();
                            },
                            () -> emitter.onError(new AuthenticationException(new AuthenticationFailed()))
                    );
        }, BackpressureStrategy.ERROR);
    }

}
