FROM docker.pkg.github.com/oms-opensource-minigame-server/oms-core/oms-spigotcore:latest
COPY ./target/minigame-core-*-SNAPSHOT.jar /home/container/plugins/minigame-core.jar

WORKDIR /home/container

CMD ["java","-jar", "/home/container/server.jar"]