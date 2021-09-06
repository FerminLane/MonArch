<#import "*/productForm.ftl" as prod>

<@prod.prod>
    <div>

        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите сообщение">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Tag">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <label class="custom-file-label" for="customFile">Выбрать файл</label>
                    <input type="file" class="custom-file-input" name="file" id="customFile">
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary ml-2">Добавить</button>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>





        <table class="table">
            <caption>Документы</caption>
            <thead>
            <tr>
                <th scope="col">Имя документа</th>
                <th scope="col">Тип документа</th>
                <th scope="col">Дата загрузки</th>
                <th scope="col">Скачать</th>
            </tr>
            </thead>
            <tbody>
            <#list docs as doc>
                <#if doc.name??>
                <tr>
                    <td>${auditelement.name}</td>
                    <td>${auditelement.updateDateTime}</td>
                    <td>${auditelement.updatedBy}</td>
                    <td>
                        <a href="#" class="card-link">Подробнее</a>
                    </td>
                </tr>
                </#if>
            <#else>
                Документов нет
            </#list>
            </tbody>
        </table>

    </div>
</@prod.prod>