<#import "*/productForm.ftl" as prod>

<@prod.prod>
    <div>
        <table class="table">
            <caption>История изменений</caption>
            <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Время изменения</th>
                <th scope="col">Изменения внес</th>
                <th scope="col">Ссылка</th>
            </tr>
            </thead>
            <tbody>
            <#list audit as auditelement>

                <tr>
                    <td>${auditelement[0].name}</td>
                    <td>${auditelement[0].updateDateTime}</td>
                    <td>${auditelement[0].updatedBy}</td>
                    <td>
                        <a href="history/${auditelement[1].id}/profile" class="card-link">Подробнее</a>
                    </td>
                </tr>

            </#list>
            </tbody>
        </table>

    </div>
</@prod.prod>