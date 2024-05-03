class Message:
    def __init__(self, messageType, timestamp, siteId):
        self.messageType = messageType
        self.timestamp = timestamp
        self.siteId = siteId

class Site:
    def __init__(self, siteId):
        self.siteId = siteId
        self.requesting = False
        self.executing = False
        self.timestamp = 0
        self.deferredQueue = []

    def requestCriticalSection(self, sites):
        self.requesting = True
        self.timestamp += 1
        for site in sites:
            if site.siteId != self.siteId:
                requestMessage = Message("REQUEST", self.timestamp, self.siteId)
                self.sendMessage(requestMessage, site)
        self.waitForReplies(sites)

    def sendMessage(self, message, destination):
        print(f"Site {self.siteId} sends {message.messageType} message to Site {destination.siteId}")
        destination.receiveMessage(message, self)

    def receiveMessage(self, message, sender):
        print(f"Site {self.siteId} receives {message.messageType} message from Site {sender.siteId}")
        if message.messageType == "REQUEST":
            if not self.requesting and not self.executing:
                self.sendMessage(Message("REPLY", 0, self.siteId), sender)
            elif self.requesting and message.timestamp < self.timestamp:
                self.deferredQueue.append(message)
        elif message.messageType == "REPLY":
            if self.requesting:
                self.deferredQueue = [m for m in self.deferredQueue if m.siteId != sender.siteId]
                if not self.deferredQueue:
                    self.executing = True
                    print(f"Site {self.siteId} enters critical section.")

    def waitForReplies(self, sites):
        repliesExpected = len(sites) - 1
        repliesReceived = 0
        while repliesReceived < repliesExpected:
            pass  # Wait for replies

    def releaseCriticalSection(self, sites):
        self.requesting = False
        self.executing = False
        for site in sites:
            if site.siteId != self.siteId:
                for message in self.deferredQueue:
                    self.sendMessage(Message("REPLY", 0, self.siteId), site)
        self.deferredQueue.clear()
        print(f"Site {self.siteId} releases critical section.")

def main():
    numberOfSites = int(input("Enter the number of sites: "))
    sites = [Site(i + 1) for i in range(numberOfSites)]
    for site in sites:
        site.requestCriticalSection(sites)
        site.releaseCriticalSection(sites)

if __name__ == "__main__":
    main()
