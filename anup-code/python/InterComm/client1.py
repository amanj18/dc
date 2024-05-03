# client code
import socket

HOST = '127.0.0.1'
PORT = 12345

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect((HOST, PORT))

    while True:
        message = input("Enter message to send to server: ")
        if message.lower() == 'exit':
            break
        client_socket.sendall(message.encode())
        data = client_socket.recv(1024)
        print("Received from server:", data.decode())
        

# run this command on new terminal after running server1.py on another terminal
# python client1.py

# output 
'''
Enter message to send to server: Hello
Received from server: Hello
Enter message to send to server: Who are You?
Received from server: Who are You?
Enter message to send to server: exit
'''