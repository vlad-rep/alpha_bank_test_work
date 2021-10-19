# Alpha Bank test case
# Задание:
    Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:
    если курс по отношению к рублю за сегодня стал выше вчерашнего, 
    то отдаем рандомную отсюда 
    https://giphy.com/search/rich
    если ниже - отсюда 
    https://giphy.com/search/broke
    
    Ссылки
    REST API курсов валют - https://docs.openexchangerates.org/
    REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
    
    Must Have
    Сервис на Spring Boot 2 + Java / Kotlin
    Запросы приходят на HTTP endpoint, туда передается код валюты
    Для взаимодействия с внешними сервисами используется Feign
    Все параметры (валюта по отношению к которой смотрится курс, 
    адреса внешних сервисов и т.д.) вынесены в настройки
    
    На сервис написаны тесты 
    (для мока внешних сервисов можно использовать @mockbean или WireMock)
    Для сборки должен использоваться Gradle
    Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
    
    Nice to Have
    Сборка и запуск Docker контейнера с этим сервисом
# HTTP endpoint
    Получить гифку по курсу:
    GET /difference/gif/get

# Инструкция по запуску
1) Скачать копию с гитхаб:
   https://github.com/....
2) Проверить конфигурацию в application.yaml

    (Установить необходимую валюту: alpha-bank.open-exchange-rates.currency)

    (Изменить app-id openexchangerates.org : alpha-bank.open-exchange-rates.app-id)

    (Изменить api-key giphy.com: alpha-bank.giphy.api-key)
   
4) Запустить в IDE или создать Docker container
# Сборка и запуск .jar:
    ./gradlew build
    java -jar alpha_bank-1.0.0.jar
# Docker container
    Build image for Dockerfile
      docker build -f Dockerfile -t alpha_bank .
      docker run -p 8081:8081 alpha_bank


