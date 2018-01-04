FROM openjdk:8

RUN mkdir -p /usr/src/potic-token-renewer && mkdir -p /opt

COPY build/distributions/* /usr/src/potic-token-renewer/

RUN unzip /usr/src/potic-token-renewer/potic-token-renewer-*.zip -d /opt/ && ln -s /opt/potic-token-renewer-* /opt/potic-token-renewer

WORKDIR /opt/potic-token-renewer

EXPOSE 5050
ENV ENVIRONMENT_NAME test
ENTRYPOINT [ "sh", "-c", "./bin/potic-token-renewer --spring.profiles.active=$ENVIRONMENT_NAME" ]
CMD []
