# svc-analyzer
Api que busca secuencias de letras en base a un array de ADN

## Como Ejecutar
#### Levantar una base en local corriendo

`docker run -d  -p 5432:5432 -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=dna --name analizer-pg postgres`

#### Ejecutar el servicio
`mvn spring-boot:run`