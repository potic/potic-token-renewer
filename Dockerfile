FROM openjdk:8

RUN mkdir -p /usr/src/potic-token-renewer && mkdir -p /usr/app

COPY build/distributions/* /usr/src/potic-token-renewer/

RUN unzip /usr/src/potic-token-renewer/potic-token-renewer-*.zip -d /usr/app/ && ln -s /usr/app/potic-token-renewer-* /usr/app/potic-token-renewer

WORKDIR /usr/app/potic-token-renewer

EXPOSE 5050
ENV ENVIRONMENT_NAME test
ENTRYPOINT [ "sh", "-c", "./bin/potic-token-renewer --spring.profiles.active=$ENVIRONMENT_NAME" ]
CMD []
