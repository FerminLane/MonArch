<#import "*/committeeForm.ftl" as comm>
<#import "*/parts/badges.ftl" as badge>

<#assign
isRevision = isHistoryObj ??
    statusCode = committee.getStatus().getId()
>

<@comm.comm>

    <div class="card">
        <div class="card-header">
            Статус:
            <@badge.badge color="${committee.status.color}" status="${committee.status.name}"/>
        </div>
        <div class="card-body">
            <h5 class="card-title">Повестка заседания: ${committee.agenda!""}</h5>
            <p class="card-text">Заявитель: ${committee.applicant!""}</p>
            <p class="card-text">Тип заседания: ${committee.type!"Нет информации"}</p>
            <p class="card-text">Планируемая дата заседания: ${committee.approxDateOfMeeting!""}</p>
            <p class="card-text">Выделеный архитектор/рецензент: ${committee.reviewer!""}</p>


            <h5 class="card-title">Проекты решений:</h5>
            <textarea readonly class="form-control" name="initialDecision" id="initialDecision"
            >${committee.initialDecision!""}</textarea>
        </div>
    </div>

    <#if statusCode == "cs2">
        <div class="card mt-5">
            <div class="card-header" style="color: cornflowerblue">
                Фактическая дата
                заседания: ${committee.dateOfMeeting!""}
            </div>
            <div class="card-body">
                <h5 class="card-title">Итоговые проекты решений:</h5>
                <textarea readonly class="form-control" name="finalDecision" id="finalDecision"
                          style="background-color: aquamarine">${committee.finalDecision!""}</textarea>
            </div>
        </div>

    <#elseif statusCode == "cs3">
    <#else>
        <#if !isRevision>

            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
               data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                Действия
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="/committees/${committee.id}/profile/edit">Редактировать</a>
                <a class="dropdown-item" href="" data-toggle="modal" data-target="#changeStatusModal">Сменить
                    статус</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="" data-toggle="modal" data-target="#decisionModal">Выпустить
                    решение</a>
            </div>
        </#if>
    </#if>
    <p class="card-title">Комментарии</p>
    <textarea readonly class="form-control" name="comment" id="comment"
    >${committee.comment!""}</textarea>

    <!-- decisionModal -->
    <div class="modal fade" id="decisionModal" tabindex="-1" role="dialog" aria-labelledby="decisionModalLabel"
         aria-hidden="true">
        <form action="profile/decision" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Выпустить решение АК</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <div class="form-group">
                            <label for="initialDecision">Исходная формулировка проекта решений</label>
                            <textarea readonly class="form-control" name="initialDecision" id="initialDecision"
                                      rows="3">${committee.initialDecision!""}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="finalDecision">Финальная формулировка проекта решений</label>
                            <textarea class="form-control ${(finalDecisionError??)?string('is-invalid', '')}"
                                      name="finalDecision" id="finalDecision"
                                      rows="3"></textarea>
                            <#if finalDecisionError??>
                                <div class="invalid-feedback">
                                    ${finalDecisionError}
                                </div>
                            </#if>
                        </div>

                        <div class="form-group">
                            <label for="version">Фактическая дата проведения</label>
                            <input type="date" name="dateOfMeeting" id="dateOfMeeting"
                                   class="form-control ${(dateOfMeetingError??)?string('is-invalid', '')}"/>
                            <#if dateOfMeetingError??>
                                <div class="invalid-feedback">
                                    ${dateOfMeetingError}
                                </div>
                            </#if>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-primary">Выпустить решение</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

    <!-- changeStatusModal -->
    <div class="modal fade" id="changeStatusModal" tabindex="-1" role="dialog" aria-labelledby="changeStatusModal"
         aria-hidden="true">
        <form action="profile/changestatus" method="post">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Смена статуса</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="vendorId">Статус</label>
                            <select class="custom-select" name="status" id="status">
                                <#list statuses as status>
                                    <option value=${status.id}>${status.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-primary">Сменить статус</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

</@comm.comm>

