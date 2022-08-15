class TreeNode():
    def __init__(self, value):
        self.right = None
        self.left = None
        self.value = value
  
    def __str__(self):
        return f"{self.value}->{self.left}\n{self.value}->{self.right}"


def balanced_bst(a):
    if len(a) < 1: 
        return None
    else:
        root = TreeNode(a[len(a)//2])
        root.left = balanced_bst(a[:len(a)//2])
        root.right = balanced_bst(a[len(a)//2 + 1:])
        return root
    


a = [1,2,3,4,5,6,7,8]
balanced_node = balanced_bst(a)
print(balanced_node)