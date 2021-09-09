<#import "*/productForm.ftl" as prod>
<#import "*/badges.ftl" as badge>

<#assign
isRevision = isHistoryObj ??
status = product.getStatus()
>

<@prod.prod>
    <div class="col-9">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">"${product.name} ${product.version}"</h1>
                <@badge.product status = status />
                <p class="lead">"${product.description}"</p>
            </div>


        </div>

        <#if !isRevision>
        <a href="/products/${product.id}/edit" class="card-link">Редактировать</a>
        <!-- Button trigger modal -->

            <#if status == "Actual">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newVersionModal">Выпустить новую
            версию продукта
        </button>
            </#if>

        </#if>
    </div>

    <!-- addModal -->
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

