<#import "*/parts/navbar.ftl" as nav>
<#import "*/parts/badges.ftl" as badge>

<@nav.nav>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Имя</th>
                <th scope="col">Иконка</th>
                <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list buildingareas as buildingarea>
                <tr>
                    <td>${buildingarea.id}</td>
                    <td>${buildingarea.name}</td>
                    <td> <h6 class="badge badge-pill badge-success" style=" background-color: ${buildingarea.color}">${buildingarea.name}</h6></td>
                    <td>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#removeModal" data-delprod="${buildingarea.id}">Удалить</button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">Добавить площадку сооружения</button>

    <!-- addModal -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form action="buildingareas/add" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Добавить площадку сооружения</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input type="text" class="form-control" id="buildingArea-name" name="buildingAreaName">
                        </div>

                        <div class="form-group">
                            <input type="color" class="form-control" id="buildingArea-color" name="buildingAreaColor">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

    <!-- removeModal -->
    <div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <form action="buildingareas/remove" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Удалить площадку</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input hidden type="text" class="form-control" id="buildingArea-id" name="buildingAreaId">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-danger">Удалить площадку</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

</@nav.nav>

<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>

<script>
    $('#removeModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var delprod = button.data('delprod') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-body input').val(delprod)
    })
</script>