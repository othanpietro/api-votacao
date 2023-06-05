# api-votacao
API para computar votos em um determinada pauta

# Configurações da API
As configurações da api se encontra no arquivo application.properties dentro da pasta resources

# Criação do Banco de Dados
O arquivo DDL_Desafio_DBC.sql para criação do banco  se encontra na pasta sql dento da pasta resources 

# Fluxo da Api
# SecaoController
Primeiro end-point a ser executado é o:
Post /secao Que envia no body o Objeto SecaoDTO apartir dele vai ser criado a pauta e seção.
No SecaoController ainda temos Get /secao que retorna todas as seções, o Get /secao/{id} retorna 
uma seção especifica pelo idSecao. E o secao/{id}/resultado que retorna o resultado da votação naquele momento.

# VotacaoControler
Após ser criada a seção poderá começar a votação na seção. No end-point Post /votacao é enviado o VotoDTO
nele contém o usuario e o voto e para qual seção é esse voto pelo idSecao

# Job para envio de messagem no kafka
Na classe VotacaoServiceImpl foi criado um job que executa de minuto em minuto consultando a base e na busca de seções 
encerradas. Ele contabiliza os votos atualiza a seção para resultado enviado. E produz a mensagem no tópico kafka.

# Comandos para execução do kafka local
Necessário ter o kafka baixado no pc

comandos kafka

iniciar zookeper
.\bin\windows\zookeeper-server-start.bat config\zookeeper.properties

iniciar kafka
.\bin\windows\kafka-server-start.bat config\server.properties

criar topico kafka
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

criar consumer kafka
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning

criar producer kafka
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test

