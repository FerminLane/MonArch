<#import "*/parts/navbar.ftl" as nav>


<@nav.nav>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="formGroupExampleInput">Наименование продукта</label>
            <input type="text" class="form-control" name="name" id="formGroupExampleInput"
                   placeholder="Введите имя">
        </div>

        <div class="form-group">
            <label for="formGroupExampleInput">Версия продукта</label>
            <input type="text" class="form-control" name="version" id="formGroupExampleInput"
                   placeholder="">
        </div>

        <div hidden class="form-group">
            <label for="formGroupExampleInput">Статус продукта</label>
            <input type="text" class="form-control" name="status" value="Actual" id="formGroupExampleInput"
                   placeholder="">
        </div>

        <div class="form-group">
            <label for="exampleFormControlTextarea1">Описание продукта</label>
            <textarea class="form-control" name="description" id="exampleFormControlTextarea1"
                      rows="3"></textarea>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary ml-2">Применить</button>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</@nav.nav>