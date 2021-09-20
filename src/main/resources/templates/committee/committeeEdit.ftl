<#import "*/parts/navbar.ftl" as nav>



<@nav.nav>
    <form method="post" name="editedCommittee">

        <div class="form-group">
            <label for="agenda">Повестка</label>
            <textarea class="form-control ${(agendaError??)?string('is-invalid', '')}" name="agenda" id="agenda"
                      rows="3"><#if committee??>${committee.agenda}</#if></textarea>
            <#if agendaError??>
                <div class="invalid-feedback">
                    ${agendaError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label for="applicant">Заявитель</label>
            <input type="text" name="applicant" id="applicant"
                   class="form-control ${(applicantError??)?string('is-invalid', '')}"
                   value="<#if committee??>${committee.applicant}</#if>"/>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${applicantError}
                </div>
            </#if>
        </div>


        <div class="form-group">
            <label for="type">Тип заседания</label>
            <select class="custom-select" name="type" id="type">
                <option selected value="Не определено">Не определено</option>
                <option value="Очное">Очное</option>
                <option value="Заочное">Заочное</option>
            </select>
        </div>


        <div class="form-group">
            <label for="approxDateOfMeeting">Планируемая дата проведения</label>
            <input type="text" name="approxDateOfMeeting" class="form-control"
                   value="<#if committee??>${committee.approxDateOfMeeting}</#if>"/>
        </div>

        <div class="form-group">
            <label for="applicant">Рецензент</label>
            <input type="text" name="reviewer" id="reviewer"
                   class="form-control ${(reviewerError??)?string('is-invalid', '')}"
                   value="<#if committee??>${committee.reviewer}</#if>"/>
            <#if reviewerError??>
                <div class="invalid-feedback">
                    ${reviewertError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <label for="initialDecision">Выносимые проекты решений</label>
            <textarea class="form-control ${(initialDecisionError??)?string('is-invalid', '')}" name="initialDecision"
                      id="initialDecision"
                      rows="3"><#if committee??>${committee.initialDecision}</#if></textarea>
            <#if initialDecisionError??>
                <div class="invalid-feedback">
                    ${initialDecisionError}
                </div>
            </#if>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary ml-2">Применить</button>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</@nav.nav>


<script type="text/javascript">
    $(function () {

        $('input[name="approxDateOfMeeting"]').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });

        $('input[name="approxDateOfMeeting"]').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
        });

        $('input[name="approxDateOfMeeting"]').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

    });
</script>