<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Scrutins Filtrés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }
        div {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        h2 {
            color: #007bff;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 5px;
            margin-bottom: 5px;
            border-bottom: 1px solid #eee;
        }
    </style>
</head>
<body>
    <?php
    $json = file_get_contents('scrutins.json');
    $scrutins = json_decode($json, true);

    $email = "amine@hotmail.fr";
    $scrutins = array_filter($scrutins, function($scrutin) use ($email) {
        return $scrutin['email'] === $email;
    });

    foreach ($scrutins as $scrutin) {
        echo "<div>";
        echo "<h2>" . htmlspecialchars($scrutin['question']) . "</h2>";
        echo "<p>Créé par : " . htmlspecialchars($scrutin['email']) . "</p>";
        echo "<p>Votants :</p>";
        echo "<ul>";
        foreach ($scrutin['votants'] as $votant) {
            echo "<li>" . htmlspecialchars($votant['nom']) . " (" . htmlspecialchars($votant['procurations']) . " procurations)</li>";
        }
        echo "</ul>";
        echo "</div>";
    }
    ?>
</body>
</html>
