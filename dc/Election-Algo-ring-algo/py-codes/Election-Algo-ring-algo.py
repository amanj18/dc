class Pro:
    def __init__(self, id):
        self.id = id
        self.act = True

class GFG:
    def __init__(self):
        self.TotalProcess = 0
        self.process = []
    
    def initialiseGFG(self):
        print("No of processes 5")
        self.TotalProcess = 5
        self.process = [Pro(i) for i in range(self.TotalProcess)]
    
    def Election(self):
        max_id_process_index = self.FetchMaximumActive()
        print("Process no " + str(self.process[max_id_process_index].id) + " fails")
        self.process[max_id_process_index].act = False

        # Initiating election by the highest ID process among active processes
        initialized_process = self.FetchMaximumActive()
        print("Election Initiated by " + str(self.process[initialized_process].id))

        # If there are no active processes, end the election
        if initialized_process == -1:
            print("No active processes. End of Election.")
            return
        
        for newer in range(initialized_process + 1, initialized_process + self.TotalProcess):
            newer %= self.TotalProcess
            if self.process[newer].act:
                print("Process " + str(self.process[initialized_process].id) + " pass Election(" + str(self.process[initialized_process].id) + ") to " + str(self.process[newer].id))
                if self.process[newer].id > self.process[initialized_process].id:
                    print("Process " + str(self.process[newer].id) + " responds 'OK'")
                else:
                    print("Process " + str(self.process[newer].id) + " doesn't respond")
        
        # Find the new coordinator among the active processes
        coord = self.FetchMaximumActive()
        if coord != -1:
            print("Process " + str(self.process[coord].id) + " becomes coordinator")
            old = coord
            newer = (old + 1) % self.TotalProcess
            while True:
                if self.process[newer].act:
                    print("Process " + str(self.process[old].id) + " pass Coordinator(" + str(coord) + ") message to process " + str(self.process[newer].id))
                    old = newer
                newer = (newer + 1) % self.TotalProcess
                if newer == coord:
                    print("End Of Election ")
                    break
        else:
            print("No active processes. End of Election.")
    
    def FetchMaximumActive(self):
        max_id = -1
        max_id_process_index = -1
        for i in range(self.TotalProcess):
            if self.process[i].act and self.process[i].id > max_id:
                max_id = self.process[i].id
                max_id_process_index = i
        return max_id_process_index

def main():
    obj = GFG()
    obj.initialiseGFG()
    obj.Election()

if __name__ == "__main__":
    main()
