<?php
// afficherScrutinsEmailSession.php

session_start();

// Vérifier si l'utilisateur est connecté
if (!isset($_SESSION['email'])) {
    echo "Vous devez être connecté pour voir vos scrutins.";
    exit;
}

// Lire les scrutins
$json = file_get_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/scrutins.json');
$scrutins = json_decode($json, true) ?: [];

// Lire les scrutins votables
$jsonUtilisateurs = file_get_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/utilisateurs.json');
$utilisateurs = json_decode($jsonUtilisateurs, true) ?: [];
$scrutinsVotables = $utilisateurs[$_SESSION['email']]['scrutins_votes'];
error_log(print_r($scrutinsVotables, true));


// Filtrer les scrutins de l'utilisateur
$mesScrutins1 = array_filter($scrutins, function($scrutin) use ($scrutinsVotables) {
    return in_array($scrutin['id'], $scrutinsVotables);
});

$mesScrutins = array_reverse($mesScrutins1);


// Afficher les scrutins
foreach ($mesScrutins as $scrutin) {
    foreach ($scrutin['votants'] as $votant) {
        if ($votant['nom'] == $_SESSION['email'] && $votant['vote_possible'] > 0 && $scrutin['ouvert'] == true) {
            echo "<div class ='mesScrutins' style ='width: 400px;'>";
            echo "<p style='float: right; font-size: 10px;'>Date: " . htmlspecialchars($scrutin['date']) . "</p>" .
            "<p style='font-size: 10px;'>ID: " . htmlspecialchars($scrutin['id']) . "</p>";
            echo "<p style='font-size: 10px;'>Créateur : " . htmlspecialchars($scrutin['email']) . "</p>" ;
            echo "<h2> Questions : " . htmlspecialchars($scrutin['question']) . "</h2>";
            foreach ($scrutin['options'] as $option) {
                echo "<p>" . htmlspecialchars($option) . "</p>";
                echo "<button onclick=\"vote('" . $scrutin['id'] . "', '" . $option . "')\">Voter pour cette option</button>";
            }
            echo "</div>";
        }
    }
}
?>
<script>
    function vote(id, option) {
    if (confirm('Voulez-vous vraiment voter pour cette option ?')) {
        // Envoi de la requête AJAX
        $.ajax({
            type: "POST",
            url: "voteScrutin.php",
            data: { id: id, option: option }
        }).done(function(response) {
            alert(response);
        }).fail(function() {
            alert('Une erreur est survenue.');
        });
    }
    }

    function laisseProcurer(id) {
        if (confirm('Voulez-vous vraiment laisser quelqu\'un d\'autre voter à votre place ?')) {
            $.ajax({
                type: "POST",
                url: "laisserProcurer.php",
                data: { id: id }
            }).done(function(response) {
                alert(response);
            }).fail(function() {
                alert('Une erreur est survenue.');
            });
        }
    }

</script>