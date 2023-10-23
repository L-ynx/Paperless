#!/bin/bash
#
# Spring Generator Configuration-Parameters reference, see https://openapi-generator.tech/docs/generators/spring/
#
docker run \
  --name openapi-gen \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/openapi.yaml \
  -g spring \
  -p pocoModels=true \
  -p useSeperateModelProject=true \
  --package-name at.fhtw.swen3.paperless \
  --api-package at.fhtw.swen3.paperless.controller \
  --model-package at.fhtw.swen3.paperless.services.dto \
  --additional-properties configPackage=at.fhtw.swen3.paperless.config \
  --additional-properties basePackage=at.fhtw.swen3.paperless.services \
  --additional-properties useSpringBoot3=true \
  --additional-properties requestMappingMode=controller \
  --additional-properties useSpringController=true \
  --additional-properties useTags=true \
  -o /tmp/out/

docker cp openapi-gen:/tmp/out/ ./out/

docker rm -f openapi-gen
