<#import "*/parts/navbar.ftl" as nav>
<#import "*/badges.ftl" as badge>

<@nav.nav>
    <div class="row">

        <div class="col-4">
            <form>
                <div class="form-group">
                    <a class="btn btn-secondary" href="/products/add" role="button">Добавить новый продукт</a>
                </div>
            </form>
        </div>
        <div class="card-columns">
            <#list products as product>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">"${product.name} ${product.version}"</h5>
                        <@badge.product status="${product.status}"/>
                        <p class="card-text">"${product.description}"</p>
                        <a href="/products/${product.id}/profile" class="card-link">Открыть карточку продукта</a>
                    </div>
                </div>
            <#else>
                Нет зарегистрированных продуктов
            </#list>
        </div>
        <div class="col-8">

        </div>
    </div>

</@nav.nav>