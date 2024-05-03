# server code
import socket
import struct
import threading  # Import the threading module

# Define the multicast address and port
MULTICAST_GROUP = '224.3.29.71'
MULTICAST_PORT = 10000

# Create a socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the server address
server_address = ('', MULTICAST_PORT)
sock.bind(server_address)

# Join the multicast group
group = socket.inet_aton(MULTICAST_GROUP)
mreq = struct.pack('4sL', group, socket.INADDR_ANY)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

# Function to receive messages
def receive_messages():
    while True:
        data, address = sock.recvfrom(1024)
        print("Received message from {}: {}".format(address, data.decode()))

# Start a thread to receive messages
receive_thread = threading.Thread(target=receive_messages)
receive_thread.start()

# Wait for the thread to finish
receive_thread.join()

# Close the socket
sock.close()

# run the command in terminal 
# python server.py

# output 

'''
Received message from ('192.168.137.1', 55024): Hello
Received message from ('192.168.137.1', 55024): Anup
'''
