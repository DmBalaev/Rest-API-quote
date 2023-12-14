# Rest-API-quote


### End points

#### User Controller
Создание нового пользователя

* **URL**: `/api/v1/user`
* **Method**: `POST`
* **Request Body:**
```json
{
    "username": "exampleUser",
    "password": "examplePassword",
    "email": "user@example.com"
}
```
* **Response**:
   * HTTP Status Code: `201 Created`
____
#### Vote Controller
Проголосовать
* **URL**: **/api/v1/votes/vote/{quoteId}**
* **Method**: `POST`
* **Request Parameters:**
    * `voteType`: (String) Type of vote ("LIKE" or "DISLIKE")
* **Response**:
  * HTTP Status Code: `200 OK`


Отменить голос
* **URL**: `/api/v1/votes/cancel/{quoteId}`
* **Method**: `POST`
* **Response**:
  * HTTP Status Code: `200 OK`
___
#### Quote Controller
Добавить новую цитату
* **URL**: `/api/v1/quotes/add`
* **Method**: `POST`
* ** Request Body:**
    ```json
    {
      "content": "This is a new quote."
    }
    ```
* **Response**:
  * HTTP Status Code: `201 Created`

Обновление цитаты
* **URL**: `/api/v1/quotes/update`
* **Method**: `PUT`
* **Request Body**:
```json
{
    "quoteId": 123,
    "newContent": "Updated quote content."
}
```
* Response:
  * HTTP Status Code: `200 OK`

Удалить цитату
* **URL**: `/api/v1/quotes/delete/{quoteId}`
* **Method**: `DELETE`
* **Response**:
  * HTTP Status Code: `200 OK`

Список лучших цитат
* **URL**: `/api/v1/quotes/top`
* **Method**: `GET`
* **Response**:
  * HTTP Status Code: `200 OK`

Список худших цитат
* **URL**: `/api/v1/quotes/bottom`
* **Method**: `GET`
* **Response**:
  * HTTP Status Code: `200 OK`

Случайная цитата
* **URL**: `/api/v1/quotes/random`
* **Method**: `GET`
* **Response**:
HTTP Status Code: `200 OK`
___
### Docker-compose для локального развертывания

1. Склонируйте репозиторий

        git clone https://github.com/DmBalaev/Rest-API-quote.git

2. Перейдите в папку проекта
        
        cd Spring-Boot-Rest-Task-Manager

3. Build the maven project

        mvn clean install
     

4. Запустите контейнер

        docker-compose up
___

### Docker hub
Вы можете скачать и запустить образ из Docker Hub
```bash
docker run -p 8080:8080 dmbalaev/app.jar
```
Приложение автоматически запустится на порту 8080.