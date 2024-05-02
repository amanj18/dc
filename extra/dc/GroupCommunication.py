import multiprocessing

def worker_function(worker_id, shared_data):
    print(f"Worker {worker_id} received: {shared_data.value}")

if __name__ == "__main__":
    # Shared data among processes
    shared_data = multiprocessing.Value('i', 10)

    # Creating multiple processes
    num_workers = 3
    processes = []
    for i in range(num_workers):
        process = multiprocessing.Process(target=worker_function, args=(i, shared_data))
        processes.append(process)
        process.start()

    # Broadcasting data to all processes
    shared_data.value = 42

    # Waiting for all processes to finish
    for process in processes:
        process.join()


#Python code 
#Same code run on VScode and Online compiler of Python*/
# Run as py filename.py
#Worker 0 received: 42
#Worker 1 received: 42
#Worker 2 received: 42