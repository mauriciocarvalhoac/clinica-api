# Informações sobre o sistema

* [Acesse esse link para saber mais sobre o projeto](https://mauriciocarvalho.net/)

# Tecnologias e informações

## 1. Docker e Kubernetes

### Criar um Cluster k3d

* k3d cluster create --config ./k8s/clinic-app.yaml

### Subindo os Helm Charts

#### 1. Criando os charts do projeto:

* helm create clinic-db ./k8s/clinic-db/
* helm create clinic-api ./k8s/clinic-api/

#### 2. Instalando os charts do projeto:

* helm install clinic-db ./k8s/clinic-db/
* helm install clinic-api ./k8s/clinic-api/

### Subindo no Kubernetes

Para deployar no kubernetes localmente, execute o comando:

* mvn clean install
* docker build -t mauriciocarvalhoac/clinic-api:1.0.0 .
* docker pull mauriciocarvalhoac/clinic-api:1.0.0
* k3d image import mauriciocarvalhoac/clinic-api:1.0.0 -c clinic-app
* kubectl rollout restart deployment clinic-api

## 2. Migrations

## 3. Github Actions

* Criar pipeline no github