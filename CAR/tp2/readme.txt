README
------

Ce projet fournit un framework simple [1] pour l'execution de programmes
accessibles en tant que ressources REST.

Les ressources se programment comment des classes annotees avec l'API JAX-RS.
Voir un example avec la classe car.HelloWorldResource.

Pour pouvoir etre prises en compte par le framework, les ressources doivent etre
declarees dans la classe com.example.config.AppConfig, methode jaxRsServer().
La declaration se fait en utilisant l'instruction suivante :

	serviceBeans.add(new _classe_de_la_ressource());
	par exemple serviceBeans.add(new HelloWorldResource());
	
Autant de ressources que necessaire peuvent etre declarees.

Le lancement du framework se fait en invoquant la methode Main de la classe
com.example.Starter.

Une fois lancees, les ressources sont accessibles, par exemple via un
navigateur, en chargeant une URL de la forme ;

	http://localhost:8080/rest/api/_ressource_
	par exemple http://localhost:8080/rest/api/helloworld
	

Lionel Seinturier.
13 juin 2014.


[1] http://aredko.blogspot.fr/2013/01/going-rest-embedding-jetty-with-spring.html