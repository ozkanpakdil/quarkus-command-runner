
java -Xmx4g -Dsite=CommandRunner -Dspring.profiles.active=prod -jar target/whois-0.0.1-SNAPSHOT.jar --server.port=8081  > log.log 2>&1&