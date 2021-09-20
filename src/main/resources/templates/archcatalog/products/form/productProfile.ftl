<#import "*/productForm.ftl" as prod>
<#import "*/parts/badges.ftl" as badge>

<#assign
isRevision = isHistoryObj ??
actual = product.getActual()
>

<@prod.prod>
    <div class="col-9">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">"${product.name} ${product.version}"</h1>
                <@badge.actual status="${product.actual?c}"/>
                <p class="lead">"${product.description}"</p>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">${product.vendor!""}</li>
                <li class="list-group-item">${product.platform!""}</li>
            </ul>
        </div>

        <#if !isRevision>
            <a href="/products/${product.id}/profile/edit" class="card-link">Редактировать</a>
            <!-- Button trigger modal -->

            <#if actual>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newVersionModal">
                    Выпустить новую
                    версию продукта
                </button>
            </#if>
        </#if>
    </div>

    <!-- newVersionModal -->
    <div class="modal fade" id="newVersionModal" tabindex="-1" role="dialog" aria-labelledby="newVersionModalLabel"
         aria-hidden="true">
        <form action="profile/newversion" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Выпуск новой версии</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <div class="form-group">
                            <label for="formGroupExampleInput">Наименование продукта</label>
                            <input readonly type="text" class="form-control" name="name" value="${product.name}"
                                   id="formGroupExampleInput">
                        </div>

                        <div class="form-group">
                            <label for="formGroupExampleInput">Версия продукта</label>
                            <input type="text" class="form-control" name="version"
                                   id="formGroupExampleInput">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Выпустить версию</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

</@prod.prod>

