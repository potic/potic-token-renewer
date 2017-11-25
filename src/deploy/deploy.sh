#!/usr/bin/env sh

##############################################################################
##
##  Stop and kill currently running docker image, pull newest version and
##  run it.
##
##############################################################################

warn ( ) {
    echo "$*"
}

warn "Currently running docker images"
docker ps -a

warn "Killing currently running docker image..."
docker kill potic-token-renewer; docker rm potic-token-renewer

warn "Pulling latest docker image..."
docker pull potic/potic-token-renewer:$TAG_TO_DEPLOY

warn "Starting docker image..."
docker run -dit --name potic-token-renewer -e LOG_PATH=/logs -v /logs:/logs -p 40407:5050 potic/potic-token-renewer:$TAG_TO_DEPLOY

warn "Currently running docker images"
docker ps -a
