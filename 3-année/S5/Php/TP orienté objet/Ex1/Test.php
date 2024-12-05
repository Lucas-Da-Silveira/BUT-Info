<?php
require_once 'EmployeSalary.class.php';
require_once 'EmployeParHeure.class.php';
require_once 'EmployeParCommission.class.php';
require_once 'BasePlusEmployeParCommission.class.php';


$salarie = new EmployeSalarie("Dupont", "Jean", "1985-05-15", "123456789012345", 500 / 52); 

$employeHeure = new EmployeParHeure("Martin", "Claire", "1990-08-20", "987654321098765", 20, 45);

$employeCommission = new EmployeParCommission("Bernard", "Alice", "1988-03-12", "123123123123123", 6000, 0.1);

$employeBaseCommission = new BasePlusEmployeParCommission("Lemoine", "Sophie", "1992-07-22", "456456456456456", 5000, 0.1, 400);

echo "Revenu du salarié : " . $salarie->revenu() . "€\n";
echo "Revenu de l'employé par heure : " . $employeHeure->revenu() . "€\n";
echo "Revenu de l'employé par commission : " . $employeCommission->revenu() . "€\n";
echo "Revenu de l'employé base + commission : " . $employeBaseCommission->revenu() . "€\n";

// Nombre total d'employés
echo "Nombre d'employés créés : " . Employe::getNbEmployes() . "\n";

// Libération des objets (optionnel, PHP le fait automatiquement)
unset($salarie, $employeHeure, $employeCommission, $employeBaseCommission);
