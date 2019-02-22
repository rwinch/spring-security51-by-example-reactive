FROM jboss/keycloak:4.1.0.Final

ADD keycloak-realm.json /tmp/demo-realm.json

ENV DB_VENDOR=H2 KEYCLOAK_USER=admin KEYCLOAK_PASSWORD=password

CMD ["-b", "0.0.0.0", "-Dkeycloak.import=/tmp/demo-realm.json"]
