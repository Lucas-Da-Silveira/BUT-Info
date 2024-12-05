<?php
abstract class Employe {
    protected $nom;
    protected $prenom;
    protected $dateNaissance;
    protected $numeroSecuSociale;

    protected static $nbEmployes = 0;

    public function __construct($nom, $prenom, $dateNaissance, $numeroSecuSociale) {
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->dateNaissance = $dateNaissance;
        $this->numeroSecuSociale = $numeroSecuSociale;

        self::$nbEmployes++;
    }

    abstract public function revenu(): float;

    // Getters
    public function getNom() {
        return $this->nom;
    }

    public function getPrenom() {
        return $this->prenom;
    }

    public function getDateNaissance() {
        return $this->dateNaissance;
    }

    public function getNumeroSecuSociale() {
        return $this->numeroSecuSociale;
    }

    // Setters
    public function setNom($nom) {
        $this->nom = $nom;
    }

    public function setPrenom($prenom) {
        $this->prenom = $prenom;
    }

    public function setDateNaissance($dateNaissance) {
        $this->dateNaissance = $dateNaissance;
    }

    public function setNumeroSecuSociale($numeroSecuSociale) {
        $this->numeroSecuSociale = $numeroSecuSociale;
    }

    public static function getNbEmployes(): int {
        return self::$nbEmployes;
    }

    public function __destruct() {
        self::$nbEmployes--;
    }
}
?>