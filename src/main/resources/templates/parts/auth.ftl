<#macro login>
    <form action="/login" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Войти"/></div>
    </form>

</#macro>

<#macro logout>
    <form c  class="dropdown-item" action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input class="dropdown-item" type="submit" value="Logout"/></div>
    </form>
</#macro>

<#macro registration>
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Регистрация"/></div>
    </form>

</#macro>

