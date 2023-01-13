package site.nomoreparties.stellarburgers.utilities;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserApi {
    @Step("Отправить запрос на вход под логином пользователя")
    public ValidatableResponse login(UserLogin userLogin) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(userLogin)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then().log().all();
    }

    @Step("Получение успешного ответа на запрос по созданию пользователя")
    public String loginInSusses(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("accessToken");
    }

    @Step("Отправить запрос на удаление пользователя")
    public ValidatableResponse delete(String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then().log().all();
    }

    @Step("Получение успешного ответа на запрос по удалению пользователя")
    public void assertDeleteSusses(ValidatableResponse response) {
        response.assertThat()
                .statusCode(202)
                .body("message", equalTo("User successfully removed"));
    }
    @Step("Отправить запрос на создание пользователя")
    public ValidatableResponse create(UserCreate userCreate) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(userCreate)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then().log().all();
    }

    @Step("Получение успешного ответа на запрос по созданию пользователя")
    public String assertCreationSusses(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("accessToken");
    }
}
