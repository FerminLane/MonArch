<#macro product status>

    <#if status == "Actual">
        <h6 class="badge badge-pill badge-success">Actual</h6>
    <#elseif status == "Stale" >
        <h6 class="badge badge-pill badge-warning">Stale</h6>
    </#if>
</#macro>
