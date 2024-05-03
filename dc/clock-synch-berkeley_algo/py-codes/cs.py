from datetime import datetime, timedelta

def berkeley_algorithm(nodes):
    # Calculate the average time (converted to timestamps)
    average_time = sum(node['time'].timestamp() for node in nodes) / len(nodes)
    # Calculate the time difference for each node
    for node in nodes:
        node['offset'] = average_time - node['time'].timestamp()
        node['synchronized_time'] = node['time'] + timedelta(seconds=node['offset'])

def synchronize_clocks(nodes):
    for node in nodes:
        # Synchronize the clock for each node
        node['synchronized_time'] = node['time'] + timedelta(seconds=node['offset'])

def print_node_times(nodes):
    for node in nodes:
        print(f"Node {node['id']} - Local Time: {node['time']}, Synchronized Time: {node.get('synchronized_time', 'Not synchronized')}")

if __name__ == "__main__":
    # Example with three nodes
    nodes = [
        {'id': 1, 'time': datetime.now()},
        {'id': 2, 'time': datetime.now() + timedelta(seconds=5)},
        {'id': 3, 'time': datetime.now() - timedelta(seconds=3)}
    ]
    print("Original Node Times:")
    print_node_times(nodes)
    berkeley_algorithm(nodes)
    synchronize_clocks(nodes)
    print("\nAfter Berkeley Algorithm:")
    print_node_times(nodes)
