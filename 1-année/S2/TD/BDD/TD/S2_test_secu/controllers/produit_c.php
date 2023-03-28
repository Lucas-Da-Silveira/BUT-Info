<?php
class Produit
{
    private $instanceModelProduit;

    public function __construct(){
        include("models/produit_m.php");
        $this->instanceModelProduit = new Produit_m();

        include('helper/helperViewPrix.php');
    }

    public function index()  
    {
        include("views/v_head.php");
        include('views/v_menu.php');
        include("views/v_foot.php");
    }
    public function afficherProduits()  
    {

        include("views/v_head.php");
        include('views/v_menu.php');
        $data=$this->instanceModelProduit->getAllProduits();
        include("views/v_table_produit.php");
        include("views/v_foot.php");
    }
    public function creerProduit()  
    {
        include("views/v_head.php");
        include('views/v_menu.php');
        include('views/v_form_create_produit.php');
        include("views/v_foot.php");
    }

    public function validFormCreerProduit()  
    {
        $donnees['nom']=$_POST['nom'];
        $donnees['typeProduit_id']=$_POST['typeProduit_id'];
        $donnees['prix']=$_POST['prix'];
        $donnees['photo']=$_POST['photo'];
        $this->instanceModelProduit->insertUnProduit($donnees);
        header("Location: ".BASE_URL."app.php/Produit/afficherProduits");
    }

    public function supprimerProduit($id='')  
    {
        $this->instanceModelProduit->deleteUnProduit($id);
        header("Location: ".BASE_URL."app.php/Produit/afficherProduits");
    }


    public function modifierProduit($id='')
    {
        include("views/v_head.php");
        include("views/v_menu.php");
        $donnees=$this->instanceModelProduit->readUnProduit($id);
        include("views/v_form_update_produit.php");
        include("views/v_foot.php");
    }
    public function validFormModifierProduit()  
    {
        $id=$_POST['id']; // 
        $donnees['nom']=$_POST['nom']; // evite injection js ...
        $donnees['typeProduit_id']=$_POST['typeProduit_id'];
        $donnees['prix']=$_POST['prix'];
        $donnees['photo']=$_POST['photo'];

        $donnees=$this->instanceModelProduit->updateUnProduit($id,$donnees);
        header("Location: ".BASE_URL."app.php/Produit/afficherProduits");
    }
    
    public function init_BDD()
    {
        print('init bdd');
        $donnees=$this->instanceModelProduit->init_bdd();
        header("Location: ".BASE_URL."app.php/Produit/afficherProduits");
    }

} 

