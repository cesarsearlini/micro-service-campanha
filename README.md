## Micro Services - Campanha
  
  * Framework's utilizado. 
	> Spring Cloud</br>
    > Spring Boot</br>
	> Spring RESTful</br>
	> Spring Data</br>
	> Netflix OSS</br>
	
  * Banco de Dados 
   > PostgreSQL</br>

###  Service REST - Time

Metodo	| Path	     | Descrição	
------- | -----      | ---------
GET     | /time      | Retorna todos os times.
GET  	| /time/{id} | Retorna o time pelo id.
POST    | /time	     | Cadastrar um time novo.
PUT	    | /time	     | Altera um time.
DELETE  | /time/{id} | Deleta um tima.

###  Service REST Campanha

Metodo	| Path	             | Descrição	
------- | ------------------ | ---------
GET     | /campanha          | Retorna todas as campanhas.
GET  	| /campanha/{id}     | Retorna uma campanha pelo id.
POST    | /campanha  	     | Cadastra uma nova campanha.
PUT     | /campanha  	     | Altera uma campanha.
DELETE  | /campanha/{id}     | Deleta uma campanha.

##### JSON - Cadastro de campanha.
```
{
    "nome": "Campanha Sucesso",
    "dataInicio": "2017-10-02",
    "dataTermino": "2017-10-03",
    "idTime": 1
}
```

### Service REST - Torcedor

Metodo	| Path	| Descrição	
------- | -------------------------- | ---------
GET     | /torcedor        		     | Retorna todos torcedor, time e campanhas.
GET  	| /torcedor/{id}             | Retorna um torcedor pelo id.
GET  	| /torcedor/addCampanha/     | Retorna um torcedor pelo id.
POST    | /torcedor 	     	     | Cadastra um torcedor.
PUT     | /torcedor  	             | Altera um torcedor.
DELETE  | /torcedor/{id}             | Deleta um torcedor.

##### JSON - Torcedor. (Cadastro)
```
{
    "nome": "Cesar Alexandre Searlini Junior",
    "dataNascimento": "1990-08-14",
    "email": "cesar.searlini@gmail.com",
    "idTime": 1
}
```
##### Modelo Response JSON  - Torcedor com Time do coração
```
{
	"id": 10,
	"nome": "Cesar Alexandre Searlini Junior",
	"email": "cesar.searlini@gmail.com",
	"dataNascimento": "1990-08-13",
	"time": {
	   "id": 1,
	   "nome": "São Paulo"
	}
}
```

##### JSON - Torcedor - Associa torcedor a campanha (Cadastro)
```
[
    {
        "idTorcedor": 4,
        "idCampanha": 1
    }
]
```