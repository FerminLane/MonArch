<#import "*/parts/navbar.ftl" as nav>

<@nav.nav>
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">"${itsystem.name}"</h1>
            <p class="lead">"${itsystem.description}"</p>
        </div>
    </div>
    <a href="/itsystem/edit/${itsystem.id}" class="card-link">Редактировать</a>
</@nav.nav>