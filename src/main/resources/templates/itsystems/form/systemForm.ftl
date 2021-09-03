<#import "*/parts/navbar.ftl" as nav>


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
                        <a class="nav-link" href="history">История изменений</a>
                    </li>
                    </ul>
                </nav>
            </div>
            <div class="col-9">
                <#nested>
            </div>
        </div>
    </@nav.nav>
</#macro>