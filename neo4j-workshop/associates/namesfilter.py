__author__ = 'partyks'

dict = {}

with open("names.csv", "r") as file:
    for line in file:
        username = line.strip().split(',')[0]
        dict[username] = line

with open("names_u.csv", "wt") as file:
    for username in dict:
        file.write(dict[username])

