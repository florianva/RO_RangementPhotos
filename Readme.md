-------------------
#Projet de création d'album photo automatique par optimisation
Auteur      : S. Verel
Date        : 8/11/2015

Modifications : F. Vansteene
Date 			 : 06/01/2017


-------------------
##Liste des fichiers :

- buildAlbum.py               : code python permettant de créer les pages web avec l'album à partir d'un fichier de solution
- html                        : dossier pour recevoir les pages web avec l'album
- html/img/*.jpg              : les 55 photos au format jpg de l'album photo
- html/styleAlbum.css         : feuille de style associée aux pages web de l'album
- data/info-photo.json        : information sur les 55 photos au format json
- data/info-album.json        : information sur les 9 pages de l'album
- data/chronologic-order.sol  : fichier contenant une solution de disposition des photos de l'album (par ordre chronologique)
- projet : dossier du code java contenant les algorithmes à exécuter
- config.properties : fichier de configuration à modifier en fonction des paramètres à passer au jar

-------------------

##Modification du fichier de configuration
- Le fichier de configuration se présente sous la forme :
- photoFileName= Your info-photo file
- albumFileName= Your info-album file
- solutionFileName= Your chronologic-order file
- algorithme= Algorithme (cf. Liste des Algorithmes)
- critere= Critere(cf. Liste des Criteres de tri)
- nbRun= Nombre d'itertions
- nbEval= Nombre d'evaluations
- empreinte= Choix de l'empreinte pour la distance ( dhashdist, ahashdist ou phashdist )



###Liste des Algorithmes
* 1 = Hill Climber First Improvement
* 2 = Iterated Local Search
     
    
    
###Liste des Criteres de tri
* 1 = Empreinte (ahashdist)
* 2 = Empreinte + Nuances de gris
* 3 = Empreinte + Tags
* 4 = Empreinte + Nuances de gris + Tags



-------------------
##Création des pages

python code/buildAlbum.py
utilise par défaut le fichier data/chronologic-order.sol

------------------

##Exécution
java -jar projet.jar

