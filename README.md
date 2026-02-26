# mediatekformationmobile
Cette application mobile permet d'accéder aux vidéos des formations proposées par la médiathèque.
## Fonctionnalités
L'application permet de consulter la liste des titres des formations mises à disposition (récupérées dans la base de données distante, via une API REST).<br>
En sélectionnant un titre, une page présente plus de détails sur la formation et le clic sur l'image permet d'accéder à la page de diffusion de la vidéo à partir de son adresse en ligne.

### Page 1 : l'accueil
La page d'accueil présente le menu :<br>
. "les formations" : avec en dessous, une image cliquable qui permet d'accéder à la page des formations.<br>
. "mes favoris" : avec en dessous, une image cliquable qui permet d'accéder à la page des formations mais affichant seulement les favoris.<br>
![img1](https://github.com/user-attachments/assets/8d25dbea-89a6-44a8-a969-fc508b933f96)
<br>
Le code se trouve dans MainActivity lié au layout activity_main.<br>	 

### Page 2 : les formations / les favoris
La page des formations affiche la liste des formations avec, sur chaque ligne :<br>
- un cœur actuellement qui permet de sélectionner les favoris ;<br>
- la date de la formation et, en dessous, son titre en plus gros.<br>
La liste est triée sur la date de parution, dans l'ordre inverse (la plus récente en premier).<br>
Au-dessus de la liste, une zone de saisie et un bouton "filtrer" doit permettre de filtrer les lignes sur le titre (en tapant une partie du titre, la casse n'est pas prise en compte). Si le bouton "filtrer" est utilisé alors que la zone de saisie est vide, toutes les formations sont être à nouveau affichées.<br>
Le clic sur une date ou un titre de formation permet d'accéder à la page suivante de la présentation du détail d'une formation.<br>
Le clic sur la flèche à gauche du titre permet de revenir au menu (cette possibilité est définie dans le manifest).<br>
Cette page se décline en deux versions, qui affichent toutes les formations ou juste les favoris. Sinon les fonctionnalités sont identiques.<br>
![img2](https://github.com/user-attachments/assets/1be5bf13-b9ba-43de-9bd6-27a1aa727940)
<br>
Le code se trouve dans FormationsActivity lié au layout activity_formations. FormationActivity fait appel à FormationListAdapter pour gérer la liste interactive. Chaque ligne de la liste interactive est construite avec le layout layout_liste_formations.

### Page 3 : une formation
La page de présentation d'une formation contient, de haut en bas :<br>
- l'image de la formation (correspondant à l'attribut "picture") ;<br>
- le titre de la formation (en gros) ;<br>
- la date de parution ;<br>
- le sous-titre "description" avec, en dessous, la description détaillée dans une zone déroulante (dans le cas où elle dépasse la taille de la zone d'affichage).<br>
Le clic sur l'image permet d'accéder à la page suivante contenant la vidéo. Si la formation n'a pas d'image, une image standard s'affiche, cependant, la fonctionnalité d'accès à la page de la vidéo est opérationnelle.<br>
Le clic sur la flèche à gauche du titre permet de revenir à la page de la liste des formations.<br>
<img width="211" height="448" alt="image" src="https://github.com/user-attachments/assets/3ce08a64-e07e-4a55-89b2-d6bf6559db32" />
<br>
Le code se trouve dans UneFormationActivity lié au layout activity_une_formation.

### Page 4 : la vidéo
La page de la vidéo affiche la vidéo en pleine page et permet de la lancer. Cette page peut être gérée verticalement ou horizontalement (suivant le format de la vidéo, la version horizontale est souvent plus confortable).<br>
Pour que la vidéo soit la plus grande possible, la barre du haut a été supprimée. Pour revenir à la page précédente, il suffit d'utiliser la flèche gauche de navigation en bas du smartphone.<br>
<img width="433" height="226" alt="image" src="https://github.com/user-attachments/assets/7b548bed-564c-4596-bec6-e291fe002eca" />
<br>
Le code se trouve dans VideoActivity lié au layout activity_video.

## Base de données
La base de données 'mediatekformation' est au format MySQL. Elle est aussi utilisée pour l'application web.<br>
Elle contient plusieurs tables mais une seule est utile pour l'application mobile : la table 'formation' dont voici la structure :<br>
<pre><code>CREATE TABLE IF NOT EXISTS formation (
  id int NOT NULL AUTO_INCREMENT,
  playlist_id int DEFAULT NULL,
  published_at datetime DEFAULT NULL,
  title varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  description longtext COLLATE utf8mb4_unicode_ci,
  video_id varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id),
  KEY IDX_404021BF6BBD148 (playlist_id)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;</code></pre><br>
Explication des champs :<br>
. id : identifiant de la formation<br>
. playlist_id : identifiant de la playlist à laquelle la vidéo est rattachée(bt>
. published_at : date de parution<br>
. title : titre<br>
. description : description<br>
. video_id : id de la vidéo sur YouTube. Cet id correspond à la fin du chemin obtenu en cliquant sur le bouton "partager" d'une vidéo sur YouTube. Par exemple, l'id " Z4yTTXka958" a été récupéré dans le lien de partage "https://youtu.be/Z4yTTXka958"<br>
. playlist_id : id de la playlist qui contient cette formation (non utilisé ici).
<br><br>
L'application utilise aussi une BDD locale pour sauvegarder les formations favorites. Il y simplement une table avec une colonne qui liste les ids des formations.<br>

## Contenu de l'API REST
L'API REST qui est accessible ici :<br>
<a href="https://github.com/josephchaouistannard/BTS_atelier_mediatekdocuments_api">https://github.com/josephchaouistannard/BTS_atelier_mediatekdocuments_api</a><br>
Pour comprendre son fonctionnement, il est conseillé de lire le Readme de ce dépôt.<br>
## Installation des applications
### API REST
Pour tester l'application mediatekformationmobile local, il faut d'abord installer l'API. Voici le mode opératoire :<br>
- Installer les outils nécessaires (WampServer ou équivalent, Netbeans ou équivalent pour intervenir dans le code, Postman pour les tests).<br>
- Récupérer le zip du code de l'API (en racine du dépôt) et le dézipper dans le dossier www de wampserver (renommer le dossier en "rest_mediatekformationmobile").<br>
- Récupérer le script mediatekformation.sql dans le zip précédent, avec phpMyAdmin, créer la BDD mediatekformation et, dans cette BDD, exécuter le script pour remplir la BDD.<br>
- Ouvrir l'API dans Netbeans (ou autre IDE) pour pouvoir analyser le code et le faire évoluer suivant les besoins.

### Application Android
L'application a été faite avec la version "Android Studio Narwhal 2025.1.2".<br>
Une fois la BDD et l'API REST installées, si le but est de tester en déployant l'application (donc BDD et API REST sur un serveur distant et construction de l'APK) :<br>
Dans FormationApi.java du package api, se trouve la déclaration de la constante API_URL qui contient l’adresse IP de l’api rest pour un test local avec émulateur. Il faut remplacer l’adresse actuelle par celle de l'api en ligne.
