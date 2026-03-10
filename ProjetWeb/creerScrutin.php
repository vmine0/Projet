<?php
session_start();

if(isset($_SESSION["email"])) {
    $email = $_SESSION["email"];
} else {
    header("Location: connexion.php");
    exit();
}

$msg = "
<div class = 'res' id='creer-scrutin'>
    <h2>Votons</h2>
    <form action = 'enregistrer_scrutin.php' method='post' id='formCreationScrutin'>
    <h2>Créer un Scrutin</h2>
    <label for='email'>Email :</label>
     <input type='email' id='email' value = '$email' readonly>
    <label for='question'>Question :</label>
    <input type='text' id='question' name='question' required><br>

    <div id='options'>
        <h3>Options de réponse</h3>
        <input type='text' name='options[]' required placeholder='Option 1'><br>
        <input type='text' name='options[]' required placeholder='Option 2'><br>
    </div>
    <button type='button' onclick='ajouterOption()'>Ajouter une option</button><br>

    <label for='votants'>Liste de Votants :</label>
    <textarea id='votants' name='votants' required placeholder='Nom;Procurations\nNom;Procurations'></textarea><br>

    <input type='submit' value='Créer le scrutin'>
</form>
</div>
";

echo $msg;
?>

