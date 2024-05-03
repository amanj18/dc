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



# server code
# import socket
# import struct
# import threading  # Import the threading module

# # Define the multicast address and port
# MULTICAST_GROUP = '224.3.29.71'
# MULTICAST_PORT = 10000

# # Create a socket
# sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# # Bind the socket to the server address
# server_address = ('', MULTICAST_PORT)
# sock.bind(server_address)

# # Join the multicast group
# group = socket.inet_aton(MULTICAST_GROUP)
# mreq = struct.pack('4sL', group, socket.INADDR_ANY)
# sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

# # Function to receive messages
# def receive_messages():
#     while True:
#         data, address = sock.recvfrom(1024)
#         print("Received message from {}: {}".format(address, data.decode()))

# # Start a thread to receive messages
# receive_thread = threading.Thread(target=receive_messages)
# receive_thread.start()

# # Wait for the thread to finish
# receive_thread.join()

# # Close the socket
# sock.close()
