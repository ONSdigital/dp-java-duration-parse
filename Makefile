.PHONY: all
all: audit test build

.PHONY: audit
audit : audit
	mvn install -pl dp-java-duration-parse -Dmaven.test.skip -Dossindex.skip=true

.PHONY: build
build: build
	mvn -pl dp-java-duration-parse -Dmaven.test.skip -Dossindex.skip=true clean package dependency:copy-dependencies

.PHONY: test
test: test
	mvn '-Dtest=dp-java-duration-parse.*Test' test
	# mvn -pl zebedee-reader -Dossindex.skip=true test
