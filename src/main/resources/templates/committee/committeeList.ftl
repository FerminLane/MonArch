<#import "*/parts/navbar.ftl" as nav>
<#import "*/parts/badges.ftl" as badge>


<@nav.nav>

    <div>
        <a class="btn btn-secondary" href="/committees/add" role="button">Создать новое заседание</a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Выносимый вопрос</th>
                <th scope="col">Автор</th>
                <th scope="col">Статус</th>
                <th scope="col">Планируемая дата проведения</th>
                <th scope="col">Фактическая дата проведения</th>
                <th scope="col">Курирующий архитектор</th>
                <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list committees as committee>
                <tr>
                    <td>${committee.id}</td>
                    <td>${committee.agenda!""}</td>
                    <td>${committee.applicant!""}</td>
                    <td><@badge.badge color="${committee.status.color}" status="${committee.status.name}"/></td>
                    <td>${committee.approxDateOfMeeting!""}</td>
                    <td>${committee.dateOfMeeting!""}</td>
                    <td>${committee.author.username!""}</td>
                    <td>
                        <a href="/committees/${committee.id}/profile" class="card-link">Открыть карточку заседания</a>
                    </td>
                </tr>
            <#else>
                <td>Нет заседаний</td>
            </#list>
            </tbody>
        </table>
    </div>

</@nav.nav>