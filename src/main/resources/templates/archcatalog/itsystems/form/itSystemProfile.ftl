<#import "*/itSystemForm.ftl" as itsys>

<@itsys.sys>
    <div class="col-9">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">"${itSystem.name}"</h1>
                <p class="lead">"${itSystem.description}"</p>
            </div>
        </div>
        <a href="/itsystems/${itSystem.id}/edit" class="card-link">Редактировать</a>
    </div>

</@itsys.sys>

