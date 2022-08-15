def getX(x, nums):
    nums.sort()
    if(x<=len(nums) and x>0):
        return nums[x-1]
    else:
        return 0

print(getX(-1, [6, 3, -1, 5,4,10,9]))