# Run server and client seperately copy the below server code 
# First run server
# client code 
import socket
import struct  # Import the struct module for packing TTL

# Define the multicast address and port
MULTICAST_GROUP = '224.3.29.71'
MULTICAST_PORT = 10000

# Create a socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Set the time-to-live (TTL) for multicast packets
ttl = struct.pack('b', 1)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, ttl)

# Prompt the user to enter messages and send them as multicast
while True:
    message = input("Enter message to send (type 'exit' to quit): ")
    if message.lower() == 'exit':
        break
    sock.sendto(message.encode(), (MULTICAST_GROUP, MULTICAST_PORT))

# Close the socket
sock.close()
