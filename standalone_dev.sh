mvn compile assembly:single -Dmaven.test.skip=true
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8765,suspend=n -Xms512m -Xmx512m -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -Dninja.context=/test -Dninja.port=8081 -Dninja.mode=dev -jar target/unitofwork-transactional-test-1.0-SNAPSHOT-jar-with-dependencies.jar
