import time
import threading
import requests
import json
import sys
import signal
import argparse
# host = "35.223.1.166"
# host = "34.69.236.48"
# host = "35.232.118.0"
uri = "/version"

response_recieved = ""
    
total_number_of_request = 0
green_version_count = 0
blue_version_count = 0
unknown_version_count = 0    

blue_version_string = ""
green_version_string = ""

def send_request_and_process():

    global green_version_count
    global blue_version_count
    global unknown_version_count   
    global total_number_of_request
    global response_recieved
    global blue_version_string
    global green_version_string

    response_recieved = requests.get(url=url)
    version_object = json.loads(response_recieved.text)
    
    if response_recieved.status_code == 200:
        total_number_of_request += 1
        if (blue_version_string != "" and version_object['release'] == "BLUE" and blue_version_string != version_object['version']) \
            or (green_version_string != "" and version_object['release'] == "GREEN" and green_version_string != version_object['version']):
            green_version_count = 0
            blue_version_count = 0
        
        if version_object['release'] == "GREEN" :
            green_version_count += 1
            green_version_string = version_object['version']
        elif version_object['release'] == "BLUE" :
            blue_version_count += 1
            blue_version_string = version_object['version']
        else:
            unknown_version_count += 1
    else:
        sys.stdout.write("Something went wrong!!!. Check endpoint at url " + url)

def signal_handler(sig, frame):
    sys.stdout.write('\nSIGINT recieved, Goodbye.\n')
    sys.exit(0)
signal.signal(signal.SIGINT, signal_handler)

def main():
    while(True):
        send_request_and_process()
        sys.stdout.write("No. of request - {} \t".format(str(total_number_of_request)))
        if green_version_count == 0 :
            sys.stdout.write("Green - 0% ")
            sys.stdout.write("Blue - {}%  ".format(str(int(blue_version_count/total_number_of_request * 100))))
        elif blue_version_count == 0:
            sys.stdout.write("Green - {}% ".format(str(int(green_version_count/total_number_of_request * 100))))
            sys.stdout.write("Blue - 0% ")
        else:   
            sys.stdout.write("Green - {}% ".format(str(int(green_version_count/total_number_of_request * 100))))
            sys.stdout.write("Blue - {}%  ".format(str(int(blue_version_count/total_number_of_request * 100))))
        sys.stdout.write("Responses - {} \r".format(response_recieved.text))

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Version Analyzer')
    parser.add_argument('--threads', type=int, help='Number of threads', default=1)
    parser.add_argument('--host', type=str, help='Hostname to test ', required=True)
    parser.add_argument('--port', type=str, help='Port to use ', default='80')
    args = parser.parse_args()
    host = args.host
    port = args.port
    url = "http://" + host + ":" + port + uri
    for i in range(0, args.threads):
        x = threading.Thread(target=main)
        x.setDaemon(daemonic=True)
        x.start()
    while True:
        time.sleep(0.5)