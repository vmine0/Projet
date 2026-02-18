<?php

session_start();

// Vérifier si l'utilisateur est connecté
if (!isset($_SESSION['email'])) {
    echo "Vous devez être connecté pour supprimer un scrutin.";
    exit;
}

// Vérifier si l'ID du scrutin a été fourni
if (!isset($_POST['id'])) {
    echo "Aucun ID de scrutin fourni.";
    exit;
}

$id = $_POST['id'];

// Lire les scrutins
$json = file_get_contents('scrutins.json');
$scrutins = json_decode($json, true) ?: [];

// Supprimer le scrutin
$scrutins = array_filter($scrutins, function($scrutin) use ($id) {
    return $scrutin['id'] !== $id;
});

// Réécrire les scrutins
file_put_contents('scrutins.json', json_encode($scrutins));

// Lire les utilisateurs
$json = file_get_contents('utilisateurs.json');
$utilisateurs = json_decode($json, true) ?: [];

// Supprimer le scrutin de tous les utilisateurs
foreach ($utilisateurs as &$utilisateur) {
    $utilisateur['scrutins_crees'] = array_filter($utilisateur['scrutins_crees'], function($scrutinId) use ($id) {
        return $scrutinId !== $id;
    });

    $utilisateur['scrutins_votes'] = array_filter($utilisateur['scrutins_votes'], function($scrutinId) use ($id) {
        return $scrutinId !== $id;
    });
}

// Réécrire les utilisateurs
file_put_contents('utilisateurs.json', json_encode($utilisateurs));

echo "Scrutin supprimé avec succès.";
?>