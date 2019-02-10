# svc-analizer
Code challenge de Mercado libre

## Como Ejecutar
#### Levantar una base en local corriendo

`docker run -d  -p 5432:5432 -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=dna --name analizer-pg postgres`

#### Ejecutar mvn 