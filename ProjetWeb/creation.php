<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email-inscription']; 
    $motDePasse = $_POST['mot-de-passe-inscription']; 
    $confirmation = $_POST['confirmation-mot-de-passe'];

    if ($motDePasse !== $confirmation) {
        echo '<p>Les mots de passe ne correspondent pas.</p>';
        header('Refresh: 2; URL=index.php');
        exit;
    }

    $fichierJSON = 'utilisateurs.json';
    $json = file_get_contents($fichierJSON);
    $utilisateurs = json_decode($json, true);

    if (isset($utilisateurs[$email])&& isset($utilisateurs[$email]['mdp'])) {
        echo '<p>Cet email est déjà utilisé.</p>';
        header('Refresh: 2; URL=index.php');
        exit;
    }

    // Ajout de l'utilisateur
    $hashed_password = password_hash($motDePasse, PASSWORD_DEFAULT);
    $utilisateurs[$email] = array("email" => $email, "mdp" => $hashed_password);

    file_put_contents($fichierJSON, json_encode($utilisateurs));

    echo '<p>Inscription réussie.</p>';
    // Redirection vers index.php après un certain temps
    header('Refresh: 2; URL=index.php');
}
?>
