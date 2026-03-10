function creer() {
    $.ajax({
      type: "POST",
      url: "creerScrutin.php",
    }).done(function(msg) {
      $("#libre").html(msg);
    }).fail(function(msg) {
      console.log(msg);
      alert("error");
    });
  }

  function afficheMesScrutins() {
    $.ajax({
      type: "GET",
      url: "affichierMesScrutin.php",
    }).done(function(msg) {
      $("#libre").html(msg);
    }).fail(function(msg) {
      console.log(msg);
      alert("error");
    });
  }


function ajouterOption() {
  const div = document.getElementById('options');
  const inputCount = div.getElementsByTagName('input').length;
  const newInput = document.createElement('input');
  newInput.type = 'text';
  newInput.name = 'options[]';
  newInput.placeholder = `Option ${inputCount + 1}`;
  div.appendChild(newInput);
  div.appendChild(document.createElement('br'));
}

function enregistrer() {
  const form = document.getElementById('form');
  const formData = new FormData(form);
  fetch('enregistrerScrutin.php', {
    method: 'POST',
    body: formData
  }).then(response => response.text())
    .then(data => {
      document.getElementById('res-enregistrer-scrutin').innerHTML = data;
    })
    .catch(error => {
      console.error(error);
      alert('error');
    });
}

function enregistrerScrutin(){
  var formData = $("#formCreationScrutin").serialize(); 
  $.ajax({
    type: "POST",
    url: "/Applications/MAMP/htdocs/Projet_Prog_Web/enregistrer_scrutin.php",
    data: formData
  }).done(function(msg) {
    $("#libre").html(msg);
    alert("scrutin enregistré avec succès");
  }).fail(function(msg) {
    console.log(msg);
    alert("error");
  });
}

function afficherScrutinVoter(){
  $.ajax({
    type: "GET",
    url: "afficherScrutinsVotables.php"
  }).done(function(msg) {
    $("#libre").html(msg);
  }).fail(function(msg) {
    console.log(msg);
    alert("Aucun scrutin n'est en cours");
  });
}

function afficherConsulter(){
  $.ajax({
    type: "GET",
    url: "afficherScrutinsConsulte.php",
  }).done(function(msg) {
    $("#libre").html(msg);
  }).fail(function(msg) {
    console.log(msg);
    alert("erroooorrrr");
  });
}

function deconnexion() {
  $.ajax({
    type: "POST",
    url: "deconnexion.php",
  }).done(function() {
    window.location.href = "index.php";
  }).fail(function() {
    alert("Erreur lors de la déconnexion");
  });
}

