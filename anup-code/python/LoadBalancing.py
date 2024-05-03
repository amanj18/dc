class RoundRobinLoadBalancer:
    def __init__(self, numServers):
        self.numServers = numServers
        self.servers = [[] for _ in range(numServers)]

    def addProcesses(self, processes):
        currentIndex = 0
        for process in processes:
            self.servers[currentIndex].append(process)
            currentIndex = (currentIndex + 1) % self.numServers  # Round robin distribution

    def printProcesses(self):
        for i, server in enumerate(self.servers):
            print(f"Server {i + 1} Processes: {server}")

def main():
    # Initial processes in the servers
    initialProcesses = [1, 2, 3, 4, 5, 6, 7]
    # Number of servers
    numServers = 4
    loadBalancer = RoundRobinLoadBalancer(numServers)
    print("Processes before balancing:")
    print(*initialProcesses)
    loadBalancer.addProcesses(initialProcesses)
    print("\nProcesses after balancing:")
    loadBalancer.printProcesses()

if __name__ == "__main__":
    main()



# Output 
'''
Processes before balancing:
1 2 3 4 5 6 7

Processes after balancing:
Server 1 Processes: [1, 5]
Server 2 Processes: [2, 6]
Server 3 Processes: [3, 7]
Server 4 Processes: [4]

'''
