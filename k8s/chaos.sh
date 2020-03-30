#!/bin/bash
# Randomly delete pods in a Kubernetes namespace.
set -ex

: ${DELAY:=10}
: ${NAMESPACE:=default}
: ${TAG:=app}
: ${VALUE:=webapp}

while true; do
  kubectl \
    --namespace "default" \
    -o 'jsonpath={.items[*].metadata.name}' \
    get pods \
    --selector="${TAG}"="${VALUE}" | \
      tr " " "\n" | \
      shuf | \
      head -n 1 |
      xargs -t --no-run-if-empty \
        kubectl --namespace "default" delete pod
  sleep "${DELAY}"
done
