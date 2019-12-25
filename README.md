# universAAL-ECHONET Lite Service Gateway
A service gateway that interfaces ECHONET Lite devices into universAAL platform resource in the form of semantic resources.

## Prerequisite
Some libraries (ont.echonetontology-3.4.0.jar,echowand-1.1.jar) are not available in the online maven repository so we need to install it locally to get thing done!


	1. The ECHONET Ontology
		1.1 Install the JAR file into local maven directory (E.G
			mvn install:install-file -Dfile=ont.echonetontology-3.4.0.jar -DgroupId=org.universAAL.ontology -DartifactId=echonetontology Dversion=3.4.0 -Dpackaging=jar
		1.2 Import the installed library as maven dependency in the pom.xml (E.G
			<dependency>
				<groupId>org.universAAL.ontology</groupId>
				<artifactId>echonetontology</artifactId>
				<version>3.4.0</version>
			</dependency>
		1.3 Install the library as a bundle in the KARAF deployment environment (E.G
			root@karaf> bundle:install wrap:mvn:org.universAAL.ontology/echonetontology/3.4.0
	2. The ECHONET Lite Middleware
		2.1 Install the JAR file into local maven directory (E.G
			mvn install:install-file -Dfile=echowand-1.1.jar -DgroupId=jaist.tanlab -DartifactId=echowand -Dpackaging=jar -Dversion=1.1
		2.2 Import the installed library as maven dependency in the pom.xml (E.G
			<dependency>
				<groupId>jaist.tanlab</groupId>
				<artifactId>echowand</artifactId>
				<version>1.1</version>
     		</dependency>
     	2.3 Install the library as a bundle in the KARAF deployment environment (E.G
     		root@karaf> bundle:install wrap:mvn:jaist.tanlab/echowand/1.1

## Installation
1. Import the source code as a maven project
2. Set the target network interface which interacts with ECHONET Lite devices in the [Activator.java] file (line 63) 
3. Right-click at the project name -> Run As -> 8 Maven install
4. Generated files are located at the target folder
5. Copy the generated jar file into the deployment folder (karaf/deploy)

Refer to the universAAL github to know how to deploy a library!

## Authors
	1. PHAM, Van Cu (cupham@jaist.ac.jp) (Japan Advanced Institute of Science and Technology (JAIST)) : initial and create the project
	
	2. Yuto LIM (JAIST): advisor 
	
	3. Yasuo TAN (JAIST): advisor

## Credits
The ECHONET Lite middleware library is developed by Yoshiki Makino

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License 
Apache 2.0 

## Acknowledgments
This work is a part of the
<a href="http://caressesrobot.org/en/">title="CARESSES Project" alt="CARESSES Project"></a>

Contact information:

	1. Chong Nak-Young (JAIST, Japan) : Japan  Coodinator 
	
	2. Antonio Sgorbissa (University of Genoa, Italy) : EU Coodinator
