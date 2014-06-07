__author__ = 'partyks'


output = open('inserts.sql', 'w')
peopleIds = {}
languageIds = {}
projectIds = {}
categoryIds = {}
schoolsIds = {}

id = 1



with open('./../names_u.csv', 'r') as people:
    output.write('COPY people FROM stdin;\n')
    for line in people:
        (username, firstname, lastname) = line.strip().split(',')
        name = firstname + ' ' + lastname

        peopleIds[username] = id

        insert = str(id) + '\t' + name + '\t' + username + '\n'
        output.write(insert)
        id = id + 1
    output.write('\.\n')

with open('./../knows.csv', 'r') as knows:
    output.write('COPY knows FROM stdin;\n')
    for line in knows:
        (username1, username2) = line.strip().split(',')
        output.write(str(peopleIds[username1]) + '\t' + str(peopleIds[username2]) + '\n')
    output.write('\.\n')

id = 1
with open('./../languages.csv', 'r') as languages:
    output.write('COPY language FROM stdin;\n')
    for line in languages:
        (abrv, name) = line.strip().split(',')
        output.write(str(id) + '\t' + abrv + '\t' + name + '\n')
        languageIds[abrv] = id
        id = id + 1
    output.write('\.\n')



with open('./../personLanguages.csv', 'r') as personLanguages:
    output.write('COPY person_language FROM stdin;\n')
    for line in personLanguages:
        (user, language, level) =  line.strip().split(',')
        output.write(str(peopleIds[user]) + '\t' + str(languageIds[language]) + '\t0\n')
    output.write('\.\n')

id =1

with open('./../projects.csv', 'r') as projects:
    output.write('COPY project FROM stdin;\n')
    for line in projects:
        (name, timestamp1, timestamp2) = line.strip().split(',')
        output.write(str(id) + '\t' + name + '\t' + timestamp1 + '\t' + timestamp2 + '\n')
        projectIds[name] = id
        id = id + 1
    output.write('\.\n')

with open('./../personProject.csv', 'r') as personProject:
    output.write('COPY person_project FROM stdin;\n')
    for line in personProject:
        (name, user) = line.strip().split(',')
        output.write(str(peopleIds[user]) + '\t' + str(projectIds[name]) + '\n')
    output.write('\.\n')


id = 1
with open('./../workCategories.csv', 'r') as categories:
    output.write('COPY category FROM stdin;\n')
    for line in categories:
        (abrv, name) = line.strip().split(',')
        output.write(str(id) + '\t' + abrv + '\t' + name + '\n')
        categoryIds[abrv] = id
        id = id + 1
    output.write('\.\n')

with open('./../projectCategories.csv', 'r') as projectCategories:
    output.write('COPY person_project FROM stdin;\n')
    for line in projectCategories:
        (name, category) = line.strip().split(',')
        output.write(str(projectIds[name]) + '\t' + str(categoryIds[category]) + '\n')
    output.write('\.\n')

id = 1

with open('./../schools.csv', 'r') as schools:
    output.write('COPY school FROM stdin;\n')
    for line in schools:
        (abrv, name) = line.strip().split(',')
        output.write(str(id) + '\t' + abrv + '\t' + name + '\n')
        schoolsIds[abrv] = id
        id = id + 1
    output.write('\.\n')


with open('./../studenschool.csv', 'r') as projectCategories:
    output.write('COPY person_studying FROM stdin;\n')
    for line in projectCategories:
        (user, school) = line.strip().split(',')
        output.write(str(peopleIds[user]) + '\t' + str(schoolsIds[school]) + '\n')
    output.write('\.\n')

with open('./../graduated.csv', 'r') as projectCategories:
    output.write('COPY person_graduated FROM stdin;\n')
    for line in projectCategories:
        (user, school) = line.strip().split(',')
        output.write(str(peopleIds[user]) + '\t' + str(schoolsIds[school]) + '\n')
    output.write('\.\n')


with open('./../workon.csv', 'r') as projectCategories:
    output.write('COPY person_category FROM stdin;\n')
    for line in projectCategories:
        (user, category, asdf) = line.strip().split(',')
        output.write(str(peopleIds[user]) + '\t' + str(categoryIds[category]) + '\n')
    output.write('\.\n')


