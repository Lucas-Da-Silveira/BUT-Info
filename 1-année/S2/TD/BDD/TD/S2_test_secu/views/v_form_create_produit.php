<form method="post" action="<?php echo BASE_URL; ?>app.php/Produit/validFormCreerProduit">
    <div class="row">
        <fieldset>
            <legend>Creer un produit</legend>
            <label>Nom
                <input name="nom"  type="text"  size="18" value=""/>
            </label>

            <label>id_type
                <input name="typeProduit_id"  type="text"  size="18" value="1"/>
            </label>

            <label>prix
                <input name="prix"  type="text"  size="18" value="10"/>
            </label>

            <label>photo
                <input name="photo"  type="text"  size="18" value="essai.jpg"/>
            </label>
            <input type="submit" name="CreerProduit" value="Creer" />

        </fieldset>
    </div>
</form>

