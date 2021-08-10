<#import "auth.ftl" as a>


<#macro page><!DOCTYPE html>
    <html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
    <head>
        <meta charset="UTF-8">
        <title>MonArch</title>
        MonArch - рисуем архитектуру!
        <link rel="stylesheet" href="/static/style.css">
    </head>
    <body>

    <#if Session.SPRING_SECURITY_CONTEXT??>
        <@a.logout/>
    <#else>
        <a href="/login">Login</a>
    </#if>
    <#nested>
    </body>
    </html>
</#macro>




