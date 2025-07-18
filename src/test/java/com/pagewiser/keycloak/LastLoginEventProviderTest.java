package com.pagewiser.keycloak;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.events.Event;
import org.keycloak.events.EventType;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;

import static org.mockito.Mockito.*;

class LastLoginEventProviderTest {

    private KeycloakSession session;
    private RealmProvider realmProvider;
    private RealmModel realm;
    private UserModel user;
    private LastLoginEventProvider provider;

    @BeforeEach
    void setUp() {
        session = mock(KeycloakSession.class);
        realmProvider = mock(RealmProvider.class);
        realm = mock(RealmModel.class);
        user = mock(UserModel.class);

        when(session.realms()).thenReturn(realmProvider);
        when(session.users()).thenReturn(mock(org.keycloak.models.UserProvider.class));
        provider = new LastLoginEventProvider(session);
    }

    @Test
    void testOnEventLoginUpdatesUserAttributes() {
        Event event = mock(Event.class);
        when(event.getType()).thenReturn(EventType.LOGIN);
        when(event.getRealmId()).thenReturn("realmId");
        when(event.getUserId()).thenReturn("userId");
        when(event.getTime()).thenReturn(123456789L);

        when(realmProvider.getRealm("realmId")).thenReturn(realm);
        when(session.users().getUserById(realm, "userId")).thenReturn(user);
        when(user.getUsername()).thenReturn("testuser");

        provider.onEvent(event);

        verify(user).setSingleAttribute(eq("last-login"), anyString());
        verify(user).setSingleAttribute("last-login-timestamp", "123456789");
    }

    @Test
    void testOnEventNonLoginDoesNothing() {
        Event event = mock(Event.class);
        when(event.getType()).thenReturn(EventType.REGISTER);

        provider.onEvent(event);

        verifyNoInteractions(realmProvider);
        verifyNoInteractions(session.users());
    }

    @Test
    void testOnEventUserNotFoundDoesNothing() {
        Event event = mock(Event.class);
        when(event.getType()).thenReturn(EventType.LOGIN);
        when(event.getRealmId()).thenReturn("realmId");
        when(event.getUserId()).thenReturn("userId");
        when(realmProvider.getRealm("realmId")).thenReturn(realm);
        when(session.users().getUserById(realm, "userId")).thenReturn(null);

        provider.onEvent(event);

        verifyNoInteractions(user);
    }
}
