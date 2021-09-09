<#import "*/parts/navbar.ftl" as nav>
<#assign
isRevision = isHistoryObj ??

>

<#macro prod>
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
                    <h6 class="badge badge-pill badge-secondary">Revision</h6>
                    <a href="/products/${product.id}/profile" class="card-link">Вернуться назад</a>
                    <#else>
                        <a href="/products" class="card-link">Вернуться назад</a>
                </#if>
                <#nested>
            </div>
        </div>
    </@nav.nav>
</#macro>