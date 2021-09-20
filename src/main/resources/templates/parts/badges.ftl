
<#macro actual status>

    <#if status == 'true'>
        <h6 class="badge badge-pill badge-success">Actual</h6>
    <#else>
        <h6 class="badge badge-pill badge-warning">Stale</h6>
    </#if>
</#macro>

<#macro badge color status>
        <h6 class="badge badge-pill badge-success" style="background-color: ${color!"gray"}">${status!"Нет данных"}</h6>
</#macro>




