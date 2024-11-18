<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Convertisseur de devises</title>
</head>

<body>
    <h1>Convertisseur de Dollars en Euros</h1>
    <form action="convertor.php" method="post">
        <label for="amount">Montant :</label>
        <input type="number" id="amount" name="amount" step="0.01" required>
        <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['amount']) && $_POST['amount'] < 0) {
            echo '<span class="error"> Le montant ne peut pas être négatif.</span>';
        }
        ?>
        <br>
        <br>
        <label for="conversion">Type de conversion :</label>
        <select id="conversion" name="conversion">
            <option value="usd_to_eur">Dollar - Euro</option>
            <option value="eur_to_usd">Euro - Dollar</option>
        </select>
        <br>
        <br>
        <button type="submit">Convertir</button>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $amount = $_POST['amount'];
        $conversion = $_POST['conversion'];

        if (!empty($amount) && is_numeric($amount) && $amount > 0) {
            if ($conversion == "usd_to_eur") {
                $result = $amount * 0.92;
                echo "<p>$amount USD = $result EUR</p>";
            } elseif ($conversion == "eur_to_usd") {
                $result = $amount / 0.92;
                echo "<p>$amount EUR = $result USD</p>";
            } else {
                echo "<p>Type de conversion non valide.</p>";
            }
        }
    }
    ?>
</body>
</html>