# REST API PARA CADASTRO/LOGIN/LISTAGEM de USUARIOS
Esta � um Api desenvolvida para um desafio na linguagem JAVA

## Testes e Avalia��o da API
API Implantada no HEROKU
```
https://desafioconcretesolutions.herokuapp.com/
```

## Funcionalidades

### Cadastro de Usuario
Cria um usuario
```
http://{HOST}/usuario/ via m�todo POST (JSON)
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
http://{HOST}/login/ via m�todo POST (JSON)
```

 ** Exemplo
```
{
        "email": "cgs@globo.com",
        "password": "24132"
        
}


```

####Atributos JSON

* email - Email fornecido no cadastro de usuario
* password - Senha fornecida no cadastro do usuario

### Listar Dados do Usuario
Lista os dados de perfil do usuario

```
http://{HOST}/usuario/{ID} via m�todo GET
```

####Parametros

* ID - Identificador de usuario recebido no momento da cadastro

#### Acesso via Token
basta fazer a requisi��o enviando os parametros pelo Header


x-auth-token: {Token de acesso recebido no momento do cadastro de usuario}

* Exemplo de Envio no Header
``` 
x-auth-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0Nzc4NjU1MDYsImlkIjoiYWRtaW4iLCJvcmlnX2lhdCI6MTQ3Nzg2MTkwNn0.Pt4O5ZqDtVGEomQlnOSlxSvCGGvs-tFXmoJQ0OoXbRU
```


** Exemplo
```
http://{HOST}/usuario/f7efbd8a9f454db6a00ab9421382a479 - Lista dados do usuario do id fornecido
```


