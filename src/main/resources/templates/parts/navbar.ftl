<#import "auth.ftl" as a>
<#import "common.ftl" as com>

<#macro nav>
    <@com.page>
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#960b10;">
            <a class="navbar-brand" href="/main">MonArch</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/main">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/home">Демо_Сообщения</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user">Демо_Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Архитектурный комитет</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Регистрация сервиса</a>
                    </li>


                    <#if Session.SPRING_SECURITY_CONTEXT??>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Здравствуйте, ${Session.SPRING_SECURITY_CONTEXT.getAuthentication().getPrincipal().getUsername()}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <@a.logout/>
                            </div>
                        </li>

                    <#else>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Login
                            </a>
                            <div class="dropdown-menu">
                                <form class="px-4 py-3" action="/login" method="post">
                                    <div class="form-group">
                                        <label for="exampleDropdownFormEmail1">Email address</label>
                                        <input type="email" class="form-control" id="exampleDropdownFormEmail1"
                                               name="username"
                                               placeholder="email@example.com">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleDropdownFormPassword1">Password</label>
                                        <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                               name="password"
                                               placeholder="Password">
                                    </div>
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="dropdownCheck">
                                        <label class="form-check-label" for="dropdownCheck">
                                            Remember me
                                        </label>
                                    </div>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <button type="submit" class="btn btn-primary">Sign in</button>
                                </form>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/registration">New around here? Sign up</a>
                                <a class="dropdown-item" href="#">Forgot password?</a>
                            </div>
                        </li>
                    </#if>
                </ul>
            </div>
        </nav>
        <div class="container">
            <#nested>
        </div>
    </@com.page>
</#macro>