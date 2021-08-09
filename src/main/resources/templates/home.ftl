<#import "parts/common.ftl" as com>
<#import "parts/auth.ftl" as a>

<@com.page>
    <div>
        <@a.logout/>
        <span><a href="/user">Список пользователей</a> </span>
        <form method="post">
            <input type="text" name="message" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Tag">
            <button type="submit">Добавить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>


    <div>Список сообщений</div>
    <form method="get" action="/home">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Найти</button>
    </form>
    <#list messages as mes>

        <div>
            <a>${mes.authorName}</a>
            <c>${mes.message}</c>
            <d>${mes.tag}</d>
        </div>
    <#else>
        Сообщений нет
    </#list>
</@com.page>

