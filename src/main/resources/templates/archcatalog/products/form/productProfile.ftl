<#import "*/productForm.ftl" as prod>

<@prod.prod>
    <div class="col-9">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">"${product.name}"</h1>
                <p class="lead">"${product.description}"</p>
            </div>



        </div>
        <a href="/products/${product.id}/edit" class="card-link">Редактировать</a>
    </div>

</@prod.prod>

