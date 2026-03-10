<?php

session_start();

// Vérifier si l'utilisateur est connecté
if (!isset($_SESSION['email'])) {
    echo "Vous devez être connecté pour voir vos scrutins.";
    exit;
}

// Lire les scrutins
$json = file_get_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/scrutins.json');
$scrutins = json_decode($json, true) ?: [];

// Lire les scrutins 
$jsonUtilisateurs = file_get_contents('/Applications/MAMP/htdocs/Projet_Prog_Web/utilisateurs.json');
$utilisateurs = json_decode($jsonUtilisateurs, true) ?: [];
$scrutinsVotables = $utilisateurs[$_SESSION['email']]['scrutins_votes'];


// Filtrer les scrutins de l'utilisateur
$mesScrutins1 = array_filter($scrutins, function($scrutin) use ($scrutinsVotables) {
    return in_array($scrutin['id'], $scrutinsVotables);
});

$mesScrutins = array_reverse($mesScrutins1);

// Afficher les scrutins
foreach ($mesScrutins as $scrutin) {
    foreach ($scrutin['votants'] as $votant) {
        if ($votant['nom'] == $_SESSION['email'] && ($votant['vote_possible'] ==  0 || $scrutin['ouvert'] == false)) {
            echo "<div class ='mesScrutins' style ='width: 400px;'>";
            echo "<p style='float: right; font-size: 10px;'>Date: " . htmlspecialchars($scrutin['date']) . "</p>" .
            "<p style='font-size: 10px;'>ID: " . htmlspecialchars($scrutin['id']) . "</p>";
            echo "<p style='font-size: 11px;'>Créateur : " . htmlspecialchars($scrutin['email']) . "</p>" ;
            echo "<h2> Questions : " . htmlspecialchars($scrutin['question']) . "</h2>";
            if ($scrutin['ouvert'] == false || $scrutin['vote_attendu'] == $scrutin['nb_vote']) {
                echo "<p style='font-size: 12px;color: blue;'>Résultat</p>";
                $tauxParticipation = ($scrutin['nb_vote'] / $scrutin['vote_attendu']) * 100;
                echo "<p>Taux de participation : " . round($tauxParticipation, 2) . "%</p>";
                echo "<progress value='" . $scrutin['nb_vote'] . "' max='" . $scrutin['vote_attendu'] . "'></progress>";
                $votes = $scrutin['votes'];
                $gagnant = array_search(max($votes), $votes);
                foreach ($scrutin['options'] as $option) {
                    if ($option == $gagnant) {
                        echo "<p style='color: green;'>" . htmlspecialchars($option) . "</p>";
                        echo "<p style='font-size: 12px;'>Votes reçus: " . $votes[$option] . "</p>";
                        echo "<p style='font-size: 12px;'>Nombre total de votes: " . $scrutin['nb_vote'] . "</p>";
                        echo "<p style='font-size: 12px;'>Nombre de vote attendu: " . $scrutin['vote_attendu'] . "</p>";
                    }else{
                        echo "<p style='color: red;'>" . htmlspecialchars($option) . "</p>";
                        echo "<p style='font-size: 12px;'>Votes reçus: " . $votes[$option] . "</p>";
                        echo "<p style='font-size: 12px;'>Nombre total de votes: " . $scrutin['nb_vote'] . "</p>";
                        echo "<p style='font-size: 12px;'>Nombre de vote attendu: " . $scrutin['vote_attendu'] . "</p>";


                    }
                }
            }else {
                echo "<p style='font-size: 12px;'>Vous avez voté , en attente des resultats ...</p>";
                $tauxParticipation = ($scrutin['nb_vote'] / $scrutin['vote_attendu']) * 100;
                echo "<p>Taux de participation : " . round($tauxParticipation, 2) . "%</p>";
                echo "<progress value='" . $scrutin['nb_vote'] . "' max='" . $scrutin['vote_attendu'] . "'></progress>";
                foreach ($scrutin['options'] as $option) {
                    echo "<p>" . htmlspecialchars($option) . "</p>";
                }
            }
            
            echo "</div>";
        }
    }
}
?>
<script>
    function consulter(id) {
        $.ajax({
            type: "GET",
            url: "consulterScrutin.php",
            data: { id: id}
        }).done(function(msg) {
            $("#libre").html(msg);
        }).fail(function(msg) {
            console.log(msg);
            alert("Aucun scrutin n'est consultable pour le moment.");
        });
}

</script>