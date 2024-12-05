<?php

require_once 'EmployeParCommission.class.php';

class BasePlusEmployeParCommission extends EmployeParCommission {
    private $salaireBase;
    public function __construct($nom, $prenom, $dateNaissance, $numeroSecuSociale, $totalVente, $tauxCommission, $salaireBase) {
        parent::__construct($nom, $prenom, $dateNaissance, $numeroSecuSociale, $totalVente, $tauxCommission);
        $this->salaireBase = $salaireBase;
    }

    public function getSalaireBase(): float {
        return $this->salaireBase;
    }

    public function setSalaireBase(float $salaireBase): void {
        $this->salaireBase = $salaireBase;
    }

    public function revenu(): float {
        return $this->salaireBase + ($this->getTotalVente() * $this->getTauxCommission());
    }
}
