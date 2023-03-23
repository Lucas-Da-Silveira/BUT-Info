import math
from matplotlib import pyplot as plt
import numpy as np


def flocon(n):
    x = [0, 0.5, 1, 0]
    y = [0, math.sqrt(3) / 2,0, 0]
    xtemp=[]
    ytemp=[]

    for k in range(n):
        for i in range(len(x)-1):
            xtemp.append(x[i])
            ytemp.append(y[i])
            a=x[i+1]-x[i]
            b=y[i+1]-y[i]

            x1 =x[i]+a/3
            y1=y[i]+b/3
            x3=x[i]+2*a/3
            y3=y[i]+2*b/3
            xm=x[i]+a/2
            ym=y[i]+b/2
            x2=xm-b*math.sqrt(3)/6
            y2=ym+a*math.sqrt(3)/6

            xtemp.append(x1)
            xtemp.append(x2)
            xtemp.append(x3)
            ytemp.append(y1)
            ytemp.append(y2)
            ytemp.append(y3)

        xtemp.append(x[0])
        ytemp.append(y[0])

        x=xtemp
        y=ytemp
        xtemp=[]
        ytemp=[]
    plt.axis('equal')
    plt.plot(x, y)
    plt.show()

flocon(10)