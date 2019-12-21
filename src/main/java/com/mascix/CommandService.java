package com.mascix;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CommandService {

    private final Logger log = LoggerFactory.getLogger(CommandService.class);

    @Inject
    CacheHelper cache;

    public String execToString(String ip) throws IOException, InterruptedException {
        String c1 = "/bin/sh";
        String[] cmd = {c1, "-c", "torify whois " + ip};
        if (System.getProperty("os.name").toLowerCase().contains("windows"))// check is OS windows
        {
            c1 = "cmd.exe";
            cmd = new String[]{c1, "/c", "dir"};
        }

        if (cache.get(ip) != null) {
            return (String) cache.get(ip);
        }

        log.info("ip:{}", ip);
        String result;
        ProcessBuilder pb = new ProcessBuilder(cmd);
        Process p = pb.start();
        p.waitFor(60, TimeUnit.SECONDS);

        result = IOUtils.toString(p.getInputStream(), StandardCharsets.UTF_8);
        cache.put(ip, result);
        return result;
    }
}
