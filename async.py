import threading
import urllib.request
import urllib.parse
import time
import math
import random

numberOfRequestInMinute = 100
url = "http://localhost:8080/BadanieRestApi/rest/process"
sizes = [200,500,1000,2000,5000]

class Timing:
    def __init__(self, start, stop):
        self.start = start
        self.stop = stop

calculatedInterval = math.ceil(numberOfRequestInMinute / 60)

def fetch_url(url):
	start = time.time()
	rightUrl = url+"?"+urllib.parse.urlencode({"size":random.choice(sizes)})
	urlHandler = urllib.request.urlopen(rightUrl)
	html = urlHandler.read()
	executionTime = Timing(start, time.time())
	times.append(executionTime)
	percentage = sum * 100 / 30000 
	print(round(percentage,2),"%",rightUrl)
 

def nextTime(rateParameter):
    return -math.log(1.0 - random.random()) / rateParameter

threads = []
times = []
sum = 0
while sum<(30*1000):
	t = threading.Thread(target=fetch_url, args=(url,))
	threads.append(t)
	value = nextTime( calculatedInterval / 1000 )
	time.sleep (value / 1000.0);
	t.start()
	sum += value
    

for thread in threads:
    thread.join()
 
for singleTime in times:
	print(singleTime.start,singleTime.stop) 