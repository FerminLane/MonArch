<#import "parts/navbar.ftl" as nav>

<@nav.nav>
    <div>Редактировать пользователя</div>
    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Сохранить"/></div>

    </form>
</@nav.nav>