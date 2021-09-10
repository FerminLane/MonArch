<#import "common.ftl" as com>


<@com.page>

    <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="/login" method="post">
                            <h3 class="text-center text-info">Ошибка входа</h3>
                            <div class="form-group">
                                <h1>У вас нет прав для просмотра этой страницы</h1>
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="/" class="text-info">Вернуться на главную</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@com.page>