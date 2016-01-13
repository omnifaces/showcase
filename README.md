# OmniFaces showcase

### Showcase Online
OmniFaces Showcase stable version is available at [**showcase.omnifaces.org**](http://showcase.omnifaces.org).

OmniFaces Showcase snapshop version is available at [**snapshot.omnifaces.org**](http://snapshot.omnifaces.org).

### Showcase Local
OmniFaces Showcase can be run locally using maven. Showcase project has multiple profiles defined for various application servers. Any given profile can be activated using `mvn -P profilename` command, ex: `mvn -P tomee`.

```
  dev  (default)
  tomee
  tomee-jaxrs
  wildfly
  glassfish
```
#### WAR deployment

Clone showcase project and run maven to create `WAR` file. Created WAR file can be deployed on any JEE6 complaint servers. Follow the standard WAR file deployment procedures.
```
git clone https://github.com/omnifaces/showcase.git
cd showcase
mvn
```

#### Running with TomEE

Showcase has two versions of TomEE profile defile defined.

##### TomEE with Mojarra

Use `tomee` profile to run showcase with TomEE with Mojarra JSF 2.2.x. This profile uses TomEE Plume version, in addition JSF implementation is updated to Mojarra 2.2.10.

```
mvn -P tomee
```
Maven starts TomEE server and deploy application locally. Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).

##### TomEE with MyFaces

Use `tomee-jaxrs` profile to run showcase with TomEE with MyFaces JSF 2.2.x. This profile uses Tomee JAXRS version, in addition MyFaces version updated to 2.2.8.

```
mvn -P tomee-jaxrs
```
Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).

#### Running with WildFly

##### WildFly 8.2

Use `wildfly` profile to run showcase with JBoss WildFly server. By default it uses embdded `wildfly 8.2.0.Final`. 

```
mvn -P wildfly
```

##### WildFly 9.x

WildFly server version can be changed using commandline option, for example, command below will run WildFly 9

```
mvn -P wildfly -Dwildfly.version=9.0.0.CR1
```
Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).

##### Local WildFly Server

If you would prefer to use local installation of WildFly Server then, specify that using commandline (replace your wildfly path).

```
mvn -P wildfly -Djboss-as.home=/home/srbala/apps/servers/wildfly8
```

#### Running With GlashFish

##### Embedded GlashFish Server

Use `glassfish` profile option to run Embedded GlashFish Server

```
mvn -P glassfish
```
Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).

