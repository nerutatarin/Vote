FROM ubuntu:20.04
RUN apt-get update && apt-get install -y tor
COPY snowflake-client /usr/share/tor/
COPY torrc /etc/tor/
RUN chmod og+rx /usr/share/tor/snowflake-client
CMD ["/usr/bin/tor", "-f", "/etc/tor/torrc"]
EXPOSE 9050