<div class="row">
<a href="<?php echo BASE_URL?>app.php/Produit/creerProduit/" target="_blanck"> Ajouter un produit </a>
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
</table>


<h3> utilisation obligatoire de FORMULAIRE pour AJOUTER/MODIFIER/SUPPRIMER des données</h3>
<code>curl http://www-etu.iut-bm.univ-fcomte.fr/~amillet/S2_test_secu/app.php/Produit/supprimerProduit/6</code></li>
<p>=&gt; utilisation d’un <strong>token (JETON) </strong> dans les formulaires</p> 

<br>
<h2>Tester les injections ci-dessous : <br></h2>
<ul>
<li>reflected XSS, créer 2 produits avec les noms suivants : <br>
<code>essai',2,'1','photo');  Delete from produits where id &gt; 3;</code></li>
<li>persistent XSS <br>
<code>&lt;script&gt;alert(&quot;hacked&quot;)&lt;/script&gt;</code></li>
</ul>
<pre>
<code>
curl --data &quot;typeProduit_id=2&amp;prix=3&amp;photo=photo&quot; --data-urlencode &quot;nom=essai&#39;,2,&#39;3.1&#39;,&#39;photo&#39;);  Delete from produits where id &gt; 3;&quot; http://www-etu.iut-bm.univ-fcomte.fr/~amillet/S2_test_secu/app.php/Produit/validFormCreerProduit

curl --data &quot;typeProduit_id=2&amp;prix=3.1&amp;photo=essai2.jpg&quot; --data &quot;nom=&lt;script&gt;alert(\&quot;hacked\&quot;)&lt;/script&gt;&quot; http://www-etu.iut-bm.univ-fcomte.fr/~amillet/S2_test_secu/app.php/Produit/validFormCreerProduit
</code>
</pre>

<!--
curl --data &quot;typeProduit_id=2&amp;prix=3.1&amp;photo=essai1.jpg&quot; --data-urlencode &quot;nom=essai&#39;,2,&#39;3.1&#39;,&#39;photo&#39;);  Delete from produits where id &gt; 3;  --&quot; http://URL_de_base_sur_le_navigateur/app.php/Produit/validFormCreerProduit

curl --data &quot;typeProduit_id=2&amp;prix=3.1&amp;photo=essai2.jpg&quot; --data &quot;nom=&lt;script&gt;alert(\&quot;hacked\&quot;)&lt;/script&gt;&quot; http://URL_de_base_sur_le_navigateur/app.php/Produit/validFormCreerProduit
-->

<h1 id="csrf">CSRF</h1>
<p>
Définition : CSRF ou plus communément appelée “Cross Site Request Forgery” 
<a href="http://fr.wikipedia.org/wiki/Cross-Site_Request_Forgery" class="uri" target="_blanck">http://fr.wikipedia.org/wiki/Cross-Site_Request_Forgery</a> <br>
<a href="http://openclassrooms.com/courses/securisation-des-failles-csrf" class="uri" target="_blanck">http://openclassrooms.com/courses/securisation-des-failles-csrf</a>
</p>
<p>principe : mettre dans un message, commentaire … <code>&lt;img src=&quot;votresite/ressource/supprimer/id&quot; /&gt;</code></p>
<ul>
<li>tester une faille csrf avec :

<br> 
<!--
<code>curl http://URL_de_base_sur_le_navigateur/app.php/Produit/supprimerProduit/39</code>
-->

</ul>
<p>Exercice Tester une faille CSRF :</p>
<ul>

<li>nom du produit : <code>&lt;img src=&quot;http://www-etu.iut-bm.univ-fcomte.fr/~amillet/S2_test_secu/app.php/Produit/supprimerProduit/3&quot;&gt;</code></li>
</ul>
<p>=&gt; utilisation d’un <strong>token (JETON) </strong> dans les formulaires</p> 


<br><br>
<h2> Préférer les ORM, les objets comme "queryBuilder" ou faire attention à n'utiliser que des requêtes préparées</h2>
<br>
<a href="https://itexpert.fr/blog/querybuilder/" class="uri" target="_blanck">attention à l'écriture du code</a> <br>

<a href="https://tm.samf.me/html/preparation_sql.html" class="uri" target="_blanck">requête préparée en javascript</a> <br>
<br><br><br><br>
<tbody>

</div>

