def diviseur(n):
	s=0
	for k in range (1,n,1):
		if n%k == 0:
			s=s+k
	return s
	
def amicaux(m,n):
	a=diviseur(n)
	b=diviseur(m)
	if a==n and b==m:
		return True
	else:
		return False
		
Nombre1=int(input("Entrez un nombre :"))
Nombre2=int(input("Entrez un nombre :"))

print(amicaux(Nombre1,Nombre2))
