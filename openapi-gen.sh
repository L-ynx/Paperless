#!/bin/bash
#
# Spring Generator Configuration-Parameters reference, see https://openapi-generator.tech/docs/generators/spring/
#
docker run \
  --name openapi-gen \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/swagger.json \
  -g spring \
  -p pocoModels=true \
  -p useSeperateModelProject=true \
  --package-name at.fhtw.swkom.paperless \
  --api-package at.fhtw.swkom.paperless.controller \
  --model-package at.fhtw.swkom.paperless.services.dto \
  --additional-properties configPackage=at.fhtw.swkom.paperless.config \
  --additional-properties basePackage=at.fhtw.swkom.paperless.services \
  --additional-properties useSpringBoot3=true \
  --additional-properties useJakartaEe=true \
  -o /tmp/out/

docker cp openapi-gen:/tmp/out/ ./out/

docker rm -f openapi-gen
