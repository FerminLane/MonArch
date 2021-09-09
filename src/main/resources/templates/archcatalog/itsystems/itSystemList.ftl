<#import "*/parts/navbar.ftl" as nav>

<@nav.nav>
    <div class="row">

        <div class="col-4">
            <form>
                <div class="form-group">
                    <a class="btn btn-secondary" href="/itsystems/add" role="button">Добавить новую инф.систему</a>
                </div>
            </form>
        </div>
        <div class="card-columns">
            <#list itSystems as itSystem>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">"${itSystem.name}"</h5>
                        <h6 class="badge badge-pill badge-success">тест</h6>
                        <p class="card-text">"${itSystem.description}"</p>
                        <a href="/itsystems/${itSystem.id}/profile" class="card-link">Открыть карточку инф.системы</a>
                    </div>
                </div>
            <#else>
                Нет зарегистрированных систем
            </#list>
        </div>
        <div class="col-8">

        </div>
    </div>

</@nav.nav>