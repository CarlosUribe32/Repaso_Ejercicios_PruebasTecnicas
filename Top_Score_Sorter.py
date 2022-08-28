
from audioop import reverse


def score_sorter(array, top_score):
    array.sort(reverse=True)
    for i in array:
        if(i>top_score):
            array.remove(i)
    return array


score_list = [1, 2, 3, 9999, 13]
top = 10000
print(score_sorter(score_list, top))