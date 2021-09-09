<#import "*/parts/navbar.ftl" as nav>

<@nav.nav>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="formGroupExampleInput">Наименование системы</label>
            <input type="text" class="form-control" name="name" value="${name}" id="formGroupExampleInput"
                   placeholder="Введите имя">
        </div>

        <div class="form-group">
            <label for="exampleFormControlTextarea1">Описание системы</label>
            <textarea class="form-control" name="description" id="exampleFormControlTextarea1"
                      rows="3">${description}</textarea>
        </div>


        <div class="form-group">
            <button type="submit" class="btn btn-primary ml-2">Применить</button>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>

</@nav.nav>

