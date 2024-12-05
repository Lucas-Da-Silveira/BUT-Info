<?php

require_once 'Employe.class.php';

class EmployeParHeure extends Employe {
    private $salaireH;
    private $heures;

    public function __construct($nom, $prenom, $dateNaissance, $numeroSecuSociale, $salaireH, $heures) {
        parent::__construct($nom, $prenom, $dateNaissance, $numeroSecuSociale);
        $this->salaireH = $salaireH;
        $this->heures = $heures;
    }

    public function getSalaireH(): float {
        return $this->salaireH;
    }

    public function setSalaireH(float $salaireH): void {
        $this->salaireH = $salaireH;
    }

    public function getHeures(): float {
        return $this->heures;
    }

    public function setHeures(float $heures): void {
        $this->heures = $heures;
    }

    public function revenu(): float {
        if ($this->heures <= 40) {
            return $this->salaireH * $this->heures;
        } else {
            $heuresSupplementaires = $this->heures - 40;
            return (40 * $this->salaireH) + ($heuresSupplementaires * 1.5 * $this->salaireH);
        }
    }
}
