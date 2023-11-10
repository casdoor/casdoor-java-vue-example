<h1 align="center" style="border-bottom: none;">📦⚡️Casdoor Vue + Java example</h1>
<h3 align="center">An example of casdoor-vue-sdk and casdoor-java-sdk</h3>

## Architecture

Example contains 2 parts:

| Name     | SDK                | Language         | Source code                                                               |
|----------|--------------------|------------------|---------------------------------------------------------------------------|
| Frontend | casdoor-vue-sdk    | Javascript + Vue | https://github.com/casdoor/casdoor-java-vue-example/tree/master/web       |
| Backend  | casdoor-java-sdk   | Java + Springboot| https://github.com/casdoor/casdoor-java-vue-example                       |

### Demo videos
![normalLogin](./img/login.gif)

## Installation

Example uses Casdoor to manage members. So you need to create an organization and an application for the example in a Casdoor instance. For how to install Casdoor, see: https://casdoor.org/docs/basic/server-installation

### Get the code

```shell
git clone https://github.com/casdoor/casdoor-java-vue-example
```

## Configuration

### Frontend

```js
// in web/src/config.js
export let serverUrl = `http://localhost:8081` // port where java backend runs
```

```js
// in web/src/main.js
const config = {
  serverUrl: "https://demo.casdoor.com", // Casdoor server URL
  clientId: "294b09fbc17f95daf2fe",
  organizationName: "casbin",
  appName: "app-vue-java-example",
  redirectPath: "/callback",
};
```

### Backend

```properties
#in application.properties
# Casdoor server URL
casdoor.endpoint=https://demo.casdoor.com 
casdoor.client-id= 294b09fbc17f95daf2fe
casdoor.client-secret=dd8982f7046ccba1bbd7851d5c1ece4e52bf039d
#certificate:get in your Casdoor server -> application
casdoor.certificate=\
        -----BEGIN CERTIFICATE-----\n\
        MIIE+TCCAuGgAwIBAgIDAeJAMA0GCSqGSIb3DQEBCwUAMDYxHTAbBgNVBAoTFENh\n\
        c2Rvb3IgT3JnYW5pemF0aW9uMRUwEwYDVQQDEwxDYXNkb29yIENlcnQwHhcNMjEx\n\
        MDE1MDgxMTUyWhcNNDExMDE1MDgxMTUyWjA2MR0wGwYDVQQKExRDYXNkb29yIE9y\n\
        Z2FuaXphdGlvbjEVMBMGA1UEAxMMQ2FzZG9vciBDZXJ0MIICIjANBgkqhkiG9w0B\n\
        AQEFAAOCAg8AMIICCgKCAgEAsInpb5E1/ym0f1RfSDSSE8IR7y+lw+RJjI74e5ej\n\
        rq4b8zMYk7HeHCyZr/hmNEwEVXnhXu1P0mBeQ5ypp/QGo8vgEmjAETNmzkI1NjOQ\n\
        CjCYwUrasO/f/MnI1C0j13vx6mV1kHZjSrKsMhYY1vaxTEP3+VB8Hjg3MHFWrb07\n\
        uvFMCJe5W8+0rKErZCKTR8+9VB3janeBz//zQePFVh79bFZate/hLirPK0Go9P1g\n\
        OvwIoC1A3sarHTP4Qm/LQRt0rHqZFybdySpyWAQvhNaDFE7mTstRSBb/wUjNCUBD\n\
        PTSLVjC04WllSf6Nkfx0Z7KvmbPstSj+btvcqsvRAGtvdsB9h62Kptjs1Yn7GAuo\n\
        I3qt/4zoKbiURYxkQJXIvwCQsEftUuk5ew5zuPSlDRLoLByQTLbx0JqLAFNfW3g/\n\
        pzSDjgd/60d6HTmvbZni4SmjdyFhXCDb1Kn7N+xTojnfaNkwep2REV+RMc0fx4Gu\n\
        hRsnLsmkmUDeyIZ9aBL9oj11YEQfM2JZEq+RVtUx+wB4y8K/tD1bcY+IfnG5rBpw\n\
        IDpS262boq4SRSvb3Z7bB0w4ZxvOfJ/1VLoRftjPbLIf0bhfr/AeZMHpIKOXvfz4\n\
        yE+hqzi68wdF0VR9xYc/RbSAf7323OsjYnjjEgInUtRohnRgCpjIk/Mt2Kt84Kb0\n\
        wn8CAwEAAaMQMA4wDAYDVR0TAQH/BAIwADANBgkqhkiG9w0BAQsFAAOCAgEAn2lf\n\
        DKkLX+F1vKRO/5gJ+Plr8P5NKuQkmwH97b8CS2gS1phDyNgIc4/LSdzuf4Awe6ve\n\
        C06lVdWSIis8UPUPdjmT2uMPSNjwLxG3QsrimMURNwFlLTfRem/heJe0Zgur9J1M\n\
        8haawdSdJjH2RgmFoDeE2r8NVRfhbR8KnCO1ddTJKuS1N0/irHz21W4jt4rxzCvl\n\
        2nR42Fybap3O/g2JXMhNNROwZmNjgpsF7XVENCSuFO1jTywLaqjuXCg54IL7XVLG\n\
        omKNNNcc8h1FCeKj/nnbGMhodnFWKDTsJcbNmcOPNHo6ixzqMy/Hqc+mWYv7maAG\n\
        Jtevs3qgMZ8F9Qzr3HpUc6R3ZYYWDY/xxPisuKftOPZgtH979XC4mdf0WPnOBLqL\n\
        2DJ1zaBmjiGJolvb7XNVKcUfDXYw85ZTZQ5b9clI4e+6bmyWqQItlwt+Ati/uFEV\n\
        XzCj70B4lALX6xau1kLEpV9O1GERizYRz5P9NJNA7KoO5AVMp9w0DQTkt+LbXnZE\n\
        HHnWKy8xHQKZF9sR7YBPGLs/Ac6tviv5Ua15OgJ/8dLRZ/veyFfGo2yZsI+hKVU5\n\
        nCCJHBcAyFnm1hdvdwEdH33jDBjNB6ciotJZrf/3VYaIWSalADosHAgMWfXuWP+h\n\
        8XKXmzlxuHbTMQYtZPDgspS5aK+S4Q9wb8RRAYo=\n\
        -----END CERTIFICATE-----
casdoor.organization-name=casbin
casdoor.application-name=app-built-in
server.port=8081
```

- install dependencies

  ```shell
  PS .\casdoor-java-vue-example\web> yarn install
  ```

- run

  ```shell
  PS .\casdoor-java-vue-example\web> yarn serve
  ```

- Now, example runs its front end at port 8080 and runs it's back end at port 8081. You can modify the code and see what will happen.
