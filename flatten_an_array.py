def flatten_array(arr):
    newArr = []
    for i in arr:
        if(isinstance(i, list)):
            newArr.extend(i)
        else:
            newArr.append(i)
    return newArr

print(flatten_array([1, 2, [3, 4, 5], 6, [7, 8], 9]))