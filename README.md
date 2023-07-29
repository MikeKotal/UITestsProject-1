# UI-тесты, проект №1
В данном проекте покрыт тестами UI веб-приложения [Яндекс.Самокат](http://qa-scooter.praktikum-services.ru)
## В проекте используется:
* Java 11
* Maven
* JUnit 4
* Selenium
## Запуск тестов
Склонировать репозиторий
```
git clone https://github.com/MikeKotal/UITestsProject-1.git
```
Локально запустить тесты, лежат по следующему пути:
```
src/test/java/scooter_ui
```
В проекте реализован запуск тестов в браузере Chrome и Firefox, логика реализована в классе **Initializer**

Для просмотра отчета в Allure, выполнить следующие комманды:
```
mvm clean test
mvn allure:serve
```