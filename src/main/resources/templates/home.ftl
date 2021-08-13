<#import "parts/navbar.ftl" as nav>

<@nav.nav>

    <div class="form-row"></div>
    <div class="form-group col-md-6">
        <form method="get" action="/home" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
    </div>


    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить запись
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group" mt-2>
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Tag">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="file" id="customFile">
                        <label class="custom-file-label for=" customFile">Выбрать файл</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary ml-2">Добавить</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list texts as txt>
            <div class="card my-3">
                <div>
                    <img src="/img/${txt.fileName}" class="card-img-top">
                    <#if txt.filename??>

                    </#if>
                </div>
                <div class="m-2">
                    ${txt.text}
                </div>
                <div class="card-footer text-muted">
                    ${txt.authorName}
                </div>
            </div>
        <#else>
            Сообщений нет
        </#list>
    </div>
</@nav.nav>


