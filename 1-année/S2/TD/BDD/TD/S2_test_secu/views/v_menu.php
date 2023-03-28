

<nav class="top-bar" data-topbar role="navigation">
    <ul class="title-area"> 
      <li class="name"> 
        <h1> <a href="">TP web</a></h1> 
      </li> 
      <li class="toggle-topbar menu-icon">
        <a href="#"><span>Menu</span></a>
      </li> 
    </ul> 
    <section class="top-bar-section"> 
      <ul class="left">
        <li class="divider"></li> 
        <li class="active"><a href="<?php echo BASE_URL?>app.php/Produit/init_BDD">init</a></li> 
        <li class="divider"></li> 
        <li class="has-dropdown"><a href="#">produit</a> 
        <ul class="dropdown"> 
          <li><a href="<?php echo BASE_URL?>app.php/Produit/creerProduit"> créer un produit </a></li> 
          <li><a href="<?php echo BASE_URL?>app.php/Produit/afficherProduits">afficher/editer/supprimer les produits</a></li> 
        </ul> 
        </li> 
      </ul> 
      <ul class="right">
          <li><a href="<?php echo BASE_URL?>app.php/Produit/init_BDD">init tables</a></li>
      </ul> 
    </section> 
</nav>
