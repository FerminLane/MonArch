<#import "parts/common.ftl" as com>
<#import "parts/auth.ftl" as a>

<@com.page>

    <Title>Вход в систему</Title>
    <@a.login "/login"/>

    <a href="/registration">Регистрация</a>

</@com.page>