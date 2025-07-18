package com.pagewiser.keycloak;

import org.junit.jupiter.api.Test;
import org.keycloak.models.KeycloakSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LastLoginEventProviderFactoryTest {

    @Test
    void testCreateReturnsProvider() {
        LastLoginEventProviderFactory factory = new LastLoginEventProviderFactory();
        KeycloakSession session = mock(KeycloakSession.class);
        assertNotNull(factory.create(session));
    }

    @Test
    void testGetId() {
        LastLoginEventProviderFactory factory = new LastLoginEventProviderFactory();
        assertEquals("pagewiser_last_login_attribute", factory.getId());
    }
}
