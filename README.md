# selenium-lib-test

<b>examples for usage of https://github.com/cpo1964/selenium-lib</b>

First include

		<dependency>
			<groupId>com.github.cpo1964</groupId>
			<artifactId>selenium-lib</artifactId>
			<version>1.0.x</version>
		</dependency>

to the pom.xml (hint: use latest available version)

Use Java 11 or newer!

<b>Run all existing junit4 tests:</b>

- open console window (windows command or linux shell)
- navigate to root directory of this project
- call: mvn test
- or call: mvn test -Dbrowser=firefox -Dmandant=ooe -DtestEnvironment=prod

If needed add additional proxy settings with your credentials:<br>
-Dproxy=yourProxyHost -DproxyUser=yourProxyUser -DproxyPass=yourProxyPassword

Use this maven property for hidden browser:<br>
-Dhidden=true

There is no need to download any selenium browser driver - this is done like magic by the maven artifact io.github.bonigarcia.webdrivermanager<br>
The selenium drivers will be loaded at runtime automatically from:<br>
src/test/resources<br>

Starting a browser driver you maybe will see warnings in the console like 'Unable to find an exact match for CDP version XXX'.<br>
To avoid this warning the usage of the maven artifact 'org.seleniumhq.selenium.selenium-devtools-vXXX' will help.<br>
XXX stands for the used browser version.

If you wish to define a different directory for the download of selenium browser drivers,<br>
then you can set the system property 'wdm.cachePath' as maven parameter, eg:<br>
-Dwdm.cachePath=src/test/MyDriverDir

<b>Following sites will be tested by above mvn calls</b><br>

* web application mtours<br>
    * https://demo.guru99.com/test/newtours/index.php<br>
    * http://localhost:8881/servlets/com.mercurytours.servlet.WelcomeServlet<br>
    see: https://admhelp.microfocus.com/alm/Mercury_Tours/Install_Mtous_App_Windows.htm<br><br>
  
* web application jpetstore<br>
    * https://jpetstore.aspectran.com/catalog/<br>
    * http://localhost:8080/jpetstore/actions/Catalog.action<br>
    see: https://github.com/mybatis/jpetstore-6<br>





