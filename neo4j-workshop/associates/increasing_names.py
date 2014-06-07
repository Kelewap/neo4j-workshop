__author__ = 'partyks'

output = open('./../names_u2.csv', 'w')

with open('./../names_u.csv', 'r') as file:
    for line in file:
        (username, firstname, secondname) = line.strip().split(',')
        for i in range(30):
            usernameDig = username + str(i)
            output.write(usernameDig + ',' + firstname + ',' + secondname + '\n')
