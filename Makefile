SHELL=bash

JAVA_OPTS=-Xmx1024m -Xms1024m -Xdebug -Xrunjdwp:transport=dt_socket,address=8004,server=y,suspend=n

test:
	mvn -Dossindex.skip test
audit:
	mvn ossindex:audit
build:
	mvn -DskipTests -Dossindex.skip -Dossindex.skip=true clean package
lint:
	exit
.PHONY: build test audit lint