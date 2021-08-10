<#import "parts/common.ftl" as com>
<#import "parts/auth.ftl" as a>

<@com.page>
    <div>
        <span><a href="/user">Список пользователей</a> </span>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Tag">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>


    <div>Список сообщений</div>
    <form method="get" action="/home">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
    <#list texts as txt>

        <div>
            <a>${txt.authorName}</a>
            <c>${txt.text}</c>
            <d>${txt.tag}</d>
            <div>
                <img src="/img/${txt.fileName}">
                <#if txt.filename??>

                </#if>
            </div>
        </div>
    <#else>
        Сообщений нет
    </#list>
</@com.page>

