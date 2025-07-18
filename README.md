# Keycloak Last Login Event Listener SPI

This project provides a Keycloak Event Listener SPI that updates a user's attributes with their last login time whenever
they successfully log in.

## Features

* On user login, sets the following attributes:

  * last-login: ISO-8601 UTC timestamp of the login
  * last-login-timestamp: raw epoch milliseconds of the login

## Requirements

* Java 17
* Maven
* Keycloak 21 or newer

## Installation

1. Build the project:

    ```bash
    mvn clean package
    ```

1. Deploy the JAR: Copy target/keycloak-last-login-spi.jar to your Keycloak server:

* For standalone deployments: place in standalone/deployments/
* For Quarkus-based Keycloak: place in providers/

1. Register the provider: Ensure your JAR includes a file at
`META-INF/services/org.keycloak.events.EventListenerProviderFactory` with the fully qualified factory class name.

## Enabling the Event Listener in Keycloak

1. Login to Keycloak Admin Console.
1. Go to Realm Settings > Events > Config.
1. In the Event Listeners section, add pagewiser_last_login_attribute (the provider ID) to the list.
1. Save changes.

## Enabling Event Logging in Keycloak

To enable event logging for user actions:

1. In the Admin Console, go to Realm Settings > Events > Config.
1. Under Save Events, enable User Events and select event types (e.g., LOGIN).
1. Optionally, configure Admin Events and set retention policies.
1. Save changes.

## Testing

Run unit tests with:

```bash
mvn test
```

## License

MIT
