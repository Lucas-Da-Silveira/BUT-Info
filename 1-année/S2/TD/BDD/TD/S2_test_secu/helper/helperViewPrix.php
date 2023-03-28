<?php 
class HelperViewPrix
{
    const TAUX_TVA=0.2;
    public static $taux_tva=0.2;

    public static function view($prix="")
    { 
        if(filter_var($prix, FILTER_VALIDATE_FLOAT) !== false){
            return number_format((float)$prix,2)." &euro;";
        }
        else
            return false;
    }
    public static function viewTVA($prix="")
    { 
        if(filter_var($prix, FILTER_VALIDATE_FLOAT) !== false){
            $tva=$prix*self::TAUX_TVA;  // il n'est pas possible d'utiliser this dans une méthode static
            return self::view($tva);    // donc TAUX_TVA est considéré comme un attribut static
        }
        else
            return false;
    }
    public static function viewHT($prix="")
    { 
        if(filter_var($prix, FILTER_VALIDATE_FLOAT) !== false){
            $prixHT=$prix*(1-SELF::$taux_tva);
            return self::view($prixHT);
        }
        else
            return false;
    }
}