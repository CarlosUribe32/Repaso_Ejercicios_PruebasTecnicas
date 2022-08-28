import random

def egg_drop(n):
    stories = n
    dropps = 0
    f = random.randrange(1, stories+1)
    eggStatus = [0,0]
    while(True):
        if(eggStatus[1]<f):
            eggStatus[0] = eggStatus[1]
            if(n>12):
                eggStatus[1] = eggStatus[1]+13
            else:
                eggStatus[1] = eggStatus[1]+n
            dropps = dropps+1
        else:
            eggStatus[0] = eggStatus[0]+1
            if(eggStatus[0]<eggStatus[1]):
                dropps = dropps+1
            if(eggStatus[0]==f):
                break 
    return dropps   

    

print(egg_drop(1))