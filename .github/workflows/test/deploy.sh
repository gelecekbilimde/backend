#!/bin/bash

echo 'deployment is starting...'

echo 'connecting to server'

echo "${TEST_ENVIRONMENT_PRIVATE_KEY}" > private_key && chmod 600 private_key

ssh -o StrictHostKeyChecking=no -i private_key "${TEST_ENVIRONMENT_USERNAME}"@"${TEST_ENVIRONMENT_IP}" || exit

echo 'pulling latest changes from github and rebuild docker image'

ssh -o StrictHostKeyChecking=no -i private_key "${TEST_ENVIRONMENT_USERNAME}"@"${TEST_ENVIRONMENT_IP}" "
  cd ${TEST_ENVIRONMENT_BE_APP_PATH};
  sudo git switch ${TEST_ENVIRONMENT_DEPLOYMENT_BRANCH};
  sudo git fetch --all;
  sudo git pull;
  sudo docker compose -f docker-compose-test.yml up -d --build
" || exit

echo 'deployment is done'
