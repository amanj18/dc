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

# server code

import socket

HOST = '127.0.0.1'
PORT = 12345

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
    server_socket.bind((HOST, PORT))
    server_socket.listen()

    print("Server listening on", (HOST, PORT))

    while True:
        client_socket, client_address = server_socket.accept()
        print("Connected by", client_address)
        with client_socket:
            while True:
                data = client_socket.recv(1024)
                if not data:
                    break
                print("Received:", data.decode())
                client_socket.sendall(data)

