<?php
if (!isset($_POST['id'])) {
    echo "Aucun id de scrutin fourni.";
    exit;
}

$json = file_get_contents('scrutins.json');
$scrutins = json_decode($json, true) ?: [];

foreach ($scrutins as &$scrutin) {
    if ($scrutin['id'] === $_POST['id']) {
        if($scrutin['ouvert'] == false){
            echo "Le scrutin est déjà clos. ";
        }
        $scrutin['ouvert'] = false;
        break;
    }
}

file_put_contents('scrutins.json', json_encode($scrutins));

echo "Scrutin clos avec succès.";
?>