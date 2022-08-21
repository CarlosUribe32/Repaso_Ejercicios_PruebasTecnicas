from LinkedList import *


def reverse_linked_list(linked_list):
  listNew = the_list(linked_list)
  return make_linked_list(listNew)

def the_list(linked_list):
  if linked_list == None:
    return []
  else:
    listNew = the_list(linked_list.next)
    listNew.append(linked_list.data)
    return listNew

print("Original")
demo_list = make_linked_list([4,8,15])
demo_list.print_linked_list()
print("Reversed")
reverse = reverse_linked_list(demo_list)

reverse.print_linked_list()
print("Original Unchanged")
demo_list.print_linked_list()
