# quarkus-command-runner
![CI Build](https://github.com/ozkanpakdil/quarkus-command-runner/workflows/CI%20Build/badge.svg)

how to call the service
```
curl localhost:8082/whois?ip=1.2.3.4
```

example output
```bash
mlinux@mint20:~/tmp/quarkus-command-runner$ curl localhost:8082/whois?ip=1.2.3.4
% [whois.apnic.net]
% Whois data copyright terms    http://www.apnic.net/db/dbcopyright.html

% Information related to '1.2.3.0 - 1.2.3.255'

% Abuse contact for '1.2.3.0 - 1.2.3.255' is 'helpdesk@apnic.net'

inetnum:        1.2.3.0 - 1.2.3.255
netname:        Debogon-prefix
descr:          APNIC Debogon Project
descr:          APNIC Pty Ltd
country:        AU
org:            ORG-RQA1-AP
admin-c:        AR302-AP
tech-c:         AR302-AP
abuse-c:        AA1412-AP
status:         ASSIGNED PORTABLE
mnt-by:         APNIC-HM
mnt-routes:     MAINT-AU-APNIC-GM85-AP
mnt-irt:        IRT-APNICRANDNET-AU
last-modified:  2020-11-25T06:34:44Z
source:         APNIC

irt:            IRT-APNICRANDNET-AU
address:        PO Box 3646
address:        South Brisbane, QLD 4101
address:        Australia
e-mail:         helpdesk@apnic.net
abuse-mailbox:  helpdesk@apnic.net
admin-c:        AR302-AP
tech-c:         AR302-AP
auth:           # Filtered
remarks:        helpdesk@apnic.net was validated on 2021-02-09
mnt-by:         MAINT-AU-APNIC-GM85-AP
last-modified:  2021-03-09T01:10:21Z
source:         APNIC

organisation:   ORG-RQA1-AP
org-name:       Resource Quality Assurance
country:        AU
address:        6 Cordelia Street, South Brisbane
e-mail:         research@apnic.net
mnt-ref:        APNIC-HM
mnt-by:         APNIC-HM
last-modified:  2020-11-25T05:35:30Z
source:         APNIC

role:           ABUSE APNICRANDNETAU
address:        PO Box 3646
address:        South Brisbane, QLD 4101
address:        Australia
country:        ZZ
phone:          +000000000
e-mail:         helpdesk@apnic.net
admin-c:        AR302-AP
tech-c:         AR302-AP
nic-hdl:        AA1412-AP
remarks:        Generated from irt object IRT-APNICRANDNET-AU
abuse-mailbox:  helpdesk@apnic.net
mnt-by:         APNIC-ABUSE
last-modified:  2021-03-09T01:10:22Z
source:         APNIC

role:           APNIC RESEARCH
address:        PO Box 3646
address:        South Brisbane, QLD 4101
address:        Australia
country:        AU
phone:          +61-7-3858-3188
fax-no:         +61-7-3858-3199
e-mail:         research@apnic.net
nic-hdl:        AR302-AP
tech-c:         AH256-AP
admin-c:        AH256-AP
mnt-by:         MAINT-APNIC-AP
last-modified:  2018-04-04T04:26:04Z
source:         APNIC

% This query was served by the APNIC Whois Service version 1.88.15-SNAPSHOT (WHOIS-UK4)
```

## docker container with tor
https://hub.docker.com/r/ozkanpakdil/torify-whois-quarkus

```
docker build -f src/main/docker/Dockerfile.jvm -t torify-whois-quarkus .
docker run -it --init --rm -p 8080:8080 torify-whois-quarkus
```

for publishing
```
docker tag torify-whois-quarkus ozkanpakdil/torify-whois-quarkus
docker push ozkanpakdil/torify-whois-quarkus
```

for [graalvm](https://hub.docker.com/r/ozkanpakdil/torify-whois-quarkus-graalvm)
```bash
mvn clean package -DskipTests -P native
docker tag torify-whois-quarkus-graalvm ozkanpakdil/torify-whois-quarkus-graalvm
docker push ozkanpakdil/torify-whois-quarkus-graalvm
```
there is a big difference in container size, one without jre build with graalvm takes 75mb and other one with jre is 219mb. graalvm wins again :)
