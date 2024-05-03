from collections import deque

class Site:
    def __init__(self, site_id, num_sites):
        self.site_id = site_id
        self.RN = [0] * num_sites
        self.LN = [0] * num_sites

class SuzukiKasami:
    def __init__(self, num_sites, initial_token_site_id):
        self.num_sites = num_sites
        self.sites = [Site(i + 1, num_sites) for i in range(num_sites)]
        self.current_token_holder = initial_token_site_id
        self.token_queue = deque()

    def simulate(self):
        while True:
            print(f"Site {self.current_token_holder} has the token.")
            print(f"Site {self.current_token_holder} executes critical section.")
            current_LNs = self.sites[self.current_token_holder - 1].LN
            current_RNs = self.sites[self.current_token_holder - 1].RN
            current_LNs[self.current_token_holder - 1] = current_RNs[self.current_token_holder - 1]
            for j in range(self.num_sites):
                if j + 1 not in self.token_queue and current_RNs[j] == current_LNs[j] + 1:
                    self.token_queue.append(j + 1)
            if self.token_queue:
                next_token_holder = self.token_queue.popleft()
                print(f"Token sent from Site {self.current_token_holder} to Site {next_token_holder}")
                self.current_token_holder = next_token_holder
            else:
                print(f"Site {self.current_token_holder} retains the token.")
            requesting_site_id = self.request_critical_section()
            if requesting_site_id is None:
                print("Invalid site ID.")
                return
            self.sites[requesting_site_id - 1].RN[requesting_site_id - 1] += 1
            for i in range(self.num_sites):
                if i != requesting_site_id - 1:
                    self.sites[i].RN[requesting_site_id - 1] = max(self.sites[i].RN[requesting_site_id - 1], self.sites[requesting_site_id - 1].RN[requesting_site_id - 1])
                    if self.current_token_holder == i + 1 and self.sites[i].RN[requesting_site_id - 1] == self.sites[i].LN[requesting_site_id - 1] + 1:
                        print(f"Token sent from Site {self.current_token_holder} to Site {requesting_site_id}")
                        self.current_token_holder = requesting_site_id

    def request_critical_section(self):
        while True:
            try:
                requesting_site_id = int(input(f"Enter the site ID that wants to enter the critical section (1-{self.num_sites}): "))
                if 1 <= requesting_site_id <= self.num_sites:
                    return requesting_site_id
            except ValueError:
                pass

def main():
    num_sites = int(input("Enter the number of sites: "))
    initial_token_site_id = int(input(f"Enter the site ID which initially has the token (1-{num_sites}): "))
    if not (1 <= initial_token_site_id <= num_sites):
        print("Invalid site ID for initial token holder.")
        return
    suzuki_kasami = SuzukiKasami(num_sites, initial_token_site_id)
    suzuki_kasami.simulate()

if __name__ == "__main__":
    main()
