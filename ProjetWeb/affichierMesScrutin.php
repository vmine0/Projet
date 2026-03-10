<?php
// afficherScrutinsEmailSession.php

session_start();

// Vérifier si l'utilisateur est connecté
if (!isset($_SESSION['email'])) {
    echo "Vous devez être connecté pour voir vos scrutins.";
    exit;
}

// Lire les scrutins
$json = file_get_contents('scrutins.json');
$scrutins = json_decode($json, true) ?: [];

// Filtrer les scrutins de l'utilisateur
$mesScrutins1 = array_filter($scrutins, function($scrutin) {
    return $scrutin['email'] === $_SESSION['email'];
});

$mesScrutins = array_reverse($mesScrutins1);
// Afficher les scrutins
foreach ($mesScrutins as $scrutin) {
        echo "<style>
    progress[value] {
        -webkit-appearance: none;
        appearance: none;
    width: 100%;
    height: 20px;
    border: 1px solid #0d6efd;
    }
    </style>";
    echo "<div class ='mesScrutins'style ='width: 400px;'>";
    echo "<p style='float: right; font-size: 10px;'>Date: " . htmlspecialchars($scrutin['date']) . "</p>" .
            "<p style='font-size: 10px;'>ID: " . htmlspecialchars($scrutin['id']) . "</p>";
    $tauxParticipation = ($scrutin['nb_vote'] / $scrutin['vote_attendu']) * 100;
    echo "<p>Taux de participation : " . round($tauxParticipation, 2) . "%</p>";
    echo "<progress value='" . $scrutin['nb_vote'] . "' max='" . $scrutin['vote_attendu'] . "'></progress>";
    echo "<h2> Question : " . htmlspecialchars($scrutin['question']) . "</h2>";
    echo "<p>Options : " . implode(', ', $scrutin['options']) . "</p>";
    foreach ($scrutin['votants'] as $votant) {
        echo "<p>" . htmlspecialchars($votant['nom']) . " : " . $votant['vote_possible'] . " votes restants</p>";
    }
    echo "<button onclick=\"cloreScrutin('" . $scrutin['id'] . "')\">Clore le scrutin</button>";
    echo "<button style = 'background-color: red;float: right'onclick=\"supprimerScrutin('" . $scrutin['id'] . "')\">Supprimer le scrutin</button>";
    echo "</div>";
}
?>
<script>
function cloreScrutin(id) {
    if (confirm('Voulez-vous vraiment clore ce scrutin ?')) {
        // Envoi de la requête AJAX
        $.ajax({
            type: "POST",
            url: "fermetureScrutin.php",
            data: { id: id }
        }).done(function(response) {
            alert(response);
        }).fail(function() {
            alert('Une erreur est survenue.');
        });
    }
}

function supprimerScrutin(id) {
    if (confirm('Voulez-vous vraiment supprimer ce scrutin ?')) {
        $.ajax({
            type: "POST",
            url: "supprimerScrutin.php",
            data: { id: id }
        }).done(function(response) {
            alert(response);
        }).fail(function() {
            alert('Une erreur est survenue.');
        });
    }
}

</script>