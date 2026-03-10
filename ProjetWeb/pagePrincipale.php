<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord de l'organisateur</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="script.js"></script>
</head>
<body>
    <header>
        <h1>TimeToVote</h1>
        Bienvenue, <?php session_start(); echo $_SESSION['email']?>
    </header>
    <div class="container">
        <h2>Tableau de Bord</h2>
        <div class="actions">
            <input type = "button" id="mesScrutins" onclick="afficheMesScrutins()" value="Mes Scrutins">
            <input type = "button" id ="creerScrutin" onclick="creer()" value="Créer un scrutin" >
            <input type = "button"  id="consulterScrutin" onclick="afficherConsulter()" value="Consulter un scrutin">
            <input type = "button" id="voterScrutin" onclick="afficherScrutinVoter()" value="Voter">
            <input style = "background-color:red;" type = "button" id="deconnexion" onclick="deconnexion()" value="Déconnexion">
        </div>
        <br>
        <div id="libre"></div>
    </div>

    
</body>
</html>
