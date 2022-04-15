package com.mascix;

import io.quarkus.cache.CacheResult;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CommandService {
    private final Logger log = LoggerFactory.getLogger(CommandService.class);

    @CacheResult(cacheName = "cmd-cache")
    public String execToString(String ip) {
        String c1 = "/bin/sh";
        String[] cmd = {c1, "-c", "torify whois " + ip};
        if (System.getProperty("os.name").toLowerCase().contains("windows"))// check is OS windows
        {
            c1 = "cmd.exe";
            cmd = new String[]{c1, "/c", "dir"};
        }

        log.info("cmd:{}", Arrays.toString(cmd));

        String result = "";
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process p = pb.start();
            p.waitFor(60, TimeUnit.SECONDS);

            result = IOUtils.toString(p.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("runcmd error:", e);
        }

        return result;
    }
}
