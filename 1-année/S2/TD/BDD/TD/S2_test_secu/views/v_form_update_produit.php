<form method="post" action="<?php echo BASE_URL; ?>app.php/Produit/validFormModifierProduit">
<div class="row">
	
	<fieldset>
	<legend>Modifier un produit</legend>
	<input name="id"  type="hidden" value="<?php if(isset($donnees['id'])) echo $donnees['id']; ?>"/>

	<label>Nom 
	<input name="nom"  type="text"  size="18" 
	value="<?php if(isset($donnees['nom'])) echo $donnees['nom']; ?>"/>
    </label>

	<label>Type
	<input name="typeProduit_id"  type="text"  size="18"
	value="<?php if(isset($donnees['typeProduit_id'])) echo $donnees['typeProduit_id']; ?>"/>
	</label>

	<label>Prix
	<input name="prix"  type="text"  size="18" 
	value="<?php if(isset($donnees['prix'])) echo $donnees['prix']; ?>"/>
    </label>

    <label>Photo
	<input name="photo"  type="text"  size="18" 
	value="<?php if(isset($donnees['photo'])) echo $donnees['photo']; ?>"/>
    </label>

	<input type="submit" name="ModifierProduit" value="Modifier" />
		
	</fieldset>	
</div>
</form>