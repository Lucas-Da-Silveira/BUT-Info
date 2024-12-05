<?php

require_once 'Employe.class.php';

class EmployeSalarie extends Employe {
    private $salaireHebdo;

    // Constructeur
    public function __construct($nom, $prenom, $dateNaissance, $numeroSecuSociale, $salaireHebdo) {
        parent::__construct($nom, $prenom, $dateNaissance, $numeroSecuSociale);
        $this->salaireHebdo = $salaireHebdo;
    }

    public function getSalaireHebdo(): float {
        return $this->salaireHebdo;
    }

    public function setSalaireHebdo(float $salaireHebdo): void {
        $this->salaireHebdo = $salaireHebdo;
    }

    public function revenu(): float {
        return $this->salaireHebdo * 52;
    }
}
