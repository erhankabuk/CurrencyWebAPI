# Spring Boot - CurrencyWebAPI

This project is a basic REST API Service example for Spring Boot Application.
CurrencyWebAPI Application get data from API, inserted them to local database then respond client request as JSONObject.
Client send request as "USD/TRY".Application check database incase of update.Return data as {"date":"dd/MM/yyyy","rates":{"TRY":double value},"base":"USD"}.
This application could be developed as needs and useful for basic REST API projects.