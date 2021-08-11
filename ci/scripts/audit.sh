#!/bin/bash -eux

export cwd=$(pwd)

pushd $cwd/dp-java-duration-parse
  make audit
popd