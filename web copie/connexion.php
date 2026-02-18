<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST['email-connexion']; 
    $motDePasse = $_POST['mot-de-passe-connexion']; 

    $fichierJSON = 'utilisateurs.json';
    $json = file_get_contents($fichierJSON);
    $utilisateurs = json_decode($json, true);

    if (isset($utilisateurs[$email]) && password_verify($motDePasse, $utilisateurs[$email]['mdp'])) {
        $_SESSION['utilisateurs'] = $utilisateurs;
        $_SESSION['email'] = $email; // Stocker l'email dans la session
        header('Location: pagePrincipale.php'); // Redirection vers la page principale
        exit();
    }
    
    echo '<p>Identifiant ou mot de passe incorrect.</p>';
    // Redirection vers index.php après un certain temps
    header('Refresh: 2; URL=index.php');
}
?>
