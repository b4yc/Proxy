# Proxy
HTTP Proxy

This is a Java web proxy server that intercepts each and every HTTP request forwards it on to the web server.
This proxy controls the response that is sent to the client from the web server. The modifications made on the response affects all text, hyperlinks, and images.

This proxy is currently using a deprecated method of DataInputStream. Be sure to compile with:
```bash
javac Proxy.java -Xlint:deprecation Proxy.java HttpRequest.java HttpResponse.java
```
To run the proxy

```bash
java Proxy <PORTNUM>
```

where <PORTNUM\> is the port number of which you want the proxy to listen for incoming connections from clients.
To use the proxy server with browser and proxy on separate computers, you will need the IP address on which your proxy server is running. In this case, while running the proxy, you will have to replace the “localhost” with the IP address of the computer where the proxy server is running. Also note the port number used to configure your proxy settings on the machine.

*Congratulations! Your HTTP requests made will now have the specified modifications.*
