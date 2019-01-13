# login-server
Login server that goes along with the login-dagger project

Using JWTs with refresh token.

Group based access. 

Admin can only access /admin endpoints.

Guest can only access /guest endpoints.

/auth - folder containing the authentication "server"

/common - common services used in /auth and /resource

/resource - folder containing the resource "server"

I say "server" because this will eventually be two different projects as opposed to being smashed into this one.


### How to run
```
mvn clean package
docker build --tag=name .
docker run --network host name

```