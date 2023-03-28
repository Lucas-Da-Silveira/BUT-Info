<div class="row">
<a href="<?php echo BASE_URL?>app.php/Produit/creerProduit/"> Ajouter un produit </a>
<table>
<caption>Recapitulatifs des produits</caption>
<thead>
<tr><th>nom</th><th>type</th><th>id</th><th>prix</th><th> nom photo</th><th>photo</th>
<th>opération</th>
</tr>
</thead>
<tbody>
<?php if(isset($data[0])): ?>
	<?php foreach ($data as $value): ?>
		<tr><td>
		<?php echo $value['nom']; ?>
		</td><td>
		<?= $value['typeProduit_id']; ?>
		</td><td>
		<?php echo($value['id']); ?>
		</td><td>
		<?= helperViewPrix::view($value['prix']); ?>
		</td><td>
		<?= $value['photo']; ?>
		</td><td>
		<img style="width:40px;height:40px" src="<?php echo BASE_URL?>images/<?= $value['photo']; ?>" alt="image de <?= $value['typeProduit_id']; ?>" >
		</td>
		<td>
			<a href="<?php echo BASE_URL?>app.php/Produit/modifierProduit/<?= $value['id']; ?>">modifier</a>
			<a href="<?php echo BASE_URL?>app.php/Produit/supprimerProduit/<?= $value['id']; ?>">supprimer</a>
		</td>
		</tr>
	<?php endforeach; ?>
<?php endif; ?>
<tbody>
</table>
</div>

