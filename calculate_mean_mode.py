def stats_finder(array):
    mean = 0
    mode = 0
    cantNum = 0
    for i in array:
        mean += i
        if(mode!=i and cantNum<=array.count(i)):
            mode = i
            cantNum = array.count(i)
    return [mean/len(array), mode]

print(stats_finder([500, 400, 400, 375, 300, 350, 325, 300]))