import numpy as np

#question 1

# M1= np.random.randint(1,100, size = (2,2))

# M2=np.random.randint(1,100, size = (3,2))

# print(f"Matrix A:\n {M1}\n")
# print(f"Matrix B:\n {M2}\n")

# def produit_matrice(M1,M2):
#     global M3
    
#     if M1.shape[1] == M2.shape[0]:
#         M3 = np.zeros((M1.shape[0] == M2.shape[1]), dtype = int)
#         for i in range(len(M1)):
#             for j in range(len(M2[0])):
#                 for k in range(len(M1[0])):
#                     M3[i,j] += M1[i,k]*M2[k,j]
#         print(produit_matrice(M1,M2))
#     else: 
#         print("Les matrices ne sont pas compatible")


def matrice(n):

    M1=[]
    M2=[]

    for i in range(0,n):
        M1.append(0)
    for i in range(0,n):
        M2.append(M1)

    print(M2)

    return M2

def produit_matrice(A,B):
    if len(A[0]) != len(B):
        return[]
    
    M3 = matrice(len(A),len(B))  # type: ignore

    for i in range(len(A)):
        for j in range(len(B[0])):
            for k in range(len(A[0])):
                M3[i][j] += A[i][k]*B[k][j]
    print(M3)

    return M3

    
    
    

   