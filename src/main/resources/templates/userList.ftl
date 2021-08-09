<#import "parts/common.ftl" as com>

<@com.page>
    Список пользователей
    <table>

        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Редактировать профиль</a></td>
            </tr>

        </#list>
        </tbody>
    </table>
</@com.page>