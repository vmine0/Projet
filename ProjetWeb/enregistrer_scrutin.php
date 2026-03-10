<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    session_start();
    $email = $_SESSION["email"];
    $question = $_POST['question'];
    $options = $_POST['options'];
    $votantsBrut = explode("\n", $_POST['votants']);
    $votants = [];
    $votes = array_fill_keys($options, 0);

    $votants[] = ['nom' => $email, 'vote_possible' => 1];

    foreach ($votantsBrut as $votant) {
        list($nom, $procurations) = explode(";", $votant);
        $votants[] = ['nom' => $nom, 'vote_possible' => (int)$procurations + 1];
    }

    $idScrutin = uniqid();
    $vote_attendu = array_sum(array_column($votants, 'vote_possible'));

    $nouveauScrutin = [
        'date' => date('Y-m-d H:i:s'),
        'id' => $idScrutin,
        'email' => $email, 
        'question' => $question,
        'options' => $options,
        'votants' => $votants,
        'vote_attendu'=> $vote_attendu,
        'nb_vote' => 0, 
        'votes' => $votes,
        'aVote' => [],
        'ouvert' => true
    ];

    //mettre le scrutin dans le fichier scrutins.json
    $cheminFichier = '/Applications/MAMP/htdocs/Projet_Prog_Web/scrutins.json'; 
    $json = file_get_contents($cheminFichier);
    $scrutins = json_decode($json, true) ?: []; 
    $scrutins[] = $nouveauScrutin;
    file_put_contents($cheminFichier, json_encode($scrutins, JSON_PRETTY_PRINT));

    //mettre le scrutin dans le fichier utilisateurs.json
    $cheminFichierUtilisateurs = '/Applications/MAMP/htdocs/Projet_Prog_Web/utilisateurs.json';
    $jsonUtilisateurs = file_get_contents($cheminFichierUtilisateurs);
    $utilisateurs = json_decode($jsonUtilisateurs, true);
     if (!isset($utilisateurs[$email]['scrutins_crees'])) {
        $utilisateurs[$email]['scrutins_crees'] = [];
    }


    $utilisateurs[$email]['scrutins_crees'][] = $idScrutin;
    
    foreach ($votants as $votant) {
        $nom = $votant['nom'];
        if (!isset($utilisateurs[$nom]['scrutins_votes'])) {
            $utilisateurs[$nom]['scrutins_votes'] = [];
        }
        $utilisateurs[$nom]['scrutins_votes'][] = $idScrutin;
    }
    
    file_put_contents($cheminFichierUtilisateurs, json_encode($utilisateurs, JSON_PRETTY_PRINT));
    
    
    echo "Scrutin enregistré avec succès.";

    header('Refresh: 2; URL=pagePrincipale.php'); 
}
?>

