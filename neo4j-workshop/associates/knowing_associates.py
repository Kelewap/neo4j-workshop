__author__ = 'partyks'
from collections import namedtuple
import random

levels = ["a1", "a2", "b1", "b2", "c1", "c2"]

class AssociationAbstract(object):
    @staticmethod
    def generateAssociations(list1, list2, minimum, maximum):
        ret = []
        for element in list1:
            how_many = random.randint(minimum, maximum)
            associates = random.sample(list2, how_many)
            for associated in associates:
                if associated is not element:
                    ret.append((element, associated))
        return ret

def persistAssociates(filename, associateTupples):
    with open(filename, 'wt') as file:
        for association in associateTupples:
            print >> file, ",".join(association)
def getEntities(filename):
    names = []
    with open(filename, 'r') as file:
        for line in file:
            names.append(line.strip().split(',')[0])
    return names


names = getEntities("./../names_u.csv")
knows = AssociationAbstract.generateAssociations(names, names, 0 ,32)
persistAssociates("./../knows.csv", knows)




schools = getEntities("./../schools.csv")

studying = AssociationAbstract.generateAssociations(names, schools, 0, 1)
persistAssociates("./../studenschool.csv", studying)

graduated = AssociationAbstract.generateAssociations(names, schools, 0, 1)
persistAssociates("./../graduated.csv", graduated)

categories = getEntities("./../workCategories.csv")

workon = AssociationAbstract.generateAssociations(names, categories, 1 ,1)
workon = [associate + (str(random.randint(1,8)),) for associate in workon]
persistAssociates("./../workon.csv", workon)

languages = getEntities("./../languages.csv")
lAssociations = AssociationAbstract.generateAssociations(names, languages, 1, 3)
lAssociationsWithLevels = []
for association in lAssociations:
    lAssociationsWithLevels.append(association + tuple(random.sample(levels, 1)))
persistAssociates("./../personLanguages.csv", lAssociationsWithLevels)