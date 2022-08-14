package com.odeyalo.analog.netflix.video.support;

import com.odeyalo.analog.netflix.video.configuration.security.AuthenticatedUserInformation;

/**
 * Resolve information about user from token and returns it
 */
public interface JwtTokenAuthenticatedUserInformationResolver {

    AuthenticatedUserInformation getInfo(String token);

}
