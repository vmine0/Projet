<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription et Connexion</title>
    <style>
        body {
            background-color: #121212;
            color: #ffffff;
            font-family: 'Arial', sans-serif;
        }
        
        h1, h2 {
            text-align: center;
            color: #0d6efd; /* Bleu Bootstrap */
        }
        
        form {
            margin: 20px auto;
            width: 90%;
            max-width: 400px;
            background: #222;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
        
        label {
            display: block;
            margin-top: 10px;
        }
        
        input[type=email],
        input[type=password] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #333;
            border-radius: 5px;
            background: #333;
            color: #fff;
        }
        
        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background: #0d6efd; /* Bleu Bootstrap */
            color: #fff;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
        }

        button:hover {
            background: #0b5ed7;
        }

        button:active {
            background: #0a58ca;
        }

        @media (min-width: 768px) {
            form {
                width: 40%;
            }
        }
    </style>
</head>
<body>
    <h1>TimeToVote</h1>
    <h2>Connexion</h2>
    <form id="formulaire-connexion" action="connexion.php" method="post">
        <div>
            <label for="email-connexion">Email :</label>
            <input type="email" id="email-connexion" name="email-connexion" required>
        </div>
        <div>
            <label for="mot-de-passe-connexion">Mot de passe :</label>
            <input type="password" id="mot-de-passe-connexion" name="mot-de-passe-connexion" required>
        </div>
        <button type="submit">Se connecter</button>
    </form>

    <h2>Inscription</h2>
    <form id="formulaire-inscription" action="creation.php" method="post">
        <div>
            <label for="email-inscription">Email :</label>
            <input type="email" id="email-inscription" name="email-inscription" required>
        </div>
        <div>
            <label for="mot-de-passe-inscription">Mot de passe :</label>
            <input type="password" id="mot-de-passe-inscription" name="mot-de-passe-inscription" required>
        </div>
        <div>
            <label for="confirmation-mot-de-passe">Confirmez le mot de passe :</label>
            <input type="password" id="confirmation-mot-de-passe" name="confirmation-mot-de-passe" required>
        </div>
        <button type="submit">S'inscrire</button>
    </form>
</body>
</html>
