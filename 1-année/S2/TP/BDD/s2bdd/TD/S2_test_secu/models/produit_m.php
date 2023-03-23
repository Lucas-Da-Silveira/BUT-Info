<?php

class Produit_m{

    private $db;

    public function __construct(){
        $MaConnexion = new Connexion();
        $this->db = $MaConnexion->connect();
    }

    function getAllProduits(){
        $requete="SELECT p.id, p.typeProduit_id, p.nom, p.prix, p.photo
        FROM produits as p ORDER BY p.nom;";
        $select = $this->db->query($requete);
        $results = $select->fetchAll();
        return $results;
    }

    function insertUnProduit($donnees){
        $requete="INSERT INTO produits (id,nom,typeProduit_id,prix,photo) VALUES
        (NULL,'".$donnees['nom']."',".$donnees['typeProduit_id'].",'".$donnees['prix']."','".$donnees['photo']."');";
        try {
            $nbRes = $this->db->exec($requete);
            return $nbRes;
            } 
        catch ( Exception $e ) {
                echo "Erreur methode insertUnProduit : ", $e->getMessage();
            }
    }




    function deleteUnProduit($id){
        $requete="DELETE
        FROM produits WHERE id=".$id." LIMIT 1;";
        try {
            $nbRes = $this->db->exec($requete);
            return $nbRes;
            } 
        catch ( Exception $e ) {
                echo "Erreur methode readUnProduit : ", $e->getMessage();
            }
    }
    
    function readUnProduit($id){
        /*$requete="SELECT id,typeProduit_id,nom,prix,photo
        FROM produits WHERE id=".$id." LIMIT 1;";*/
        $requete="SELECT id,typeProduit_id,nom,prix,photo
        FROM produits WHERE id=:id LIMIT 1;";
        try {
            /*$select = $this->db->query($requete);
            $result = $select->fetchAll();
            return $result[0];*/
            $prep=$this->db->prepare($requete);
            $prep->bindParam(':id',$id,PDO::PARAM_INT);
            $prep->execute();
            $result = $prep->fetch();
            return $result;
            } 
        catch ( Exception $e ) {
                echo "Erreur methode readUnProduit : ", $e->getMessage();
            }
    }
    function updateUnProduit($id,$donnees){
        $requete="UPDATE produits SET typeProduit_id=".$donnees['typeProduit_id']." ,
        nom='".$donnees['nom']."', prix='".$donnees['prix']."' ,
        photo='".$donnees['photo']."' WHERE id=".$id.";";
        try {
            $nbRes = $this->db->exec($requete);
            } 
        catch ( Exception $e ) {
                echo "Erreur methode updateUnProduit : ", $e->getMessage();
            }
    }


    function init_bdd(){
        print('init bdd');
        $requete="DROP TABLE IF EXISTS typeProduits,produits;
";
        try {
            $nbRes = $this->db->exec($requete);
            } 
        catch ( Exception $e ) {
                echo "Erreur creation BDD : ", $e->getMessage();
            }
        $requete="
CREATE TABLE produits (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  typeProduit_id INT,
  nom varchar(255),
  prix float(19,4),
  photo varchar(255)
)  DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
";
        try {
            $nbRes = $this->db->exec($requete);
        }
        catch ( Exception $e ) {
            echo "Erreur creation BDD : ", $e->getMessage();
        }
        $requete="
INSERT INTO produits (id, typeProduit_id, nom, prix,photo)VALUES 
 (NULL, 1, 'salade', '2.00','salade.jpeg'),
 (NULL, 1, 'choux', '2.50','choux.jpeg'),
 (NULL, 1, 'pomme de terre', '1.50','pommeterre.jpeg'),
 (NULL, 1, 'tomate', '1.50','tomate.jpeg'),
 (NULL, 1, 'haricot', '6.50','haricot.jpeg'),
 (NULL, 1, 'potiron', '3.00','potiron.jpeg');

DROP TABLE IF EXISTS typeProduits;
create table typeProduits
(
id int(10) not null PRIMARY KEY ,
libelle varchar(50) 
)  DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
insert into typeProduits values (1,'légume');
insert into typeProduits values (2,'fruit');
insert into typeProduits values (3,'autre');
";
        try {
            $nbRes = $this->db->exec($requete);
        }
        catch ( Exception $e ) {
            echo "Erreur creation BDD : ", $e->getMessage();
        }
    }


}
