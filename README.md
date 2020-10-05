# Proxy
HTTP Proxy

This is a web proxy server that intercepts each and every request and (generally) forwards it on to the web server.
This proxy controls the response sent the the client from the web server.
The modifications made on the response affects all text, hyperlinks, and images.

To run the proxy
  java Proxy port
where port is the port number of which you want the proxy to listen for incoming connections from clients.
To use the proxy server with browser and proxy on separate computers, you will need the IP address on which your proxy server is running. In this case, while running the proxy, you will have to replace the “localhost” with the IP address of the computer where the proxy server is running. Also note the port number used.
