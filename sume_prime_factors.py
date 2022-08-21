def sum_of_prime_factors(n):
    sum = 0
    primerList = prime_finder(n)
    for i in primerList:
        if n%i == 0:
            sum = sum+i
    return sum


def prime_finder(n):
    primerList = []
    divs = []
    if(n>1):
        primerList = prime_finder(n-1)
    for i in range(1, n+1):
        if n%i==0:
            divs.append(i)
    if(len(divs)==2):
        primerList.append(n)
    return primerList

print(sum_of_prime_factors(91))