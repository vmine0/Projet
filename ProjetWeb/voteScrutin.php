<?php
session_start();

$id = $_POST['id'];
$option = $_POST['option'];

$json = file_get_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/scrutins.json');
$scrutins = json_decode($json, true) ?: [];

$emailVotant = $_SESSION['email']; 
$voteAutorise = false; 

foreach ($scrutins as &$scrutin) {
    if ($scrutin['id'] == $id) {
        foreach ($scrutin['votants'] as &$votant) {
            if ($votant['nom'] == $emailVotant && $votant['vote_possible'] > 0) {
                $voteAutorise = true; 
                $votant['vote_possible']--; 
                $scrutin['nb_vote']++;
                break; 
            }
        }

        if ($voteAutorise) {
            if (isset($scrutin['votes'][$option])) {
                $scrutin['votes'][$option]++;
                echo "Votre vote a été enregistré.";

            } else {
                echo "Vote Impossible"; 
            }
            $scrutin['aVote'][] = $emailVotant; // Ajouter l'email à la liste des votants qui ont déjà voté
        } else {
            // Afficher un message d'erreur si le vote n'est pas autorisé
            echo "Vous n'avez plus de vote possible pour ce scrutin.";
            // Vous pouvez choisir de sortir de la fonction/script ici si nécessaire
        }

        break; // Arrêter la recherche une fois le scrutin trouvé et mis à jour
    }
}

// Enregistrer les scrutins dans le fichier scrutins.json
$json = json_encode($scrutins, JSON_PRETTY_PRINT);
file_put_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/scrutins.json', $json);

header('Refresh: 2; URL=pagePrincipale.php');
?>