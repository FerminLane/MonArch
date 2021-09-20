<#import "parts/common.ftl" as com>

<@com.page>

    <div id="registration">
        <h3 class="text-center text-white pt-5">Registration form</h3>
        <div class="container">
            <div id="registration-row" class="row justify-content-center align-items-center">
                <div id="registration-column" class="col-md-6">
                    <div id="registration-box" class="col-md-12">
                        <form id="registration-form" class="form" action="/registration" method="post">
                            <h3 class="text-center text-info">Регистрация в системе</h3>

                            <div class="form-group">
                                <label for="username" class="text-info">Имя:</label><br>
                                <input type="text" name="username" id="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                                       value="<#if user??>${user.username}</#if>"/>
                                <#if usernameError??>
                                    <div class="invalid-feedback">
                                        ${usernameError}
                                    </div>
                                </#if>
                            </div>

                            <div class="form-group">
                                <label for="password" class="text-info">Пароль:</label><br>
                                <input type="password" name="password" id="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                       value="<#if user??>${user.password}</#if>"/>
                                <#if passwordError??>
                                    <div class="invalid-feedback">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>

                            <div class="form-group">
                                <label for="passwordConf" class="text-info">Повторите пароль:</label><br>
                                <input type="password" name="passwordConf" id="passwordConf" class="form-control ${(passwordConfError??)?string('is-invalid', '')}"
                                       value="<#if user??>${user.passwordConf}</#if>"/>
                                <#if passwordConfError??>
                                    <div class="invalid-feedback">
                                        ${passwordConfError}
                                    </div>
                                </#if>
                            </div>

                            <div class="form-group">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@com.page>

