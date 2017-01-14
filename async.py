import threading
import urllib.request
import urllib.parse
import time
import math
import random
import csv

numberOfRequestInMinute = 1000000
url = "http://localhost:8080/BadanieRestApi/rest/process"
possibleUrls = ['bubble', 'counting']
#possibleUrls = ['counting']
sizes = [200,500,1000,2000,5000]

startSoftware = time.time() * 1000.0

class Timing:
	def __init__(self, start, stop, url):
		self.url = url
		self.start = start
		self.stop = stop

calculatedInterval = math.ceil(numberOfRequestInMinute / 60)

def fetch_url(url):
	start = time.time()*1000.0
	randomUrl = random.choice(possibleUrls)
	rightUrl = url+"/"+randomUrl+"/"+str(random.choice(sizes))
	#print(rightUrl)
	#rightUrl = url+random.choice(possibleUrls)+"?"+urllib.parse.urlencode({"size":random.choice(sizes)})

	urlHandler = urllib.request.urlopen(rightUrl)
	html = urlHandler.read()
	executionTime = Timing(start, time.time()*1000.0, randomUrl)
	times.append(executionTime)
	percentage = sum * 100 / 30000 
	print(round(percentage,2),"%",rightUrl,start-time.time())


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

with open("_".join(possibleUrls)+str(numberOfRequestInMinute)+'.csv', 'a') as csvfile:
	writer = csv.writer(csvfile, delimiter=',', lineterminator='\n')
	for row in times:
		writer.writerow( [ "{:.2f}".format(row.start-startSoftware),"{:.2f}".format(row.stop-startSoftware) ])