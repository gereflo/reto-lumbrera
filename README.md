# Project Title

Pequeña prueba para Lumbrera


### Problemas

Se enumeran los problemas al relizar el proyecto.
1.- En el "Getting Started" indica que para crear un nuevo proyecto se debe de iniciar de la siguiente manera:

```
mvn io.quarkus:quarkus-maven-plugin:1.0.1.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=getting-started \
    -DclassName="org.acme.quickstart.GreetingResource" \
    -Dpath="/hello"
cd getting-started

```
Sin embargo lanza error en el OS Windows, para este sistema se cambian las "/" por "^"
```
mvn io.quarkus:quarkus-maven-plugin:1.0.1.Final:create ^
    -DprojectGroupId=org.acme ^
    -DprojectArtifactId=getting-started ^
    -DclassName="org.acme.quickstart.GreetingResource" ^
    -Dpath="/hello"
cd getting-started
```

2.- En el Json dado como ejemplo para "productos" no se incluye ningun id o nombre de compañia, por lo que no es posible validar 
como se pide en el punto 3 "3. Validar que el id de la compañía se encuentre dado de alta.". 
Se decide agregar al Json un id de compañia para que se pueda validar, de no tenerlo se indica.

3.- En el documento no se indica como deben recibirse los datos por lo que para compañia es posible hacerlos via @GET

```
http://localhost:8080/Reto/addCompanie/"Nueva_companhia"
```
Mientras que para productos se hace via @POST, esto da problemas ya que en --data hay que escapar los textos que se envien con """".
Esto parece slo afectar al OS Windows.
```
curl --header "Content-Type: application/json" ^
  --request POST ^
  --data {"""id""":0,"""name""":"""ttttttttt"""  ...} ^
  http://localhost:8080/Reto/addProduct 
```

Otra solucion mas comoda es mandar el post via archivo, de esta manera no es necesario escapar los textos.
```
curl --header "Content-Type: application/json" ^
  --request POST ^
  --data @producto.json ^
  http://localhost:8080/Reto/addProduct 
  
```
4.-Para el poder hacer update e deben de mandar los datos completos no solo el nombre, esto para poder hacer las validaciones pedidas.
 - Validar que el stock sea mayor a 0.
 - Validar que el costo del producto sea menor al precio.
 - Validar que el id de la compañía se encuentre dado de alta.
 - Puede o no tener variaciones el producto, en el caso de tener
	variaciones, válida que el sku de la variación no exista en otro
	producto y que el stock sea mayor a 0.


5.-Para la conexcion a la base de datos el driver SQL debe de ser:
```
com.mysql.cj.jdbc.Driver
```

Ademas se debe etablecer como solo "create" ya que causa problemas con "drop and create", pero hay que borrar la base de datos manualmente
```
quarkus.hibernate-orm.database.generation=create
```

6.- Se debe establecer una zona horaria de la siguiente manera "?serverTimezone=UTC" ya que de otra manera causa error:
```
quarkus.datasource.url = jdbc:mysql://localhost:3308/prueba?serverTimezone=UTC
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo


```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

