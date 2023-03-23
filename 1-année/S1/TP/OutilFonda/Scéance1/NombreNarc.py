s = 0
nbr = input('Quel nombre tester ?')

for chiffre in nbr:
	s += int(chiffre)**len(nbr)

if str(s) == nbr:
	print(f"{nbr} est narcissique")
else : 
	print(f"{nbr} n'est pas narcissique")
