package com.teedjay.kafka;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.IntStream;

@Path("/kafka")
@Produces(MediaType.TEXT_PLAIN)
public class KafkaResource {

    @Inject
    Consumer consumer;

    @Inject
    Producer producer;

    @GET
    @Path("/consume")
    public String consume() {
        return consumer.receive();
    }

    @GET
    @Path("/produce")
    public String produce() {
        return produce(1);
    }

    @GET
    @Path("/produce/{count}")
    public String produce(@PathParam("count") int count) {
        if (count < 1) throw new IllegalArgumentException("Cannot send" + count + " messages");
        IntStream.range(0, count).forEach(i -> producer.send("msg[" + i +"]"));
        return "sent %d message".formatted(count);
    }

}
