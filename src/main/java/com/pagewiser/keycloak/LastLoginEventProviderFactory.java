package com.pagewiser.keycloak;

import org.keycloak.Config;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;


public class LastLoginEventProviderFactory implements EventListenerProviderFactory {

    @Override
    public LastLoginEventProvider create(KeycloakSession keycloakSession) {
        return new LastLoginEventProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
        //
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        //
    }

    @Override
    public void close() {
        //
    }

    @Override
    public String getId() {
        return "pagewiser_last_login_attribute";
    }

}
