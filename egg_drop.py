#It's not mine
import random

def egg_drop(n):

    num_floors: int = n
    egg_pool: int = 2
    num_drops: int = 0

    # A random critical floor each time the function runs, so we have something unknown to search for
    critical_floor: int = random.randrange(1, num_floors + 1)

    # setting up the initial full range test window
    test_window = [1, num_floors]

    print('floors to test: %s' % test_window)
    print('--------------------------------')

    while True:
        # if we are out of eggs for testing, we can't go on
        if egg_pool == 0:
            print('Oh no! We are out of eggs :(')
            break

        num_drops += 1

        # handling cases between the first and last floor
        if test_window[1] > 2:
            middle_floor = int((test_window[0] + test_window[1]) / 2)

            # testing if the egg breaks or not

            # egg broke
            if middle_floor >= critical_floor:
                # narrowing down the test window
                test_window[1] = middle_floor
                egg_pool -= 1

            # egg didn't break
            else:
                # narrowing down the test window
                test_window[0] = middle_floor

        # special handling of lowest floors
        elif test_window == [1, 2]:

            # when we are down to floor 1 and 2, we check if the egg breaks at floor 1. If it does, we know the
            # critical floor is floor one. If it doesn't, we know it's floor 2
            if critical_floor == 1:
                test_window[1] = 1
                egg_pool -= 1
            else:
                test_window[1] = 2

            break

        print('%s Current testing window: %s' % (num_drops, test_window))

        # loop breaking logic
        if test_window[1] - test_window[0] <= 1 and test_window[1] > 2:
            break

    print()
    print('Critical floor suggested : %s' % test_window[1])
    print('Critical floor revealed  : %s' % critical_floor)
    print()
    print('Eggs left for eating: %s' % egg_pool)

    return num_drops


print(egg_drop(50))