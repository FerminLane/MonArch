<#import "*/parts/navbar.ftl" as nav>
<#import "/spring.ftl" as spring />



<@nav.nav>
    <form method="post">
        <div class="form-group">
            <label for="name">Наименование продукта</label>
            <input type="text" name="name" id="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
                   value="<#if product??>${product.name}</#if>" placeholder="Введите имя"/>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>

        <div class="form-group" >
            <label for="version">Версия продукта</label>
            <input type="text" name="version" id="version" class="form-control ${(versionError??)?string('is-invalid', '')}"
                   value="<#if product??>${product.version}</#if>" placeholder="Пример 0.1"/>
            <#if versionError??>
                <div class="invalid-feedback">
                    ${versionError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label for="vendorId">Вендор</label>
            <select class="custom-select" name="vendor" id="vendorId">
                <option value="">Выберите вендора</option>
                <#list vendors as vendor>
                    <option value="${vendor.id}">${vendor.name}</option>
                </#list>
            </select>
            <#if vendorError??>
                <div class="invalid-feedback">
                    ${vendorError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label for="platformId">Платформа</label>
            <select class="custom-select" name="platform" id="platformId">
                <option value="">Выберите платформу</option>
                <#list platforms as platform>
                    <option value="${platform.id}">${platform.name}</option>
                </#list>
            </select>
            <#if platformError??>
                <div class="invalid-feedback">
                    ${platformError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label for="description">Описание продукта</label>
            <textarea class="form-control" name="description" id="description"
                      rows="3"></textarea>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary ml-2">Применить</button>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</@nav.nav>