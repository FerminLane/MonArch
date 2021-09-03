<#import "*/itsystems/form/systemForm.ftl" as sys>

<@sys.sys>
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
                    <td>${auditelement.name}</td>
                    <td>${auditelement.updateDateTime}</td>
                    <td>${auditelement.updatedBy}</td>
                    <td>
                        <a href="#" class="card-link">Подробнее</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

    </div>
</@sys.sys>