# bosch-thermotechnology-api-connector

A java connector to use Bosch Thermotechnology API (https://developer.bosch.com/products-and-services/apis/bosch-thermotechnology-device-api).

Entrypoint: create a new GatewayConnectorImpl.

```
GatewayConnector connector = new GatewayConnectorImpl(
    () -> "https://ews-emea.api.bosch.com/home/sandbox/pointt/v1",
    () -> "Bearer some_Bearer_Token_Obtained_From_Bosch_Developer_portal");
```

All read and write operations are working OK. Error handlling needs rework.
