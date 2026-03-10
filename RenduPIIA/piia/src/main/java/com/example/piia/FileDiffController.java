package com.example.piia;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import javax.crypto.Mac;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class FileDiffController {

    @FXML
    private TextField messageDeFin;
    @FXML
    private Button buttonSauvegarde;
    @FXML
    private Button buttonNonSauvegarde;
    @FXML
    private Button buttonOuiSauvegarde;
    @FXML
    private Text textVerifSauvegarde;
    @FXML
    private TextField nomSauvergarde;
    @FXML
    private TextFlow textFlowCompareOriginal;
    @FXML
    private Button buttonModifieFichier;
    @FXML
    private TextFlow textFlowCompareModifie;
    @FXML
    private Button buttonSelectionOriginal;
    @FXML
    private Button buttonSelectionModifie;
    @FXML
    private TextField cheminOriginal;
    @FXML
    private TextField cheminModifie;
    @FXML
    private TextFlow textAreaResult;

    @FXML
    private TextArea zoneTexteOrignal;

    @FXML
    private TextArea zoneTexteModifie;

    @FXML
    private Button buttonEvaluer;
    @FXML
    private Button buttonAfficheOriginal;
    @FXML
    private Button buttonAfficheModifie;
    @FXML
    private Button buttonCompare;

    @FXML
    private Button buttonAccepter;
    @FXML
    private Button buttonRefuser;

    @FXML
    private TextFlow textFlowDiff;

    @FXML
    private Button buttonMenu;

    @FXML
    private TextArea textAreaResultatFinal;

    private FileChooser fileChooser = new FileChooser();

    private List<DiffRow> lignesDiff;
    private List<DiffRow> lignesDiff2;

    private int indexDiffActuel = 0;

    private List<String> TexteResultat = new ArrayList<>();




    @FXML
    private void selectFile1() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())), "UTF-8");
                if (content.length() > 1000) {
                    cheminOriginal.setText("Fichier trop grand (plus de 1000 signes)");
                    cheminOriginal.setStyle("-fx-text-fill: red;");
                } else {
                    cheminOriginal.setText(file.getAbsolutePath());
                    cheminOriginal.setStyle("-fx-text-fill: black;");
                    buttonAfficheOriginal.setVisible(true);
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
                cheminOriginal.setText("Erreur de lecture du fichier");
                cheminOriginal.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private TextField getCheminOriginal(){
        return this.cheminOriginal;
    }

    private TextField getCheminModifie(){
        return this.cheminModifie;
    }

    @FXML
    private void afficheTexte1(){
        try {
            String contenu = Files.lines(Paths.get(getCheminOriginal().getText()))
                    .collect(Collectors.joining("\n"));
            zoneTexteOrignal.setText(contenu);
        } catch (Exception e) {
            zoneTexteOrignal.setText("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }


    @FXML
    private void selectFile2() {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())), "UTF-8");
                if (content.length() > 1000) {
                    cheminModifie.setText("Fichier trop grand (plus de 1000 signes)");
                    cheminModifie.setStyle("-fx-text-fill: red;");
                } else {
                    cheminModifie.setText(file.getAbsolutePath());
                    cheminModifie.setStyle("-fx-text-fill: black;");
                    buttonAfficheModifie.setVisible(true);
                    buttonCompare.setVisible(true);
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
                cheminModifie.setText("Erreur de lecture du fichier");
                cheminModifie.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    private void afficheTexte2(){
        try {
            String contenu = Files.lines(Paths.get(getCheminModifie().getText()))
                    .collect(Collectors.joining("\n"));
            zoneTexteModifie.setText(contenu);
        } catch (Exception e) {
            zoneTexteModifie.setText("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
    @FXML
    private void compareFiles() {
        textAreaResult.setVisible(true);
        try {
            DiffRowGenerator generator = DiffRowGenerator.create()
                    .showInlineDiffs(true)
                    .mergeOriginalRevised(true)
                    .inlineDiffByWord(true)
                    .oldTag(f -> "~")
                    .newTag(f -> "**")
                    .build();

            DiffRowGenerator generator2 = DiffRowGenerator.create()
                    .showInlineDiffs(true)
                    .inlineDiffByWord(true)
                    .oldTag(f -> "~~")
                    .newTag(f -> "**")
                    .build();

            List<String> original = Files.readAllLines(Paths.get(getCheminOriginal().getText()));
            List<String> modifie = Files.readAllLines(Paths.get(getCheminModifie().getText()));

            //List<String> original = Files.readAllLines(Paths.get("/Users/macbook/Downloads/original.txt"));
            //List<String> modifie = Files.readAllLines(Paths.get("/Users/macbook/Downloads/modifie.txt"));

            lignesDiff = generator.generateDiffRows(original, modifie);
            lignesDiff2 = generator2.generateDiffRows(original, modifie);

            // Efface le contenu précédent du TextFlow
            textAreaResult.getChildren().clear();

        // Parcourir chaque ligne de différence
            for (DiffRow row : lignesDiff) {
                // La ligne combinée est déjà traitée par mergeOriginalRevised
                String combinedLine = row.getOldLine(); // Si mergeOriginalRevised(true) est utilisé, getOldLine contient déjà le résultat combiné.

                // Découper et traiter les modifications en ligne
                // Regex pour détecter et diviser la ligne en parties normales, supprimées et ajoutées
                Pattern pattern = Pattern.compile("(~.*?~)|\\*\\*.*?\\*\\*");
                Matcher matcher = pattern.matcher(combinedLine);

                int lastEnd = 0;
                // Traiter chaque partie de la ligne
                while (matcher.find()) {
                    // Texte normal avant une modification
                    String normalText = combinedLine.substring(lastEnd, matcher.start());
                    Text text = new Text(normalText);
                    textAreaResult.getChildren().add(text);

                    // Texte modifié (supprimé ou ajouté)
                    String modifiedText = combinedLine.substring(matcher.start() + 1, matcher.end() - 1);
                    Text modified = new Text(modifiedText);

                    if (combinedLine.charAt(matcher.start()) == '~') { // Suppressions
                        modified.setFill(Color.RED);
                        modified.setStrikethrough(true);
                    } else if (combinedLine.charAt(matcher.start()) == '*') { // Ajouts
                        modified.setFill(Color.BLUE);
                    }

                    textAreaResult.getChildren().add(modified);
                    lastEnd = matcher.end();
                }

                // Ajouter le reste de la ligne après la dernière modification
                if (lastEnd < combinedLine.length()) {
                    Text text = new Text(combinedLine.substring(lastEnd));
                    textAreaResult.getChildren().add(text);
                }

                // Ajouter un saut de ligne après chaque ligne traitée
                textAreaResult.getChildren().add(new Text("\n"));
            }
            buttonEvaluer.setVisible(true);

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @FXML
    private void compareTexte1_2(){
        try {
            buttonCompare.setVisible(false);
            buttonAfficheModifie.setVisible(false);
            buttonAfficheOriginal.setVisible(false);
            zoneTexteOrignal.setVisible(false);
            zoneTexteModifie.setVisible(false);
            buttonEvaluer.setVisible(false);
            cheminModifie.setVisible(false);
            cheminOriginal.setVisible(false);
            buttonSelectionOriginal.setVisible(false);
            buttonSelectionModifie.setVisible(false);
            textFlowCompareOriginal.setVisible(true);
            textFlowCompareModifie.setVisible(true);
            buttonMenu.setVisible(true);

            // Efface le contenu précédent du TextFlow
            textFlowCompareOriginal.getChildren().clear();
            textFlowCompareModifie.getChildren().clear();

            // Parcourir chaque ligne de différence
            for (DiffRow row :lignesDiff) {
                String combinedLine = row.getOldLine(); // Si mergeOriginalRevised(true) est utilisé, getOldLine contient déjà le résultat combiné.

                // Découper et traiter les modifications en ligne
                // Regex pour détecter et diviser la ligne en parties normales, supprimées et ajoutées
                Pattern pattern = Pattern.compile("(~.*?~)|\\*\\*.*?\\*\\*");
                Matcher matcher = pattern.matcher(combinedLine);

                int lastEnd = 0;
                // Traiter chaque partie de la ligne
                while (matcher.find()) {
                    // Texte normal avant une modification
                    String normalText = combinedLine.substring(lastEnd, matcher.start());
                    Text text = new Text(normalText);
                    textFlowCompareOriginal.getChildren().add(text);

                    // Texte modifié (supprimé ou ajouté)
                    String modifiedText = combinedLine.substring(matcher.start() + 1, matcher.end() - 1);
                    Text modified = new Text(modifiedText);

                    if (combinedLine.charAt(matcher.start()) == '~') { // Suppressions
                        modified.setFill(Color.RED);
                        modified.setStrikethrough(true);
                    } else if (combinedLine.charAt(matcher.start()) == '*') { // Ajouts
                        modified.setFill(Color.BLUE);
                    }

                    textFlowCompareOriginal.getChildren().add(modified);
                    lastEnd = matcher.end();
                }

                // Ajouter le reste de la ligne après la dernière modification
                if (lastEnd < combinedLine.length()) {
                    Text text = new Text(combinedLine.substring(lastEnd));
                    textFlowCompareOriginal.getChildren().add(text);
                }

                // Ajouter un saut de ligne après chaque ligne traitée
                textFlowCompareOriginal.getChildren().add(new Text("\n"));
            }
            textAreaResult.setVisible(false);
            buttonEvaluer.setVisible(false);
            buttonAccepter.setVisible(true);
            buttonRefuser.setVisible(true);
            afficherDiffActuelle();
            textFlowDiff.setVisible(true);

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void afficherDiffActuelle() {
        if (indexDiffActuel < lignesDiff2.size()) {
            DiffRow ligne = lignesDiff2.get(indexDiffActuel);
            textFlowDiff.getChildren().clear();

            String texteOriginalNettoye = nettoyerTexte(ligne.getOldLine());
            String texteModifieNettoye = nettoyerTexte(ligne.getNewLine());

            Text texteOriginal = new Text("Ancienne ligne: " + texteOriginalNettoye + "\n");
            texteOriginal.setFill(Color.GRAY);
            textFlowDiff.getChildren().add(texteOriginal);


            Text texteModifie = new Text("Nouvelle ligne: " + texteModifieNettoye);
            texteModifie.setFill(Color.BLUE);  // Changer la couleur si nécessaire
            textFlowDiff.getChildren().add(texteModifie);
        } else {
            textFlowDiff.getChildren().clear();
            textFlowDiff.getChildren().add(new Text("Aucune autre différence à afficher."));
            buttonModifieFichier.setVisible(true);
            buttonSauvegarde.setVisible(true);
            nomSauvergarde.setVisible(true);
            buttonAccepter.setVisible(false);
            buttonRefuser.setVisible(false);
            buttonModifieFichier.setVisible(true);
        }
    }

    private String nettoyerTexte(String texte) {
        return texte.replaceAll("\\*\\*|~~", "");
    }

    @FXML
    private void gererAccepter() {
        if (indexDiffActuel < lignesDiff2.size()) {
            DiffRow ligne = lignesDiff2.get(indexDiffActuel);
            String texteModifieNettoye = nettoyerTexte(ligne.getNewLine());
            TexteResultat.add(texteModifieNettoye);
            indexDiffActuel++;
            afficherDiffActuelle();
            actualiseResultat();
        }
    }

    @FXML
    private void gererRefuser() {
        if (indexDiffActuel < lignesDiff2.size()) {
            DiffRow ligne = lignesDiff2.get(indexDiffActuel);
            String texteOriginalNettoye = nettoyerTexte(ligne.getOldLine());
            TexteResultat.add(texteOriginalNettoye);
            indexDiffActuel++;
            afficherDiffActuelle();
            actualiseResultat();
        }
    }

    private void actualiseResultat(){
        textFlowCompareModifie.getChildren().clear();
        String contenuConcatene = String.join("\n", TexteResultat);
        Text texte = new Text(contenuConcatene);
        textFlowCompareModifie.getChildren().add(texte);
    }

    @FXML
    private void modifier() {
        buttonAccepter.setVisible(false);
        buttonRefuser.setVisible(false);
        textFlowDiff.setVisible(false);
        textFlowCompareModifie.setVisible(false);
        textFlowCompareOriginal.setVisible(false);
        buttonModifieFichier.setVisible(false);
        textAreaResultatFinal.setVisible(true);
        buttonSauvegarde.setVisible(true);
        nomSauvergarde.setVisible(true);
        StringBuilder contenu = new StringBuilder();
        for (String ligne : TexteResultat) {
            contenu.append(ligne).append("\n");
        }

        textAreaResultatFinal.setText(contenu.toString());
    }

    @FXML
    private void verifSauvegarder(){
        buttonSauvegarde.setVisible(false);
        buttonOuiSauvegarde.setVisible(true);
        buttonNonSauvegarde.setVisible(true);
        textVerifSauvegarde.setVisible(true);
    }

    @FXML
    private void nonSauvegarde(){
        buttonSauvegarde.setVisible(true);
        buttonOuiSauvegarde.setVisible(false);
        buttonNonSauvegarde.setVisible(false);
        textVerifSauvegarde.setVisible(false);
    }

    @FXML
    private void sauvegarde(){
        String nomFichier = nomSauvergarde.getText().trim();  // Obtient le nom de fichier du TextField
        if (nomFichier.isEmpty()) {
            nomFichier = "default";  // Nom par défaut si aucun nom n'est fourni
        }
        String contenu = textAreaResultatFinal.getText();  // Obtient le texte du TextArea
        File fichier = new File(nomFichier + ".txt");  // Crée un objet File

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(contenu);  // Écrit le contenu dans le fichier
            System.out.println("Fichier sauvegardé sous : " + fichier.getAbsolutePath());
            messageDeFin.setText("Fichier sauvegardé sous : " + fichier.getAbsolutePath());
            messageDeFin.setVisible(true);
            buttonOuiSauvegarde.setVisible(false);
            buttonNonSauvegarde.setVisible(false);
            nomSauvergarde.setVisible(false);
            textAreaResultatFinal.setVisible(false);
            textVerifSauvegarde.setVisible(false);
            textFlowCompareOriginal.setVisible(false);
            textFlowCompareModifie.setVisible(false);
            textFlowDiff.setVisible(false);
            buttonModifieFichier.setVisible(false);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
        }
    }
    @FXML
    private void revenirMenu(){
        zoneTexteOrignal.setVisible(true);
        zoneTexteModifie.setVisible(true);
        cheminModifie.setVisible(true);
        cheminOriginal.setVisible(true);
        buttonSelectionOriginal.setVisible(true);
        buttonSelectionModifie.setVisible(true);
        textFlowCompareOriginal.setVisible(false);
        textFlowCompareModifie.setVisible(false);
        buttonMenu.setVisible(false);
        textAreaResult.setVisible(false);
        buttonEvaluer.setVisible(false);
        buttonAccepter.setVisible(false);
        buttonRefuser.setVisible(false);
        textFlowDiff.setVisible(false);
        textVerifSauvegarde.setVisible(false);
        messageDeFin.setVisible(false);
        buttonModifieFichier.setVisible(false);
    }




}
