# REST API PARA CADASTRO/LOGIN/LISTAR USUARIOS
Esta é um Api desenvolvida para um desafio na linguagem JAVA



## Funcionalidades

### Cadastro de Usuario
Cria um usuario
```
http://{HOST}:{PORTA}/usuario/ via método POST (JSON)
```
####Atributos JSON
 * name 
 * email 
 * password
 * phones (lista) - com Atributos number e ddd
 
 
 ** Exemplo
```
{
        "name": "Carlos dos Santos",
        "email": "cgs@globo.com",
        "password": "24132",
        "phones": [
            {
                "number": "217654321",
                "ddd": "21"
            }
        ]
    }


```



### Login
Efetua login utilizando email e senha
```
http://{HOST}:{PORTA}/login/ via método POST (JSON)
```
####Atributos JSON

* email - Email fornecido no cadastro de usuario
* password - Senha fornecida no cadastro do usuario

### Listar Dados do Usuario
Lista os dados de perfil do usuario

```
http://{HOST}:{PORTA}/usuario/{ID} via método GET
```

####Parametros

* ID - Identificador de usuario recebido no momento da cadastro

#### Acesso via Token
basta fazer a requisição enviando os parametros pelo Header
x-auth-token: {Token de acesso recebido no momento do cadastro de usuario}

* Exemplo de Envio no Header
``` 
x-auth-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0Nzc4NjU1MDYsImlkIjoiYWRtaW4iLCJvcmlnX2lhdCI6MTQ3Nzg2MTkwNn0.Pt4O5ZqDtVGEomQlnOSlxSvCGGvs-tFXmoJQ0OoXbRU
```


** Exemplos
```
http://{HOST}:{PORTA}/usuario/{ID} - Lista notas com mês de referencia Fevereiro

http://localhost:{PORTA}/usuarios/f7efbd8a9f454db6a00ab9421382a479 - Lista dados do usuario do id fornecido
```


