package com.mascix;

import com.google.common.net.InetAddresses;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/whois")
public class CommandResource {

    @Inject
    CommandService cmd;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<Object> whois(@QueryParam("ip") String ip) throws Exception {
        if (!InetAddresses.isInetAddress(ip)) {
            return returnFuture("");
        }
        return returnFuture(cmd.execToString(ip));
    }

    public CompletionStage<Object> returnFuture(Object result) {
        return CompletableFuture.supplyAsync(() -> result);
    }
}