<#import "parts/common.ftl" as com>
<#import "parts/auth.ftl" as a>

<@com.page>

    <div>Регистрация пользователя</div>

    <@a.registration/>
    ${message!}

</@com.page>