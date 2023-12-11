## :computer: Запуск основного приложения
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## :cd: Запуск Flyway
```bash
mvn clean compile flyway:migrate
```

## :loop: Run the Tomcat Server:
```bash
mvn tomcat7:run
```

## :speaker: Запуск основного приложения
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

##  :bust_in_silhouette: запуск Swagger/OpenAPI
documentation: [swagger-codegen](https://github.com/swagger-api/swagger-codegen)

## :floppy_disk: step:
```bash
swagger-codegen generate -i openapi.yaml -l java -o output-directory
```
