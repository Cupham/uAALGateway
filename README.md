
#1.1 : Install echonetontology to local maven directory

mvn install:install-file -Dfile=ont.echonetontology-3.4.0.jar -DgroupId=org.universAAL.ontology -DartifactId=echonetontology -Dversion=3.4.0 -Dpackaging=jar

#1.2 : Install echonetLibrary to local maven directory

    mvn install:install-file -Dfile=echowand-1.1.jar -DgroupId=jaist.tanlab -DartifactId=echowand -Dpackaging=jar -Dversion=1.1

#2.1 import echonetOntology using dependency
			<dependency>
				<groupId>org.universAAL.ontology</groupId>
				<artifactId>echonetontology</artifactId>
				<version>3.4.0</version>
			</dependency>

#2.2 import echonetLibrary using dependency
     			<dependency>
				<groupId>jaist.tanlab</groupId>
				<artifactId>echowand</artifactId>
				<version>1.1</version>
     			</dependency>
#3.1 install ontology bundle to karaf

root@karaf> bundle:install wrap:mvn:org.universAAL.ontology/echonetontology/3.4.0
#3.2 install echonetLibrary bundle to karaf
root@karaf> bundle:install wrap:mvn:jaist.tanlab/echowand/1.1



