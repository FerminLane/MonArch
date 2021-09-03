<#import "*/parts/navbar.ftl" as nav>

<@nav.nav>
    <div class="row">

        <div class="col-4">
            <form>
                <div class="form-group">
                    <a class="btn btn-secondary" href="/itsystem/add" role="button">Добавить новую систему</a>
                </div>
            </form>
        </div>
        <div class="card-columns">
            <#list itsystems as itsystem>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">"${itsystem.name}"</h5>
                        <h6 class="badge badge-pill badge-success">"${itsystem.buildingArea}"</h6>
                        <p class="card-text">"${itsystem.description}"</p>
                        <a href="/itsystem/${itsystem.id}/profile" class="card-link">Открыть карточку системы</a>
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