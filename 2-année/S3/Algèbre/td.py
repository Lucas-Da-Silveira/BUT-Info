import numpy as np

Mcan = np.array([[3,-1,-2],
                [2,0,-2],
                [2,-1,-1]])

Tab =np.linalg.eig(Mcan)
print(Tab)


Mbb = np.array([[1,0,0],
                [0,0,0],
                [0,0,1]])

# P = np.array([[0.72760688, -0.57735027,  0.7407065 ],
#               [0.48507125, -0.57735027,  0.19653498],
#               [0.48507125, -0.57735027,  0.64243901]])

P =Tab[1]

A = P.dot(Mbb.dot(np.linalg.inv(P)))

print(A)