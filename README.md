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
Use `tomee` profile to run showcase with TomEE with Mojarra JSF 2.2.x.

```
mvn -P tomee
```
Maven starts TomEE server and deploy application locally. Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).
##### TomEE with MyFaces
Use `tomee-jaxrs` profile to run showcase with TomEE with MyFaces JSF 2.2.x.

```
mvn -P tomee-jaxrs
```
Showcase is available at [http://localhost:8080/showcase](http://localhost:8080/showcase).
