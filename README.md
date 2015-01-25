# Wisdom Hazelcast Sample

In this demo, 2 instances of Wisdom are communicating through a distributed Vert.x Event Bus using Hazelcast.

The `sender` application is a Wisdom application "sending" random data on the Event Bus.

The `receiver` application is another Wisdom application "receiving" the sent data and publishing it on a Web Sockets.

The 2 applications are running on separated Java Virtual Machine and potentially on different machines.

## Launch the demo

### Preparation

In both project edit the `src/main/configuration/cluster.xml` file and edit the `interfaces` element to match the netwrok interface you want to use.

### Execution

1. Open 2 terminals.
2. In the first one, navigate to the 'sender' project and launch `mvn clean wisdom:run`
3. In the second terminal, navigate to the `receiver` project and launch `mvn clean install`
4. Open http://localhost:9000/assets/data.html

A chart depicting received data should be there.
