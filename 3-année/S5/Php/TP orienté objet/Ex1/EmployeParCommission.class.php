<?php

require_once 'Employe.class.php';

class EmployeParCommission extends Employe {
    private $totalVente;
    private $tauxCommission;

    public function __construct($nom, $prenom, $dateNaissance, $numeroSecuSociale, $totalVente, $tauxCommission) {
        parent::__construct($nom, $prenom, $dateNaissance, $numeroSecuSociale);
        $this->totalVente = $totalVente;
        $this->tauxCommission = $tauxCommission;
    }

    public function getTotalVente(): float {
        return $this->totalVente;
    }

    public function setTotalVente(float $totalVente): void {
        $this->totalVente = $totalVente;
    }

    public function getTauxCommission(): float {
        return $this->tauxCommission;
    }

    public function setTauxCommission(float $tauxCommission): void {
        $this->tauxCommission = $tauxCommission;
    }

    public function revenu(): float {
        return $this->totalVente * $this->tauxCommission;
    }
}
