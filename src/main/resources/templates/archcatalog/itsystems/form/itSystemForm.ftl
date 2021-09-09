<#import "*/parts/navbar.ftl" as nav>
<#assign
isRevision = isHistoryObj

>

<#macro sys>
    <@nav.nav>
        <div class="row">
            <div class="col-3">
                <nav class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="profile">Профиль</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="docs">Документы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="modules">Модули</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="modules">Ассоциации</a>
                    </li>
                    <#if !isRevision>
                            <li class="nav-item">
                                <a class="nav-link" href="history">История изменений</a>
                            </li>
                    </#if>
                    </ul>
                </nav>
            </div>
            <div class="col-9">
                <#if isRevision>
                    <a href="/itsystems/${itSystems.id}/profile" class="card-link">Вернуться назад</a>
                    <#else>
                        <h6 class="badge badge-pill badge-success">Текущая версия</h6>
                        <a href="/itsystems" class="card-link">Вернуться назад</a>
                </#if>
                <#nested>
            </div>
        </div>
    </@nav.nav>
</#macro>