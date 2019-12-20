package com.mascix;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.jboss.resteasy.annotations.cache.Cache;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/whois")
public class CommandResource {

    @Inject
    CommandService cmd;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<Object> whois(@QueryParam("ip") String ip) throws Exception {
        if (!InetAddressValidator.getInstance().isValid(ip)) {
            return null;
        }
        // log.info(line);
        return returnFuture(cmd.execToString(ip));
    }

    public CompletionStage<Object> returnFuture(Object result) {
        return CompletableFuture.supplyAsync(() -> result);
    }
}