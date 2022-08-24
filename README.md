**AspectJ in Maven project written with Kotlin**
Very simple example on how to enable AspectJ in Maven-powered project.

1. Add plugin to pom.xml
```
   <plugin>
       <groupId>org.codehaus.mojo</groupId>
       <artifactId>aspectj-maven-plugin</artifactId>
       <version>1.14.0</version>
       <configuration>
           <complianceLevel>1.8</complianceLevel>
           <source>1.8</source>
           <target>1.8</target>
           <showWeaveInfo>true</showWeaveInfo>
           <verbose>true</verbose>
           <Xlint>ignore</Xlint>
           <encoding>${project.build.sourceEncoding}</encoding>
           <forceAjcCompile>true</forceAjcCompile>
           <weaveDirectories>
               <weaveDirectory>${project.build.outputDirectory}</weaveDirectory>
               <weaveDirectory>${project.build.testOutputDirectory}</weaveDirectory>
           </weaveDirectories>
       </configuration>
       <executions>
           <execution>
               <goals>
                   <goal>compile</goal>
                   <goal>test-compile</goal>
               </goals>
           </execution>
       </executions>
   </plugin>
```
2. Add libraries to pom.xml dependencies
```
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjrt -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
        <version>1.9.9.1</version>
        <scope>runtime</scope>
    </dependency>

     <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
     <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.9.1</version>
        <scope>runtime</scope>
     </dependency>
```
3. Create a new class with _@Aspect_ annotation
4. Create a method with @Pointcut annotation to define where to add "action"
Use regular expression. Some examples here: https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/
5. Create a method with @AfterReturning (or other available) to define what "action" will be done 
