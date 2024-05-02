numProcesses = int(input("Enter number of processes: "))
numCS = int(input("Enter number of processes that want to enter CS: "))

# timestamp: process
processMap = {}
timestamps = []

# Input process ID and timestamp for each process
for i in range(numCS):
    process, timestamp = input(f"Enter the Process ID and Timestamp of process {i+1}: ").split()
    processMap[int(timestamp)] = int(process)
    timestamps.append(int(timestamp))
    
print()

# Sort timestamps in ascending order
timestamps.sort()

# Print requests made by each process
for time in timestamps:
    processCS = processMap[time]
    for i in range(numProcesses):
        if processCS != i:
            print(f"Process {processCS} has requested Process {i}")
    print()

# Print acceptances and CS entry/exit for each process
for time in timestamps:
    processCS = processMap[time]
    for i in range(numProcesses):
        if processCS != i:
            print(f"Process {i} has accepted the request of Process {processCS}")
    print()
    print(f'Process {processCS} has now entered the CS')
    print(f'Process {processCS} has now exited the CS')
    print()


# PS C:\Users\Vedika\Desktop\DC> py RicartAggrawala.py
#Enter number of processes: 3
#Enter number of processes that want to enter CS: 1
#Enter the Process ID and Timestamp of process 1: 0 1

#Process 0 has requested Process 1
#Process 0 has requested Process 2

#Process 1 has accepted the request of Process 0
#Process 2 has accepted the request of Process 0

#Process 0 has now entered the CS
#Process 0 has now exited the CS