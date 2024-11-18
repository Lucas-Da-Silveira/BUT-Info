<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Form</title>
</head>
<body>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['name']) && isset($_POST['gender'])) {
    $name = htmlspecialchars($_POST['name']);
    $gender = htmlspecialchars($_POST['gender']);
    $lastVisit = date("Y-m-d H:i:s");

    setcookie("name", $name, time() + 10, "/");
    setcookie("gender", $gender, time() + 10, "/");
    setcookie("lastVisit", $lastVisit, time() + 10, "/");

    echo "<p>Merci, $name. Vos informations ont été sauvegardées.</p>";
} elseif (isset($_COOKIE['name']) && isset($_COOKIE['gender'])) {
    $name = htmlspecialchars($_COOKIE['name']);
    $gender = htmlspecialchars($_COOKIE['gender']);
    $lastVisit = htmlspecialchars($_COOKIE['lastVisit']);

    echo "<p>Bienvenue de nouveau, $name. Vous êtes $gender. Votre dernière visite était le $lastVisit.</p>";
} else {
    ?>
    <form action="" method="post">
        <label for="name">Nom :</label>
        <input type="text" id="name" name="name" required>
        <br>
        <br>
        <label>Sexe :</label>
        <input type="radio" id="homme" name="gender" value="Homme" required>
        <label for="homme">Homme</label>
        <input type="radio" id="femme" name="gender" value="Femme" required>
        <label for="femme">Femme</label>
        <br>
        <br>
        <button type="submit">Soumettre</button>
    </form>
    <?php
}
?>
</body>
</html>